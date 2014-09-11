(ns itac.summary.coref
  (:require [clojure.set :as set]
            [clojure.walk :as walk]
            [t6.snippets.core :as snippets]))

(defn sentence-clusters
  "returns a set of sentence clusters."
  [corefs]
  (set (for [coref-chain (vals corefs)
             :let [sentence-clusters (set (map :sentence coref-chain))]
             ;; no 1 element clusters
             :when (> (count sentence-clusters) 1)]
         sentence-clusters)))
