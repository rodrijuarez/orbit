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
   :speeds (o/initial-speeds 20)})

(defn update-state [state]
  (let [locations (:locations state)
        speeds (:speeds state)
        new-speeds (o/recalculate-orbits-speed locations speeds)
        new-locations (o/recalculate-orbits-location locations new-speeds)]
    (assoc state :locations new-locations :speeds new-speeds)))

(defn draw-state [state]
  (q/background 240)
  (o/draw-orbits (:locations state)))

(q/defsketch orbit
  :title "Eco eco eco"
  :size [500 500]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])
