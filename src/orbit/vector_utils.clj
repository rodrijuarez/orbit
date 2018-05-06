(ns orbit.vector-utils
  (:require [quil.core :as q]))

(defn random-vec [min-a max-a min-b max-b]
  (let [c (vector (q/random min-a max-a) (q/random min-b max-b))] c))

(defn constrain-vector [a min-a max-a min-b max-b]
  (let [constrained-vector (vector (q/constrain (nth a 0) min-a max-a) (q/constrain (nth a 1) min-b max-b))] constrained-vector))
