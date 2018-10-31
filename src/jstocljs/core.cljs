(ns jstocljs.core
  (:require [reagent.core :as r]))

(defonce state (r/atom 0))

;; Funções que eu quero usar no JS tenho que "exportar"
(defn ^:export MyFunc
  [x]
  (apply + (repeat x 10)))

(defn my-comp
  [{:keys [text]}]
  [:div
   [:button {:on-click #(swap! state inc)} "+"]
   [:div (str "Hello " @state)]
   [:div (str "text: " text)]])

;; Preciso adaptar o componente para ele ser um componente react 'cru'
(defn ^:export MyComp
  [m]
  (r/as-component
    [:div
     [my-comp (js->clj m :keywordize-keys true)]]))
