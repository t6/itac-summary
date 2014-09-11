(ns itac.textrank.core
  (:require [clojure.string :as string])
  (:import [edu.uci.ics.jung
            graph.UndirectedSparseGraph
            graph.DirectedSparseGraph
            graph.util.Pair
            graph.util.EdgeType] 
           [org.apache.commons.collections15
            Transformer
            functors.MapTransformer]
           itac.textrank.TextRank))

(defn- vertex-scores
  [t edges vertices]
  {:textrank  t
   :iteration (.getIterations t)
   :edges     edges
   :vertices  (reduce (fn [m [vertex prior]]
                        (assoc m vertex (.getVertexScore t vertex)))
                      {}
                      vertices)})

(defn graph-map->jung-graph
  [{:keys [vertices edges edge-type]}]
  (let [j-edge-type (case edge-type
                      :directed EdgeType/DIRECTED
                      EdgeType/UNDIRECTED)
        j-graph     (case edge-type
                      :directed (DirectedSparseGraph.)
                      (UndirectedSparseGraph.))
        _           (doseq [[v _] vertices]
                      (.addVertex j-graph v))
        _           (doseq [[[v1 v2] _] edges]
                      (.addEdge j-graph [v1 v2] (Pair. v1 v2) j-edge-type))]

    j-graph))

(defn textrank
  "Given a graph with edge weights and vertex prior probabilities
   returns for all iterations of TextRank the graph with updated
   probabilities.
   
   The TextRank object will be returned as part of the graph
   (key :textrank).

   The graph should be in the form:
   {:vertices {<vertex> <vertex prior probability>, ...}
    :edges {[<vertex1> <vertex2>] <edge weight>, ...}
    :edge-type :undirected ;; or :directed, defaults to :undirected
                           ;; if not specified
   }

   You can specify the following optional parameters:
      steps:     If non-nil only iterate for steps iterations, returns
                 a lazy-seq of each step. Note that the start state is included, so
                 you'll get steps+1 states.
      damping:   If non-nil sets the damping factor used by TextRank.
      tolerance: If non-nil sets the convergence threshold."
  [{:keys [vertices edges edge-type] :as graph-map} & {:keys [steps damping tolerance]
                                                       :or   {steps     nil
                                                              damping   0.85
                                                              tolerance 0.0001}}]
  (when-not (empty? graph-map)
    (let [j-graph (graph-map->jung-graph graph-map)
          ;; TextRank uses Transformer objects instead of maps
          priors  (MapTransformer/getInstance vertices)
          weights (MapTransformer/getInstance edges)
          
          t       (TextRank. j-graph weights priors damping)
          _       (.setTolerance t tolerance)
          _       (when steps (.setMaxIterations t steps))
          
          start   (vertex-scores t edges vertices)]
      (.step t)
      (loop [iterations [(vertex-scores t edges vertices) start]]
        (if-not (.done t)
          (do (.step t)
              (recur (cons (vertex-scores t edges vertices)
                           (lazy-seq iterations))))
          iterations)))))
