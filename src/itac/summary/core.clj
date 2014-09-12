(ns itac.summary.core
  (:require [plumbing.core :refer (letk defnk)]
            [clojure.string :as str]
            [t6.snippets.core :as snippets]
            [t6.snippets.nlp :as nlp]
            t6.snippets.nlp.corenlp))

(def pipeline (nlp/pipeline {:type :corenlp}))

(defn print-lines [coll]
  (dorun (map-indexed (fn [i x] (println (str (inc i) \. " " x))) coll)))
 
(defnk sentence-map->parts
  [tokens lemmas nes pos :as sentence-map]
  (let [nes (map (comp #(if (= % :o) :null %)
		       keyword
		       str/lower-case) nes)
	pos (map (comp keyword
		       #(if (#{":" "." "," "!" "?"} %1) "point" %1)
		       str/lower-case) pos)]
    (merge sentence-map
           {:nes            nes
            :pos            pos
            :sentence-parts
            (vec (map
                  (fn [token lemma ne pos-tag]
                    {:token token
                     :lemma lemma
                     :ne    ne
                     :pos   pos-tag})
                  tokens lemmas nes pos))})))

(defn sentence-maps
  [annotation]
  ;; sentence maps were the predecessor of the token and sentences maps
  ;; of snippets, we adapt them to snippet's model here
  (letk [[sentences tokens] annotation]
    (->> (map (fn [sentence tokens]
                {:sentence (:text sentence)
                 :index (:index sentence)
                 :tokens (mapv :token tokens)
                 :lemmas (mapv :lemma tokens)
                 :nes (mapv :ne tokens)
                 :pos (mapv :tag tokens)
                 :token-spans (mapv :span tokens)
                 :span (:span sentence)})
             sentences tokens)
        (mapv sentence-map->parts))))

(defnk sentence-clusters
  "returns a set of sentence clusters."
  [coreferences]
  (set (for [coref-chain (vals coreferences)
             :let [sentence-clusters (set (map :sentence coref-chain))]
             ;; no 1 element clusters
             :when (> (count sentence-clusters) 1)]
         sentence-clusters)))

(defn annotate-text
  [text]
  (snippets/create {:pipeline pipeline :text text}))

(defprotocol SummarySystem
  (annotate [this])
  (simplify [this])
  (rank [this])
  (reconstruct [this]))

(defprotocol SummaryOutput
  ;; Return a seq of sentences that make up the summary.
  (sentences [this])
  
  ;; Return a seq of the sentence parts that make up the summary
  (sentence-parts [this]))

(defmulti construct :system)

(defmulti summary-length (fn [{:keys [system]}] (:length system)))

(defmethod summary-length :default
  [{:keys [system maps length]}]
  (let [factor (if (:reduction system)
                 (/ (- 100 (:reduction system)) 100.0)
                 1/3)
        length (int (* factor (if length length (count maps))))]
    (if-not (zero? length)
      length
      1)))

(defn summarize
  [system text]
  (some-> (construct system text)
          annotate
          simplify
          rank
          reconstruct))
