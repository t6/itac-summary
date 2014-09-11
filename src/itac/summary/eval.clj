(ns itac.summary.eval
  (:require [itac.rouge.dataset :as dataset]
            [itac.summary.core :as core]
            [clojure.java.io :as io]
            [clojure.inspector :as inspector]
            [seesaw.core :as seesaw]
            
            itac.summary.textrank
            itac.summary.baseline
            itac.summary.frequencies
            itac.summary.select
            itac.summary.smmry-com
            itac.summary.fragments

            :reload)
  (:gen-class))

(defn evalfn
  [opts]
  (fn [n text]
    (core/sentences (core/summarize (assoc opts
                                      :reduction n) text))))

(def system-definitions
  [[:select              {:system      :select
                          :description "ROUGE-1 basierte Auswahl der besten Zusammenfassung"}]
   [:textrank-ne-cluster {:system              :textrank
                          :graph-builder       :paper
                          :sentence-similarity :ne-cluster-intersection
                          :description         "TextRank mit Berücksichtung von Named Entities und Coreferences"}]
   
   [:textrank            {:system              :textrank
                          :graph-builder       :paper
                          :sentence-similarity :token-intersection
                          :description         "TextRank"}]

   [:textrank-ne         {:system              :textrank
                          :graph-builder       :paper
                          :sentence-similarity :ne-intersection
                          :description         "TextRank mit Berücksichtung von Named Entities"}]

   [:baseline            {:system      :baseline
                          :description "Baseline: Pauschal die ersten Sätze übernehmen"}]
   
   [:frequencies         {:system      :frequencies
                          :description "Lemma-Häufigkeiten"}]

   [:fragments           {:system      :fragments
                          :description "Nur Satzfragmente extrahieren"}]
   
   [:smmry-com           {:system      :smmry-com
                          :description "SMMRY.COM"}]])

(defn system-eval-fns
  []
  (into (sorted-map)
        (for [[system options] system-definitions]
          {system (evalfn options)})))

(defn run
  ([]
     (run (dataset/read (io/resource "itac/rouge/newsround.json"))))
  ([data]
     (let [systems (system-eval-fns)]
       (dataset/eval-frame data systems))))

(defn -main
  ([]
     (seesaw/config! (run) :on-close :exit)
     nil)
  ([args]
     (-main)))
