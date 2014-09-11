(ns itac.summary.ui.textrank
  (:require [clojure.inspector :as inspector]
            [itac.textrank.ui :as textrank-ui]
            [itac.summary.ui.core :as ui])
  (:use seesaw.core))

(defmethod ui/visualize [:textrank :rank]
  [system step]
  (tabbed-panel :tabs [{:title   "Inspector"
                        :content (scrollable (tree :model (inspector/tree-model system)))}
                       {:title "TextRank"
                        :content (textrank-ui/visualize (:textrank-iterations system))}])) 
