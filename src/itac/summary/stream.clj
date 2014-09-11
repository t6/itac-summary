(ns itac.summary.stream
  (:require [clojure.string :as str]))

(defn- primitive-markers
  "Takes a parsed sentence, removes some CoreNLP oddities and annotates it
  for correctly rebuilding it to a string."
  [sentence]
  (for [{:keys [token ne pos]} sentence]
    {:token token
     :ne ne
     :pos pos
     :action (cond
              (= token "``")                      :quote-begin
              (= token "`")                       :single-quote-begin
              (= token "''")                      :quote-end
              (= token "'")                       :single-quote-end
              (= token "-LRB-")                   :rb-begin
              (= token "-RRB-")                   :rb-end
              ;; (he 's) (do n't) ...
              (not= (.indexOf token (int \')) -1) :concat-prev
              (= pos :null)                       :concat-prev
              (= pos :point)                      :concat-next
              :else                               :null)}))

(defn-
  tag-token
  [prev-ne ne prev-pos pos prev-token m & [action]]
  (let [ne-begin (keyword (str "ne-begin-" (name ne)))
        ne-end   (keyword (str "ne-end-" (name prev-ne)))
        ne-begin (if-not (= :null ne)
                   (if-not (= ne prev-ne)
                     ne-begin))
        ne-end   (if (= :null ne)
                   (if-not (= :null prev-ne)
                     ne-end))
        m        (merge m {:action     action
                           :prev-token prev-token
                           :prev-pos   prev-pos
                           :prev-ne    prev-ne})]
    [ne-begin
     ne-end
     (if (= :null ne) :token-begin)
     m
     (if (= :null ne) :token-end)]))

(defn- stream-markers
  "Takes a seq of primitive-annotate'd sentences and creates a stream of markers.
  The markers are placed at the beginning of important sentence parts. :space markers
  (indicating white space) are inserted where needed. The stream may contain nil entries,
  which will be ignored."
  [annotated-sentence]
  (first
   (reduce (fn [[acc prev-action prev-ne prev-token prev-pos]
               {:keys [token ne pos action] :as m}]
             (let [tag (partial tag-token prev-ne ne prev-pos pos prev-token)]
               [(into
                 acc
                 (condp = action
                   :quote-begin        (into (tag m :skip)
                                             (if (empty? acc)
                                               [:quote-begin]
                                               [:space :quote-begin]))
                   :single-quote-begin (into (tag m :skip)
                                             (if (empty? acc)
                                               [:single-quote-begin]
                                               [:space :single-quote-begin]))
                   
                   :quote-end          (into (tag m :skip)
                                             [:quote-end :space])
                   :single-quote-end   (into (tag m :skip)
                                             [:single-quote-end :space])
                   
                   :rb-begin           (into (tag m :skip)
                                             (if (empty? acc)
                                               [:rb-begin]
                                               [:space :rb-begin]))
                   :rb-end             (into (tag m :skip)
                                             [:rb-end :space])
                   :concat-prev        (tag m)
                   :concat-next        (tag m)
                   (condp = prev-action
                     :quote-begin        (tag m)
                     :single-quote-begin (tag m)
                     :quote-end          (tag m)
                     :single-quote-end   (tag m)
                     :rb-begin           (tag m)
                     :rb-end             (tag m)
                     (into [(if-not (empty? acc) :space)]
                           (tag m)))))
                action ne token]))
           [[] :null :null nil nil]
           annotated-sentence)))

(def text-marker-replacements
  ;; A map of replacements for the markers from marker-stream
  ;; Values maybe functions or strings. Special case: The value of
  ;; :token may be a function that takes zero or one arguments. :token
  ;; is the actual sentence part and a map containing :ne, :pos,
  ;; :prev-ne, :prev-pos :prev-token, :token
  {:space                 " "
   
   :ne-begin-person       ""
   :ne-end-person         ""
   
   :ne-begin-location     ""
   :ne-end-location       ""
   
   :ne-begin-organization ""
   :ne-end-organization   ""

   :ne-begin-time         ""
   :ne-end-time           ""

   :ne-begin-money        ""
   :ne-end-money          ""
   
   :ne-begin-percent      ""
   :ne-end-percent        ""

   :ne-begin-date         ""
   :ne-end-date           ""

   :ne-begin-misc         ""
   :ne-end-misc           ""

   :ne-begin-null         ""
   :ne-end-null           ""
   
   :quote-begin           "\""
   :quote-end             "\""
   
   :single-quote-begin    "'"
   :single-quote-end      "'"

   :rb-begin              "("
   :rb-end                ")"
   
   :token-begin           ""
   :token-end             ""
   :token                 #(if-not (= (:action %) :skip)
                             (:token %))})

(defn- arg-count
  "Returns the arity of f"
  [f]
  ;; Thank you StackOverflow: http://stackoverflow.com/a/1813967
  (let [m (first (.getDeclaredMethods (class f)))
        p (.getParameterTypes m)]
    (alength p)))

(defn replace-markers
  "Replace the markers `xs` from the marker stream using the given
  replacement map `actions`."
  [actions xs]
  (str/join
   ""
   (map (fn [x]
          (when x
            (if-let [action (actions x (:token actions nil))]
              (if (fn? action)
                (if (zero? (arg-count action))
                  (action)
                  (action x))
                action)))) xs)))

(def mark
  (comp stream-markers
        primitive-markers))
