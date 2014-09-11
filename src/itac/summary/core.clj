(ns itac.summary.core
  (:require [clojure.walk :as walk]
            [itac.service.client :as service]))

(defn print-lines [coll]
  (dorun (map-indexed (fn [i x] (println (str (inc i) \. " " x))) coll)))
 
(defn- sentence-map->parts
  [sentence-map]
  (let [sentence-map                    (walk/keywordize-keys sentence-map)
        {:keys [tokens lemmas nes pos]} sentence-map
        nes                             (map #(keyword (.toLowerCase (.name %))) nes)
        pos                             (map #(keyword (.toLowerCase (.name %))) pos)]
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
  [text]
  (vec (map sentence-map->parts (service/sentence-maps text))))

(defprotocol SummarySystem
  (annotate [this])
  (simplify [this])
  (rank [this])
  (reconstruct [this]))

(defprotocol SummaryOutput
  ;; Return a seq of sentences that make up the summary.
  (sentences [this])
  
  ;; Return a seq of the sentence parts that make up the summary
  (sentence-parts [this])
  
  ;; Return a seq with pairs [sentence temperature]
  ;; make sure that the "hottest" sentence (with the hottest
  ;; temperature) is also the most important sentence.
  ;; Note that this should NOT be ordered by temperature. The sentence
  ;; ordering is most likely different than that which is implied by sentence
  ;; importance.
  (heatmap [this]))

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

(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!"))
