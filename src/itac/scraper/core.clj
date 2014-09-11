(ns itac.scraper.core
  (:require [net.cgrand.enlive-html :as html]
            [net.cgrand.xml :as xml]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:use seesaw.core
        seesaw.cells
        seesaw.mig)
  (:import [com.gravity.goose
            Configuration
            Goose]))

(defrecord Article [title url text])

(def feeds
  {"BBC News" "http://feeds.bbci.co.uk/news/rss.xml"
   "CBBC Newsround" "http://www.bbc.co.uk/newsround/news/rss.xml"
   "CNN" "http://rss.cnn.com/rss/edition.rss"})

(defn- load-xml-resource [url]
  (html/xml-resource (java.net.URL. url)))

; String -> Future<List<Article>>
(defn articles [feed]
  (future (let [rss (load-xml-resource feed)]
            (->> (html/select rss #{[:item :title] [:item :link]})
                 (map html/text)
                 (partition 2)
                 (remove #(re-matches #"^VIDEO:.*|^.*(P|p)ictures:.*" (first %)))
                 (map (fn [[title url]] (Article. title url nil)))))))

; Article -> Future<Article>
(defn get-article [article]
  (let [title  (.title article)
        url    (.url article)
        config (doto (Configuration.)
                 (.setEnableImageFetching false))]
    (future
      (Article. title url
                (-> (.. (Goose. config)
                        (extractContent url (slurp url))
                        cleanedArticleText
                        trim)
                    (str/replace #"^.*\(CNN\)\ \-\-." "")
                    (str/replace #"\(CNN\)\ \-\-." ""))))))

(defn- string-renderer
  [f]
  "Siehe https://github.com/daveray/seesaw/issues/8"
  (default-list-cell-renderer
    (fn [this {:keys [value]}]
      (.setText this (str (f value))))))

(defn- wait-for-future
  [fut f & [cancel-f]]
  (let [t (javax.swing.Timer. 500 nil)
        a (action :handler (fn [e]
                             (if (future-cancelled? fut)
                               (do (.stop t)
                                   (invoke-later
                                    (if cancel-f
                                      (cancel-f))))
                               (when (future-done? fut)
                                 (.stop t)
                                 (invoke-later
                                  (f @fut))))))]
    (doto t
      (.addActionListener a)
      .start)))

(defn articles-panel
  [callback & [cancel-cb]]
  (let [refresh-btn  (button :icon (io/resource "itac/scraper/icons/refresh.png"))
        use-btn      (button :text "Use article")
        stop-btn     (button :icon (io/resource "itac/scraper/icons/stop.png")
                             :enabled? false)
        cur-future   (atom nil)
        source-sel   (combobox :model (into [{:title "(no source selected)"}]
                                            (for [[title feed] (map vec (partition 2 feeds))]
                                              {:title title
                                               :feed  feed}))
                               :renderer (string-renderer :title))
        articles-sel (listbox :renderer (string-renderer #(.title %)))
        panel        (border-panel
                      :north (mig-panel :constraints ["fillx" "[grow][][]" ""]
                                        :items [[source-sel "grow"]
                                                [refresh-btn  ""]
                                                [stop-btn   ""]])
                      :center (scrollable articles-sel)
                      :south (mig-panel :constraints ["fillx" "[grow][]" ""]
                                        :items [[use-btn "grow"]]))
        widgets      [refresh-btn use-btn source-sel articles-sel]]
    
    (letfn [(fetch-clicked [e]
              (config! articles-sel :model [])
              (when-let [feed (:feed (selection source-sel))]
                (wait-for-future
                 (reset! cur-future (articles feed))
                 (fn [articles]
                   (config! articles-sel :model articles)
                   (reset! cur-future nil))
                 #(reset! cur-future nil))))
            (use-clicked [e]
              (if-let [article (selection articles-sel)]
                (let [f         (fn [article]
                                  (reset! cur-future nil)
                                  (cond
                                   (nil? callback) nil
                                   (ifn? callback)  (callback article)
                                   :else           (.articleLoaded callback article)))
                      cancel-fn (fn []
                                  (reset! cur-future nil)
                                  (cond
                                   (ifn? cancel-cb)  (cancel-cb)
                                   (nil? callback)  nil
                                   :else            (.articleLoadingAborted callback)))]
                  (wait-for-future
                   (reset! cur-future (get-article article))
                   f
                   cancel-fn))))
            (stop-clicked [e] (if @cur-future (future-cancel @cur-future)))

            (change-button-state [_ _ old new]
              (doseq [w widgets]
                (config! w :enabled? (not new)))
              (config! stop-btn :enabled? new))]
      (add-watch cur-future :buttons change-button-state)
      (listen articles-sel :mouse-clicked #(if (= (.getClickCount %) 2) (use-clicked %)))
      (listen source-sel :selection fetch-clicked)
      (listen refresh-btn :action fetch-clicked)
      (listen stop-btn :action stop-clicked)
      (listen use-btn :action use-clicked))
    panel))

(defn- dev-articles-panel
  []
  (->> (articles-panel (fn [article]
                         (-> (frame
                              :content
                              (scrollable
                               (text :multi-line? true
                                     :wrap-lines? true
                                     :text (.text article))))
                             pack!
                             show!)))
       (frame :content)
       pack!
       show!))

