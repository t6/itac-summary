(ns itac.textrank.ui
  (:require seesaw.core
            seesaw.table
            [itac.textrank.core :as core]
            [clojure.string :as string])
  (:import [edu.uci.ics.jung
            algorithms.layout.Layout
            algorithms.layout.CircleLayout
            visualization.VisualizationViewer
            visualization.GraphZoomScrollPane
            visualization.control.DefaultModalGraphMouse
            visualization.control.CrossoverScalingControl]
           [org.apache.commons.collections15
            Transformer
            functors.MapTransformer]
           javax.swing.table.TableRowSorter))

(def test-graph {:edge-type :undirected
                 ;; Nodes/vertices with their prior probabilites
                 :vertices  {:this 1.0
                             :is   1.0
                             :a    1.0
                             :test 1.0}
                 ;; Edges as vertex pairs with their weights
                 :edges     {[:this :is]   0.1
                             [:is :a]      1.0
                             [:a :test]    0.4
                             [:this :test] 0.4
                             [:is :test]   4.7}})

(defn- graph-map->vertex-table-model
  [vertex->num {:keys [vertices edges edge-type]}]
  [:columns [{:key 0 :text "#"}
             {:key 1 :text "probability"}
             {:key 2 :text "description"}]
   :rows    (vec (for [[vertex probability] (sort vertices)]
                   [(vertex->num vertex) probability vertex]))])

(defn- graph-map->edge-table-model
  [vertex->num {:keys [vertices edges edge-type]}]
  [:columns [{:key 0 :text "v1"}
             {:key 1 :text "v2"}
             {:key 2 :text "weight"}]
   :rows    (for [[[v1 v2] weight] (sort edges)]
              [(vertex->num v1) (vertex->num v2) weight])])


(defn visualize
  "Opens a window visualizing the current graph state"
  [textrank-seq]
  (let [;; Realize every possible textrank iteration
        textrank-vec          (vec (reverse textrank-seq))
        
        graph                 (core/graph-map->jung-graph (first textrank-vec))
        
        vertex->num           (into {} (map (fn [[vertex _] i] {vertex i})
                                            (sort (:vertices (first textrank-vec)))
                                            (range)))
        
        layout                (CircleLayout. graph)
        _                     (.setVertexOrder layout (comparator #(> (vertex->num %)
                                                                      (vertex->num %2))))
        scaler                (CrossoverScalingControl.)

        server                (VisualizationViewer. layout)
        _                     (.scaleToLayout server scaler)
        _                     (.setGraphMouse server (DefaultModalGraphMouse.))

        ;; TODO: Edge and Vertex tooltips
        
        repaint-watch         (fn [key ref old-val new-val]
                                (.repaint server))

        show-scores           (atom false)
        _                     (add-watch show-scores nil
                                         repaint-watch)

        show-weights          (atom false)
        _                     (add-watch show-weights nil
                                         repaint-watch)

        weight-precision      (atom 5 :validator #(> % 0))
        probability-precision (atom 5 :validator #(> % 0))
        
        iteration-slider      (seesaw.core/slider
                               :value              0
                               :min                0
                               :max                (dec (count textrank-vec))
                               :snap-to-ticks?     true
                               :paint-ticks?       true
                               :minor-tick-spacing 1
                               :major-tick-spacing 10)
        
        _                     (doto (.getRenderContext server)
                                (.setVertexLabelTransformer
                                 (reify Transformer
                                   (transform [this v]
                                     (if @show-scores
                                       (format (str "%s [%." @probability-precision "f]")
                                               (vertex->num v)
                                               (get-in (nth textrank-vec (.getValue iteration-slider))
                                                       [:vertices v])
                                               #_(.getVertexScore textrank-obj v))
                                       (str (vertex->num v))))))

                                (.setEdgeLabelTransformer
                                 (reify Transformer
                                   (transform [this e]
                                     (if @show-weights
                                       (format (str "%." @weight-precision "f")
                                               (get-in (nth textrank-vec (.getValue iteration-slider))
                                                       [:edges e]))
                                       "")
                                     )))) 
        
        vertex-table          (seesaw.core/table)
        edge-table            (seesaw.core/table
                               ;; Edge weights don't change...
                               :model (apply seesaw.table/table-model
                                             (graph-map->edge-table-model
                                              vertex->num
                                              (first textrank-vec))))
        _                     (.setRowSorter edge-table
                                             (TableRowSorter. (.getModel edge-table)))
        _                     (doto (.getColumnModel edge-table)
                                (-> (.getColumn 0)
                                    (.setMaxWidth 35))
                                (-> (.getColumn 1)
                                    (.setMaxWidth 35)))

        iteration-lbl         (seesaw.core/label)
        
        update                #(try (let [step      (.getValue iteration-slider)
                                          graph-map (nth textrank-vec step)
                                          vertex-model (apply seesaw.table/table-model
                                                              (graph-map->vertex-table-model
                                                               vertex->num
                                                               graph-map))]
                                      (.setValue iteration-slider step)
                                      (.setText iteration-lbl (format "Iteration %s/%s"
                                                                      step
                                                                      (dec (count textrank-vec))))
                                      (doto vertex-table
                                        (.setModel vertex-model)
                                        (.setRowSorter (TableRowSorter. vertex-model)))
                                      (doto (.getColumnModel vertex-table)
                                        (-> (.getColumn 0)
                                            (.setMaxWidth 35))
                                        (-> (.getColumn 1)
                                            (.setMaxWidth 200))
                                        (-> (.getColumn 1)
                                            (.setMinWidth 200)))
                                      (.repaint server))
                                    (catch IllegalStateException e
                                      (seesaw.core/alert "No more steps!")))

        zoom-in-action        (seesaw.core/action
                               :handler (fn [e] (.scale scaler server 1.1 (.getCenter server)))
                               :name "+")

        zoom-out-action       (seesaw.core/action
                               :handler (fn [e] (.scale scaler server (/ 1 1.1) (.getCenter server)))
                               :name "-")
        
        prev-action           (seesaw.core/action
                               :handler (fn [e]
                                          (.setValue iteration-slider (dec (.getValue iteration-slider)))
                                          (update))
                               :name    "Previous"
                               :tip     "Show previous iteration")
        
        next-action           (seesaw.core/action
                               :handler (fn [e]
                                          (.setValue iteration-slider (inc (.getValue iteration-slider)))
                                          (update))
                               :name    "Next"
                               :tip     "Show next iteration")
        
        _                     (seesaw.core/listen
                               iteration-slider
                               :change (fn [e]
                                         (let [src   (.getSource e)
                                               value (.getValue src)]
                                           (when-not (.getValueIsAdjusting src)
                                             (update)))))
        panel                 (seesaw.core/border-panel
                               :north  (seesaw.core/toolbar
                                        :items [prev-action
                                                next-action
                                                (seesaw.core/separator)
                                                iteration-lbl
                                                iteration-slider
                                                zoom-in-action
                                                zoom-out-action])
                               :center (seesaw.core/left-right-split
                                        (seesaw.core/scrollable edge-table)
                                        (seesaw.core/top-bottom-split
                                         (seesaw.core/scrollable vertex-table)
                                         (GraphZoomScrollPane. server)
                                         )))]
    (update)
    panel))

(defn visualize-frame
  [textrank-seq]
  (-> (seesaw.core/frame
       :title   "TextRank"
       :content (visualize textrank-seq))
      seesaw.core/pack!
      seesaw.core/show!))
