(ns orbit.vector-utils
  (:require [quil.core :as q]))

(defn random-vec [min-a max-a min-b max-b]
  (let [c (vector (q/random min-a max-a) (q/random min-b max-b))] c))

(defn constrain-axis [vec axis min max]
  (q/constrain (nth vec axis) min max))

(defn constrain-vector [a min-a max-a min-b max-b]
  (let [x-axis (constrain-axis a 0 min-a max-a)
        y-axis (constrain-axis a 1 min-b max-b)
        constrained-vector (vector x-axis y-axis)] constrained-vector))
