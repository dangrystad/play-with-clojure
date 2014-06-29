(ns play-with-clojure.sketch2-circles
  ( :require [quil.core :as q]))

(defn rand-color []
  [(q/random 255) (q/random 255) (q/random 255)])

(defn draw-line [x y r]
  ;(q/stroke 0)
  (let [d (q/radians (rand-int 360))
        x1 x
        y1 y
        x2 (+ x (* r (q/cos d)))
        y2 (+ y (* r (q/sin d)))]
    (q/line x1 y1 x2 y2)))

(defn draw-circle [x y size]
  (q/fill (q/random 255) (q/random 255) (q/random 255))
  ;(q/stroke 200)
  (let [r (/ size 2)
        lx (+ x r)
        ly (+ y r)
        ]
    (dorun
     (q/ellipse lx ly size size)
     (draw-line lx ly r))))


(defn draw-circles [margin width height blocks]
  (let [
        min (if (< width height) width height)
        step (/ (- min margin margin) blocks)
        x-blocks (int (/ (- width margin margin) step))
        y-blocks (int (/ (- height margin margin) step))
        x-start (/ (- width (* x-blocks step)) 2)
        y-start (/ (- height (* y-blocks step)) 2)
        ]
    (dorun
     (for [
           x (range x-start (- width margin step -1) step)
           y (range y-start (- height margin step -1) step)]
       (draw-circle x y step)))))

(defn setup []
  (q/smooth)
  (q/stroke 255)
  (q/stroke-weight 4)
  (q/background 255)
  (let [
        margin 5
        width (q/width)
        height (q/height)
        blocks 3
        ]
    (draw-circles margin width height blocks))
  (q/save (apply str ["target/img/circles_" (rand-int 100000) ".png"])))

(q/defsketch example
  :title "Circles"
  :setup setup
  :size [320 320])
