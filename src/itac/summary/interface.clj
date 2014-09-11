(ns itac.summary.interface
  (:require [clojure.java.io :as io]
            [itac.summary.core :as core]
            [itac.summary.eval :as eval]
            [itac.rouge.dataset :as dataset]
            [itac.summary.ui.pipeline :as pipeline]
            :reload-all))

(defn summarize
  ([system text]
     (summarize system text nil))
  ([system_ text percent]
     (if percent
       (if-not (and (<= percent 100) (>= percent 0))
         (throw (IllegalArgumentException. (str "Reduction has to be between 0 % and 100 % !")))))
     (let [systems (into {} eval/system-definitions)]
       (if-let [system (systems (keyword system_))]
         (core/sentences
          (core/summarize (if percent
                            (assoc system :reduction percent)
                            system)
                          text))
         (throw (IllegalArgumentException. (str "System " system_ " not found!")))))))

(defn systems
  []
  (map (comp name first) eval/system-definitions))

(defn pipeline-panel
  ([]
     (pipeline/pipeline eval/system-definitions))
  ([text]
     (pipeline/pipeline eval/system-definitions text)))

(defn rouge
  ([]
     (rouge (io/resource "itac/rouge/newsround.json")))
  ([dataset-rdr]
     (let [eval-fns (eval/system-eval-fns)]
       (dataset/eval (dataset/read dataset-rdr) eval-fns))))
