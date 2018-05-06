(ns orbit.orbits
  (:require [quil.core :as q]
            [orbit.vector-utils :as v]))

(defn initial-locations [number-of-locations]
  (for [x (range 0 number-of-locations)]
    (v/random-vec 0 (q/width) 0 (q/height))))

(defn draw-orbits [locations]
  (doseq [orbit-location locations]
    (q/ellipse (nth orbit-location 0) (nth orbit-location 1) 16 16)))
