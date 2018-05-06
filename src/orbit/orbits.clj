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
    (q/point (nth orbit-location 0) (nth orbit-location 1))))

(defn recalculate-speed [location speed]
  (let [mouse (vector (q/mouse-x) (q/mouse-y))
        direction (* (normalise (- mouse location)) 0.5)
        speed-unconstrained (+ direction speed)
        new-speed (v/constrain-vector speed-unconstrained -5 5 -5 5)]
    new-speed))

(defn recalculate-location [location speed]
  (+ speed location))

(defn double-iteration [a b fn]
  (for [index (range 0 (count a))]
    (fn (nth a index) (nth b index))))

(defn recalculate-orbits-speed [locations speeds]
  double-iteration locations speeds recalculate-speed)

(defn recalculate-orbits-location [locations speeds]
  double-iteration locations speeds recalculate-location)
