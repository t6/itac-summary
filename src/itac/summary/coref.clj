(ns itac.summary.coref
  (:require [clojure.set :as set]
            [clojure.walk :as walk]
            [itac.service.client :as service]
            [itac.summary.frequencies :as freqs])
  (:import [edu.stanford.nlp.dcoref
            Dictionaries$MentionType
            Dictionaries$Gender
            Dictionaries$Animacy
            Dictionaries$Number]))

(def dev-test-string "Hello. This is Bruce. He walks. How are you?")

(defn- mention-to-map
  [mention]
  {:animacy          (condp = (.animacy mention)
                       Dictionaries$Animacy/ANIMATE :animate
                       Dictionaries$Animacy/INANIMATE :inanimate
                       :unknown)
   :coref-cluster-id (.corefClusterID mention)
   :end-index        (.endIndex mention)
   :gender           (condp = (.gender mention)
                       Dictionaries$Gender/FEMALE :female
                       Dictionaries$Gender/MALE :male
                       Dictionaries$Gender/NEUTRAL :neutral
                       :unknown)
   :head-index       (.headIndex mention)
   :mention-id       (.mentionID mention)
   :mention-span     (.mentionSpan mention)
   :mention-type     (condp = (.mentionType mention)
                       Dictionaries$MentionType/PRONOMINAL :pronominal
                       Dictionaries$MentionType/NOMINAL :nominal
                       :proper)
   :number           (condp = (.number mention)
                       Dictionaries$Number/PLURAL :plural
                       Dictionaries$Number/SINGULAR :singular
                       :unknown)
   :position         (-> mention .position .elems vec)
   :sent-num         (.sentNum mention)
   :start-index      (.startIndex mention)})

(defn get-coreferences
  [text]
  "Resolves all coreferences and returns a seq of seqs with all coref groups."
  (let [coref-chain (service/coref-chain text)]
    (reduce (partial merge-with conj)
            (for [[chain-id chain] coref-chain
                  :let [mentions (map mention-to-map
                                      (.getMentionsInTextualOrder chain))]]
              {chain-id (vec mentions)}))))

(defn sentence-clusters
  [corefs]
  "returns a set of sentence clusters."
  (set (for [coref-chain (vals corefs)
             :let [sentence-clusters (set (map :sent-num coref-chain))]
             ;; no 1 element clusters
             :when (> (count sentence-clusters) 1)]
         sentence-clusters)))
