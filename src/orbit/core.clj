(ns orbit.core
  (:require [orbit.orbits :as o]
            [quil.core :as q]
            [quil.middleware :as m]))

(use 'clojure.core.matrix)
(use 'clojure.core.matrix.operators)
(set-current-implementation :vectorz)

(defn setup []
  (q/frame-rate 30)
  (q/background 240)
  {:locations (o/initial-locations 20)
   :speeds (vector 0.1 0.1)})

;; (defn update-state [state]
;;   (let [mouse (vector (q/mouse-x) (q/mouse-y))
;;         snake-location (:snake-location state)
;;         direction (* (normalise (- mouse (:snake-location state))) 0.5)
;;         snake-velocity (constrain-vector (+ direction (:snake-velocity state)) -5 5 -5 5)
;;         snake-location (+ snake-velocity snake-location)]
;;     (assoc state :snake-location snake-location :snake-velocity snake-velocity)))

(defn draw-state [state]
  (o/draw-orbits (:locations state)))

(q/defsketch orbit
  :title "Eco eco eco"
  :size [500 500]
  :setup setup
  ;; :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])

