(ns itac.summary.frequencies
  (:require [itac.summary.core :as core]))

(defrecord Frequencies [text system maps simplified-maps]
  core/SummarySystem
  (annotate [this]
    (assoc this :maps (core/sentence-maps (core/annotate-text text))))

  (simplify [this]
    (letfn [(filter-pos [f sentence-map]
              (update-in sentence-map [:sentence-parts]
                         (partial filter #(-> % :pos f))))

            (remove-lemmas [lemmas sentence-map]
              (update-in sentence-map [:sentence-parts]
                         (partial remove #(-> % :lemma lemmas))))]

      (assoc this
        :simplified-maps (map (comp (partial
                          filter-pos #{:vb :vbd :vbp :vbg :vbn :vbz
                                       :nn :nns :nnp :nnps})
                         (partial
                          remove-lemmas #{"be" "have"}))
                   maps))))

  (rank [this]
    (let [lemma-freq (->> simplified-maps
                          (mapcat (comp (partial map :lemma)
                                        :sentence-parts))
                          frequencies)]
      (assoc this
        :maps (for [sentence-map maps]
                (assoc sentence-map
                  :rank (reduce + (map #(lemma-freq (:lemma %) 0)
                                       (:sentence-parts sentence-map))))))))

  (reconstruct [this]
    (let [n (core/summary-length this)]
      (assoc this
        :maps (->> maps
                   (sort-by :rank)
                   reverse
                   (take n)))))

  core/SummaryOutput
  (sentences [this]
    (map :sentence maps))
  (sentence-parts [this]
    (map :sentence-parts maps)))

(defmethod core/construct :frequencies
  [system text]
  (map->Frequencies
         {:text     text
          :system   (merge {:n 5} system)}))
