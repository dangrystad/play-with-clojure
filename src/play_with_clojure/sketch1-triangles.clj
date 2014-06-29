(ns play-with-clojure
  ( :require [quil.core :as q]))

(defn draw-triangle [x y size]
  (q/fill (q/random 255) (q/random 255) (q/random 255))
  (let [
        x1 (if (> (rand-int 2) 0) x (+ x size))
        y1 y
        x2 x
        y2 (+ y size)
        x3 (+ x size)
        y3 y2]
    (q/triangle x1 y1 x2 y2 x3 y3)))


(defn draw-box [x y size]
  (q/fill (q/random 255) (q/random 255) (q/random 255))
  (q/rect x y size size)
  (draw-triangle x y size))

(defn draw-squres [margin width height blocks]
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
       (draw-box x y step)))))

(defn setup []
  (q/smooth)
  (q/stroke 200)
  (q/stroke-weight 1)
  (q/background 200)
  (let [
        margin 5
        width (q/width)
        height (q/height)
        blocks 3
        ]
    (draw-squres margin width height blocks))
  (q/save (apply str ["target/img/triangles_" (rand-int 100000) ".png"])))

(q/defsketch example
  :title "Triangles"
  :setup setup
  :size [320 320])
