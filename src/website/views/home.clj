(ns website.views.home
  (:use [noir.core :only [defpage defpartial]]
        [website.views.common :only [main-layout form-for]]))

(def form
  (form-for "/posts"
    [:input {:name "search"}]
    [:input {:type "submit"}]))

(def header
  [:img#main])

(def menu
  [:ul
   [:li [:a {:href "/posts"} "Posts"]]
   [:li [:a {:href "/comments"} "Comments"]]
   [:li [:a {:href "/tags"} "Tags"]]
   [:li [:a {:href "/wiki"} "Wiki"]]
   [:li [:a {:href "/more"} ">>"]]])

(defpage root-path "/" []
  (main-layout header menu form))
