(ns itac.summary.ui.core
  (:require [clojure.inspector :as inspector]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.java.browse :as browse]
            [clojure.java.shell :as sh]
            [clojure.pprint :as pprint]
            [itac.summary.core :as core]
            [itac.summary.stream :as stream])
  (:use seesaw.core
        [hiccup.util :only [escape-html url-encode]])
  (:import javax.swing.JTextPane
           javax.swing.event.HyperlinkListener
           javax.swing.event.HyperlinkEvent
           javax.swing.event.HyperlinkEvent$EventType
           javax.swing.text.html.StyleSheet
           ))

(defmulti visualize (fn [{:keys [system]} step]
                      [(:system system) step]))

(defn search-term
  [term]
  (let [url (str "https://startpage.com/do/search?language=english&cat=web&query=" (url-encode term))]
    (try
      (browse/browse-url url)
      (catch Exception e
        (sh/sh "firefox" url)))))

(defn- html-view
  [listener html]
  (doto (JTextPane.)
    (.setContentType "text/html")
    (.setEditable false)
    (.addHyperlinkListener listener)
    (.setText html)))

(def html-marker-replacements
  ;; A map of replacements for the markers from marker-stream
  ;; Values maybe functions or strings. Special case: The value of
  ;; :token may be a function that takes zero or one arguments. :token
  ;; is the actual sentence part and a map containing :ne, :pos,
  ;; :prev-ne, :prev-pos :prev-token, :token
  {:space                 " "

   :ne-begin-person       "<span style='color: #859900;'><a href='#ne-person'>"
   :ne-end-person         "</a></span>"

   :ne-begin-location     "<span style='color: #dc322f;'><a href='#ne-location'>"
   :ne-end-location       "</a></span>"

   :ne-begin-organization "<span style='color: #1199ff;'><a href='#ne-orga'>"
   :ne-end-organization   "</a></span>"

   :ne-begin-time         "<span style='color: #d33682;'><a href='#ne-time'>"
   :ne-end-time           "</a></span>"

   :ne-begin-money        "<span style='color: #6c71c4;'><a href='#ne-money'>"
   :ne-end-money          "</a></span>"

   :ne-begin-percent      "<span style='color: #2aa198;'><a href='#ne-percent'>"
   :ne-end-percent        "</a></span>"

   :ne-begin-date         "<span style='color: #268bd2;'><a href='#ne-date'>"
   :ne-end-date           "</a></span>"

   :ne-begin-misc         "<span style='color: #6c71c4;'><a href='#ne-misc'>"
   :ne-end-misc           "</a></span>"

   :ne-begin-null         "<b>:null NamedEntity should not exist here!</b>"
   :ne-end-null           ""

   :quote-begin           (escape-html "\"")
   :quote-end             (escape-html "\"")

   :single-quote-begin    (escape-html "'")
   :single-quote-end      (escape-html "'")

   :rb-begin              "("
   :rb-end                ")"

   :token-begin           "<span class='token'>"
   :token-end             "</span>"
   :token                 #(if-not (= (:action %) :skip)
                             (escape-html (:token %)))})

(defn- hyperlink-listener
  [fs]
  (proxy [HyperlinkListener] []
    (hyperlinkUpdate [e]
      (condp = (.getEventType e)
        HyperlinkEvent$EventType/ACTIVATED
        (let [m {:description (.getDescription e)
                 :url         (.getURL e)
                 :src-element (.getSourceElement e)
                 :event-type  (.getEventType e)
                 :text        (let [elem         (.getSourceElement e)
                                    start-offset (.getStartOffset elem)
                                    end-offset   (.getEndOffset elem)]
                                (.. elem
                                    getDocument
                                    (getText start-offset (- end-offset start-offset))
                                    trim))}]
          (doseq [f fs]
            (try
              (f m)
              (catch Exception e
                (binding [*out* *err*]
                  (println e))))))
        nil))))

(defn- result-view
  [system]
  (let [listener (hyperlink-listener [#(search-term (:text %))])
        sent-view (try
                    (html-view
                     listener
                     (reduce #(str %1 "<p>" %2 "</p>")
                             ""
                             (map (comp (partial stream/replace-markers html-marker-replacements)
                                        stream/mark)
                                  (core/sentence-parts system))))
                    (catch AbstractMethodError e
                      (text :multi-line? true
                            :wrap-lines? true
                            :editable? false
                            :text (str/join "\n\n" (core/sentences system)))))]

    [{:title   "Summary"
      :content (scrollable sent-view)}]))

(defmethod visualize :default
  [system step]
  (let [tabs [{:title   "Inspector"
               :content (scrollable (tree :model (inspector/tree-model system)))}]]
    (tabbed-panel :tabs (if (= step :end)
                          (into (result-view system) tabs)
                          tabs))))
