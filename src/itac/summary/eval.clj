(ns itac.summary.eval)

(def system-definitions
  [[:textrank-ne-cluster {:system              :textrank
                          :graph-builder       :paper
                          :sentence-similarity :ne-cluster-intersection
                          :description         "TextRank + Named Entities + Coreferences"}]
   
   [:textrank            {:system              :textrank
                          :graph-builder       :paper
                          :sentence-similarity :token-intersection
                          :description         "TextRank"}]

   [:textrank-ne         {:system              :textrank
                          :graph-builder       :paper
                          :sentence-similarity :ne-intersection
                          :description         "TextRank + Named Entities"}]

   [:baseline            {:system      :baseline
                          :description "Baseline: take first n sentences"}]
   
   [:frequencies         {:system      :frequencies
                          :description "Lemma frequencies"}]])
