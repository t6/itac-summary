(ns itac.summary.textrank
  (:require [clojure.set :as set]
            [clojure.string :as str]
            [clojure.math.combinatorics :as combo]
            [clojure.java.io :as io]
            [itac.rouge :as rouge]
            [itac.textrank.core :as textrank]

            [itac.summary.stream :as stream]
            [itac.summary.core :as core]
            [itac.summary.frequencies :as freqs]
            [itac.summary.coref :as coref]))

(def stopwords
  (set/union (set (line-seq (io/reader (io/resource "itac/rouge/rouge-1.5.5/data/smart_common_words.txt"))))
             (set (line-seq (io/reader (io/resource "itac/summary/stopwords"))))))

(defmulti sentence-similarity
  (fn [x _ _] (get-in x [:system :sentence-similarity])))

(defmulti word-similarity
  (fn [x _ _] (get-in x [:system :word-similarity])))

(defmulti build-graph
  (fn [{:keys [system]}] (:graph-builder system)))

(defn- upcase-token
  [token]
  (str (Character/toUpperCase (nth token 0)) (subs token 1)))

(defn- remove-coordinating-conjunctions
  [{:keys [tokens nes lemmas sentence-parts pos] :as sentence}]
  (if (= (first pos) :cc)
    (let [token (upcase-token (second tokens))
          part  (assoc (first (next (next sentence-parts)))
                  :token token)]
      (assoc sentence
        :sentence-parts (cons part (next (next sentence-parts)))
        :tokens         (cons token (next (next tokens)))
        :nes            (next nes)
        :pos            (next pos)
        :lemmas         (next lemmas)))
    sentence))

(defn- reconstruct-sentence-from-tokens
  [{:keys [tokens sentence-parts] :as sentence-map}]
  (assoc sentence-map
    :sentence (->> sentence-parts
                  stream/mark
                  (stream/replace-markers stream/text-marker-replacements))))

(defrecord TextRank [text system maps length graph corefs clusters textrank-iterations]
  core/SummarySystem
  (annotate [this]
    (let [sentence-maps (core/sentence-maps text)
          corefs        (coref/get-coreferences text)]
      (assoc this
        :length   (count sentence-maps)
        ;; Sätzen auch einen Index zuweisen
        :maps     (map-indexed #(assoc %2 :index %1)
                               sentence-maps) 
        :corefs   corefs 
        :clusters (coref/sentence-clusters corefs))))

  (simplify [this]
    (let [n            (core/summary-length this)
          updated-maps (->> maps
                            (remove (fn [{:keys [tokens]}]
                                      (let [s (set tokens)]
                                        (or (s "``")
                                            (s "''")))))
                            (filter #(> (count (:tokens %)) 3)))]
      (assoc this
        :maps (if (<= n (count updated-maps))
                updated-maps
                maps))))
  
  (rank [this]
    (let [res        (textrank/textrank (build-graph this))
          vertices   (:vertices (first res))
          assoc-rank #(assoc % :rank (vertices (:sentence %)))]

      (assoc this
        :maps (vec (map assoc-rank maps))
        :textrank-iterations res)))
  
  (reconstruct [this]
    (let [n         (core/summary-length this)
          sentences (->> (sort-by :rank maps)
                         reverse
                         (take n)
                         ;; Die Satzreihenfolge soll so sein,
                         ;; wie sie auch im Originaltext war.
                         (sort-by :index)
                         ;; Alle Coordinating Conjunctions, die am Satzanfang stehen
                         ;; können vermutlich bedenkenlos entfernt werden.
                         (map remove-coordinating-conjunctions)
                         ;; Sätze aus den Tokens wieder aufbauen
                         (map reconstruct-sentence-from-tokens))]
      (assoc this
        :maps sentences)))

  core/SummaryOutput
  (sentences [this]
    (map :sentence maps))

  (sentence-parts [this]
    (map :sentence-parts maps)))

(defmethod core/construct :textrank
  [system text]
  (map->TextRank
   {:text     text
    :system   (merge {:graph-builder       :paper
                      :sentence-similarity :token-intersection
                      :word-similarity     :lin-similarity}
                     system)
    :maps     nil
    :graph    nil
    :corefs   nil
    :clusters nil
    :ranked   nil}))

(defmethod sentence-similarity :token-intersection
  [_ {part-1 :sentence-parts sent-1 :sentence} {part-2 :sentence-parts sent-2 :sentence}]
  (let [tokens-1   (set (map :token part-1))
        tokens-2   (set (map :token part-2))
        
        tokens     (set/intersection tokens-1 tokens-2)

        normal     (+ (Math/log (count tokens-1))
                      (Math/log (count tokens-2)))

        similarity (/ (count tokens)
                      normal)]
    similarity))

(defmethod sentence-similarity :rouge
  [_ {sent-1 :sentence} {sent-2 :sentence}]
  (let [result (rouge/rouge [{:peer-summary    [sent-2]
                              :model-summaries [{:summary [sent-1]}]}]
                            {:no-rouge-l       false
                             :stemming         true
                             :remove-stopwords true})]
    (:f-measure (first result))))

(defmethod build-graph :paper
  [{:keys [maps text system] :as this}]
  (let [;; a vertex is represented by an entire sentence
        vertices (into {} (for [{:keys [sentence nes]} maps]
                            {sentence 1.0}))
        ;; The (undirected) edges are 2-combinations of all sentences (vertices)
        edges    (into {} (for [[{sent-1 :sentence part-1 :sentence-parts :as sentence-map-1}
                                 {sent-2 :sentence part-2 :sentence-parts :as sentence-map-2}]
                                (combo/combinations maps 2)
                                :let [sim (sentence-similarity this
                                                               sentence-map-1
                                                               sentence-map-2)]
                                :when (> sim 0.0)]
                            {[sent-1 sent-2] sim}))]
    {:vertices  vertices
     :edges     edges
     :edge-type :undirected}))

(defn- mentions
  [nes]
  (remove (comp #{:null} first)
          (partition-by identity nes)))

(defn- mention
  [nes]
  (count (reduce concat (mentions nes))))

(defmethod sentence-similarity :ne-intersection
  [{:keys [maps]} sentence-map-1 sentence-map-2]
  (let [ne-cnt       (count (mapcat (comp mentions :nes) maps))
        
        ;; Gibt die Tokens einer Sentence map ohne Stopwords zurück
        tokens       #(set/select (complement stopwords)
                                  (set (:tokens %)))
        tokens-1     (tokens sentence-map-1)
        tokens-2     (tokens sentence-map-2)
        token-cnt    (count (set/intersection tokens-1 tokens-2))
        token-normal (+ (Math/log (count tokens-1))
                        (Math/log (count tokens-2)))]
      (+ (if-not (zero? token-normal)
           (/ token-cnt token-normal)
           0)
         (if-not (zero? ne-cnt)
           (/ (+ (mention (:nes sentence-map-1))
                 (mention (:nes sentence-map-2)))
              (* 2.0 ne-cnt))
           0))))

(defmethod sentence-similarity :ne-cluster-intersection
  [{:keys [maps clusters]} sentence-map-1 sentence-map-2]
  (let [ne-cnt         (count (mapcat (comp mentions :nes) maps))
        
        ;; Wieviele coreferences hat eine Sentence map?
        cluster        (fn [sent] (set (filter #(% (inc (:index sent))) clusters)))
        cluster-1      (cluster sentence-map-1)
        cluster-2      (cluster sentence-map-2)
        cluster-cnt    (count (set/intersection cluster-1 cluster-2))
        cluster-normal (+ (count cluster-1) (count cluster-2))
        
        ;; Gibt die Tokens einer Sentence map ohne Stopwords zurück
        tokens         #(set/select (complement stopwords) (set (:tokens %)))
        tokens-1       (tokens sentence-map-1)
        tokens-2       (tokens sentence-map-2)
        token-cnt      (count (set/intersection tokens-1 tokens-2))
        token-normal   (+ (Math/log (count tokens-1))
                          (Math/log (count tokens-2)))]
      (+ (if-not (zero? token-normal)
           (/ token-cnt token-normal)
           0)
         (if-not (zero? ne-cnt)
           (/ (+ (mention (:nes sentence-map-1))
                 (mention (:nes sentence-map-2)))
              (* 2.0 ne-cnt))
           0)
         (if-not (zero? cluster-normal)
           (/ cluster-cnt
              (+ (count cluster-1)
                 (count cluster-2)))
           0))))
