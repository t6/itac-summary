(ns itac.summary.ui.pipeline
  (:require [clojure.inspector :as inspector]
	    [clojure.java.io :as io]
	    [clojure.string :as str]
	    [clojure.set :as set]
	    [itac.summary.core :as core]
	    [itac.summary.eval :as eval]
	    [itac.summary.ui.core :as ui]
	    [itac.scraper.core :as scraper]
	    itac.summary.textrank
	    itac.summary.ui.textrank
	    itac.summary.baseline
	    itac.summary.frequencies)
  (:use itac.summary.ui.utils
	seesaw.core
	seesaw.color
	seesaw.cells
	seesaw.mig
	seesaw.chooser
	seesaw.graphics))

(defn- string-renderer
  "See https://github.com/daveray/seesaw/issues/8"
  [f]
  (default-list-cell-renderer
    (fn [this {:keys [value]}]
      (.setText this (str (f value))))))

(defn- draw-pipeline
  [state c g2d]
  (let [{:keys [current
		steps
		next-steps]}
	@state

	done-steps           (set/intersection (set steps)
					       (set next-steps))
	last-step            (last steps)
	w                    (width c)
	h                    (height c)
	stroke-w             2

	;; Color of active start node
	start-style          (style :background "#859900"
				    :foreground :black
				    :stroke     stroke-w
				    :cap        :round)
	;; Color of active end node
	end-style            (style :background "#dc322f"
				    :foreground :black
				    :stroke     stroke-w
				    :cap        :round)

	;; Color of the current state
	current-style        (style :foreground "#268BD2"
				    :background :white
				    :stroke     stroke-w
				    :cap        :round)
	;; Color of a previous (a seen inactive) state
	active-style         (style :foreground :black
				    :background :white
				    :stroke     stroke-w
				    :cap        :round)
	;; Color of an unseen inactive state
	inactive-style       (style :foreground "#958B8B"
				    :background :white
				    :stroke     stroke-w
				    :cap        :round)
	divider-style        (update-style inactive-style
					   :foreground "#dc322f"
					   :stroke (stroke
						    :width stroke-w
						    :cap :butt
						    :join :miter
						    :dashes [10]))

	border-w             25
	start-y              20
	rect-w               150
	rect-x               (- (/ w 2) (/ rect-w 2))
	rect-h               50
	circle-r             10
	circle-x             (+ rect-x (/ rect-w 2))
	margin               40

	dividing-line        (fn [y]
			       "Draws a dividing (dashed and horizontal) line at height y."
			       [(line (- rect-x 20) y (+ rect-x rect-w 20) y) divider-style])

	arrow                (fn [y1 y2 step]
			       "Draws a vertical arrow between the Boxes at heights y1 and y2. The line style is selected based on the current step."
			       [(draw-arrow (+ rect-x (/ rect-w 2)) y1
					    (+ rect-x (/ rect-w 2)) y2)
				(cond
				 (= step :start) inactive-style
				 (done-steps step) inactive-style
				 :else active-style)])

	circle               (fn [y step label]
			       "Draws a circle in height y. The style is selected based on the current step. label while be drawn at the right of the circle."
			       [(labeled-circle circle-x y circle-r (+ circle-r 20) label)
				(cond
				 (= current step :start) start-style
				 (= current step :end) end-style
				 (= step :end) inactive-style
				 :else active-style)])

	rect                 (fn [y step label]
			       "Draws a rectangle in height y. The style is selected based on the current step. The label is centered inside the rectangle."
			       [(labeled-rect rect-x y rect-w rect-h label)
				(cond
				 (= current step) current-style
				 (not (done-steps step)) active-style
				 :else inactive-style)])

	state-box            (fn [i step]
			       "Helper function to draw a state box with the appropriate style. Also draws a dividing line between the current and next states."
			       (let [y     (+ start-y circle-r margin (* i (+ rect-h margin)))
				     label (name step)]
				 (concat
				  (arrow (- y margin) y step)

				  (if (= step :end)
				    (circle (+ circle-r y) :end "End")
				    (rect y step label))

				  (if (and (= current step)
					   (not= step :end))
				    (dividing-line (+ y rect-h (/ margin 2)))))))]
    (anti-alias g2d)
    (->> steps
	 (map-indexed state-box)
	 (apply concat)
	 (concat (arrow (+ circle-r start-y) (+ margin circle-r start-y) current)
		 (circle start-y :start "Start")
		 (if (= current :start) (dividing-line (+ start-y circle-r (/ margin 2)))))
	 (apply draw g2d))))

(defn- draw-logo
  [logo c g2d]
  (draw g2d
	(->> logo io/resource icon (draw-centered-image c))
	(style)))

(defn- pipeline-transition
  "Returns the next step and the next action"
  [system reduction text current]
  ({:start       [:construct
		  (fn [_]
		    (core/construct (assoc system :reduction reduction)
				    text))]
    :construct   [:annotate    core/annotate]
    :annotate    [:simplify    core/simplify]
    :simplify    [:rank        core/rank]
    :rank        [:reconstruct core/reconstruct]
    :reconstruct [:end         identity]
    :end         [nil          (fn [_])]}
   current))

(defn- pipeline-action
  [state state-history current-action play-mode
   next-action next-step exception-handler]
  (let [old-state @state]
    (try
      (let [{:keys [system next-steps]} old-state
	    new-system    (next-action system)
	    new-inspector (ui/visualize new-system next-step)
	    next-steps    (vec (rest next-steps))]
	(dosync
	 (alter state assoc
		:text       text
		:current    next-step
		:next-steps next-steps
		:inspector  new-inspector
		:system     new-system)
	 (alter state-history conj old-state)))
      (catch InterruptedException e
	;; Thread was interrupted. Reset to old-state; this causes the
	;; ref watcher in (pipeline) to reset the UI to the old-state.
	(dosync
	 (ref-set state old-state)
	 (ref-set play-mode false)))
      (catch Exception e
	(dosync
	 (ref-set current-action nil)
	 (ref-set play-mode false))
	(exception-handler e)
	(throw e)))))

(defn pipeline
  ([system-definitions] (pipeline system-definitions ""))
  ([system-definitions text]
     (let [system-selection   (combobox
			       :model system-definitions
			       :renderer (string-renderer
					  (fn [[label {:keys [description]}]]
					    (if description
					      description
					      label))))
	   steps              [:construct :annotate :simplify :rank :reconstruct :end]
	   initial-state      {:current    :start
			       :text       text
			       :inspector  (canvas
					    :background :white
					    :paint      (partial draw-logo "itac/summary/icons/logo.png"))
			       :next-steps steps
			       :steps      steps
			       :system     nil}
	   state              (ref initial-state)
	   state-history      (ref [])

	   play-mode          (ref false)
	   current-action     (ref nil)

	   pipeline-canvas    (canvas :paint (partial draw-pipeline state)
				      :preferred-size [350 :by 0])

	   load-text-button   (button :tip  "Load text"
				      :icon (io/resource "itac/summary/icons/document-open.png"))
	   reset-button       (button :tip  "Reset"
				      :icon (io/resource "itac/summary/icons/edit-clear.png"))
	   next-button        (button :tip  "Next"
				      :icon (io/resource "itac/summary/icons/go-next.png"))
	   prev-button        (button :tip  "Previous"
				      :icon (io/resource "itac/summary/icons/go-previous.png"))
	   stop-button        (button :tip      "Stop"
				      :icon     (io/resource "itac/summary/icons/process-stop.png")
				      :enabled? false)
	   play-button        (button :tip      "Play"
				      :icon     (io/resource "itac/summary/icons/play.png"))

	   textarea           (seesaw.core/text :text        text
						:multi-line? true
						:editable?   true
						:wrap-lines? true)

	   reduction-spinner  (spinner :model (spinner-model 66 :from 0 :to 100 :by 1))

	   ;; All widgets that should be disabled when there is some
	   ;; computation going on
	   toolbar-items      [reset-button prev-button next-button play-button stop-button
			       load-text-button (label "System:") system-selection
			       (label "Reduktion in %:") reduction-spinner]

	   panel              (border-panel
			       :north (toolbar :items toolbar-items))
	   text-article-panel (tabbed-panel
			       :id   :text-article-panel
			       :tabs [{:title   "Text"
				       :content (scrollable textarea :preferred-size [350 :by 0])}
				      {:title "Online articles"
				       :content (scrollable
						 (scraper/articles-panel
						  (fn [a]
						    (text! textarea (.text a))
						    (.setSelectedIndex (select panel [:#text-article-panel]) 0))))}])
	   text-and-inspector (left-right-split
			       text-article-panel
			       (:inspector initial-state))]
	(letfn [(next-clicked [e]
		  (let [{:keys
			 [current
			  inspector]} @state

			text          (.getText textarea)

			[next-step
			 next-action] (pipeline-transition (last (selection system-selection))
							   (selection reduction-spinner)
							   text
							   current)

			 action       (Thread.
				       (partial
					pipeline-action
					state
					state-history
					current-action
					play-mode
					next-action
					next-step
					#(invoke-later (alert (str "Step " next-step "\n" %))
						       (config! prev-button :enabled? true)
						       (config! stop-button :enabled? false)
						       (config! inspector :enabled? true))))]
		    (when next-step
		      (doseq [widget toolbar-items]
			(if-not (instance? javax.swing.JLabel widget)
			  (config! widget :enabled? false)))
		      (config! text-article-panel :enabled? false)
		      (.setSelectedIndex text-article-panel 0)
		      (config! textarea :editable? false)
		      (config! inspector :enabled? false)
		      (config! stop-button :enabled? true)
		      (dosync
		       (ref-set current-action action))
		      (.start action))))

		(prev-clicked [e]
		  (when-let [old-state (peek @state-history)]
		    (let [inspector (:inspector @state)]
		      (doseq [widget toolbar-items]
			(if-not (instance? javax.swing.JLabel widget)
			  (config! widget :enabled? false)))
		      (config! inspector :enabled? false)
		      (dosync
		       (ref-set state old-state)
		       (alter state-history pop)))))

		(reset-clicked [e]
		  (let [text (.getText textarea)]
		    (dosync
		     (ref-set state initial-state)
		     (alter state assoc :text text)
		     (ref-set state-history []))))

		(stop-clicked [e]
		  (when-let [action @current-action]
		    (dosync
		     (ref-set current-action nil))
		    (.interrupt action)))

		(load-text-clicked [e]
		  (reset-clicked e)
		  (choose-file :dir "." :filters [["Text files" ["txt"]]]
			       :success-fn (fn [fc file]
					     (config! textarea :text (slurp file)))))
		(play-clicked [e]
		  (dosync
		   (ref-set play-mode true))
		  (next-clicked e))]

	  ;; Event bindings
	  (listen reset-button :action reset-clicked)
	  (listen next-button :action next-clicked)
	  (listen prev-button :action prev-clicked)
	  (listen load-text-button :action load-text-clicked)
	  (listen stop-button :action stop-clicked)
	  (listen play-button :action play-clicked)

	  ;; Register ref watchers
	  (add-watch
	   state :repaint
	   (fn [key _ old-val new-val]
	     ;; The state has changed: redraw the pipeline
	     (invoke-later
	      (repaint! pipeline-canvas)
	      (let [inspector (:inspector new-val)]
		(config! inspector
			 :preferred-size [350 :by 0]
			 :enabled?       true)
		(.setRightComponent text-and-inspector inspector))
	      (config! next-button :enabled? true)
	      (if-not @play-mode
		(config! play-button :enabled? true))
	      (config! prev-button :enabled? true)
	      (config! load-text-button :enabled? true)
	      (config! reset-button :enabled? true)
	      (config! stop-button :enabled? false)
	      (cond
	       (= :start (:current new-val))
	       (do
		 (config! textarea :editable? true)
		 (config! text-article-panel :enabled? true)
		 (config! system-selection :enabled? true)
		 (config! reduction-spinner :enabled? true))

	       (= :end (:current new-val))
	       (do
		 (dosync
		  (ref-set play-mode false))
		 (config! play-button :enabled? true))

	       @play-mode
	       (next-clicked nil)))))

	  (config! panel
		   :center
		   (mig-panel
		    :constraints ["fill, insets 0" "[200][grow]" ""]
		    :items [[pipeline-canvas "grow"]
			    [text-and-inspector "grow"]]))))))

(defn pipeline-frame
  ([system-definitions] (pipeline-frame system-definitions ""))
  ([system-definitions text]
     (show! (pack! (frame :content (pipeline system-definitions text)
			  :title "itac-summary")))))

(defn -main
  ([]
     (config! (pipeline-frame eval/system-definitions) :on-close :exit)
     nil)
  ([args]
     (-main)))
