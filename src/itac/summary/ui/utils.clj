(ns itac.summary.ui.utils
  (:use seesaw.graphics
        seesaw.color))

(defn draw-centered-image
  [c image]
  "Draws image centered on Component c."
  (reify Draw
    (draw* [shape g2d shape-style]
      (let [img          (.getImage image)
            img-observer (.getImageObserver image)
            w            (.getWidth c)
            h            (.getHeight c)
            img-w        (.getWidth img)
            img-h        (.getHeight img)
            x            (/ (- w img-w) 2)
            y            (/ (- h img-h) 2)]
        (.drawImage g2d img x y (color :white) img-observer)))))

(defn draw-label
  [x y s]
   (reify Draw
    (draw* [shape g2d shape-style]
      (let [s      (str s)
            fm     (.getFontMetrics g2d)
            rect   (.getStringBounds fm s g2d)
            text-w (.getWidth rect)
            text-h (.getHeight rect)

            x      (float x)
            y      (float y)]
        (.drawString g2d s x y)))))

(defn labeled-rect
  ([x y w h s draw-rect?]
      (reify Draw
        (draw* [shape g2d shape-style]
          (if draw-rect?
            (draw g2d
                  (rounded-rect x y w h) shape-style))
          (let [s      (str s)
                fm     (.getFontMetrics g2d)
                rect   (.getStringBounds fm s g2d)
                text-w (.getWidth rect)
                text-h (.getHeight rect)

                x      (float (+ x (/ (- w text-w) 2)))
                y      (float (+ y (/ (- h text-h) 2) (.getAscent fm)))]
            (.drawString g2d s x y)))))
  ([x y w h s]
     (labeled-rect x y w h s true)))


(defn labeled-circle
  [circle-x circle-y circle-r margin s]
  (reify Draw
    (draw* [shape g2d shape-style]
      (draw g2d
            (circle circle-x circle-y circle-r) shape-style
            (labeled-rect (+ circle-x margin) (- circle-y (/ circle-r 2))
                          circle-r circle-r s false) shape-style))))

(defn draw-arrow
  [x1 y1 x2 y2]
  (reify Draw
    (draw* [shape g2d style]
      (let [dx    (- x2 x1)
            dy    (- y2 y1)
            angle (* (/ 180 Math/PI)
                     (Math/atan2 dy dx))
            len   (- (Math/sqrt (+ (* dx dx)
                                   (* dy dy)))
                     (.getLineWidth (:stroke style)))
            size  4
            style (update-style style :background (:foreground style))]
        (push g2d
              (translate g2d x1 y1)
              (rotate g2d angle)
              (draw g2d
                    (line 0 0 len 0) style
                    (polygon [len 0]
                             [(- len size) (- size)]
                             [(- len size) size]
                             [len 0]) style))))))
