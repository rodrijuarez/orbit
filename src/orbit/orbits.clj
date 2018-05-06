(ns orbit.orbits
  (:require [quil.core :as q]
            [orbit.vector-utils :as v]))

(use 'clojure.core.matrix)
(use 'clojure.core.matrix.operators)
(set-current-implementation :vectorz)

(defn initial-locations [number-of-orbits]
  (for [x (range 0 number-of-orbits)]
    (v/random-vec 0 (q/width) 0 (q/height))))

(defn initial-speeds [number-of-orbits]
  (for [x (range 0 number-of-orbits)]
    (vector 0.1 0.1)))

(defn draw-orbits [locations]
  (doseq [orbit-location locations]
    (q/ellipse (nth orbit-location 0) (nth orbit-location 1) 16 16)))

(defn recalculate-speed [location speed]
  (let [mouse (vector (q/mouse-x) (q/mouse-y))
        direction (* (normalise (- mouse location)) 0.5)
        speed-unconstrained (+ direction speed)
        new-speed (v/constrain-vector speed-unconstrained -5 5 -5 5)]
    new-speed))

(defn recalculate-location [location speed]
  (+ speed location))

(defn recalculate-orbits-speed [locations speeds]
  (for [index (range 0 (count locations))]
    (recalculate-speed (nth locations index) (nth speeds index))))

(defn recalculate-orbits-location [locations speeds]
  (for [index (range 0 (count locations))]
    (recalculate-location (nth locations index) (nth speeds index))))
