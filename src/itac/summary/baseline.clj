(ns itac.summary.baseline
  (:require [clojure.walk :as walk]
            [itac.summary.core :as core]))

(defrecord Baseline [text system maps]
  core/SummarySystem
  (annotate [this]
    (assoc this :maps (core/sentence-maps text)))
  
  (simplify [this] this)
  
  (rank [this]
    (assoc this
      :maps (map-indexed
             (fn [i sentence-map]
               (assoc sentence-map
                 :rank i))
             maps)))

  (reconstruct [this]
    (let [n (core/summary-length this)]
      (assoc this
        :maps (take n (sort-by :rank maps)))))

  core/SummaryOutput
  (sentences [this]
    (map :sentence maps))

  (sentence-parts [this]
    (map :sentence-parts maps)))

(defmethod core/construct :baseline
  [system text]
  (merge (Baseline. nil nil nil)
         {:text     text
          :system   (merge {:n 5} system)
          :maps     nil}))
