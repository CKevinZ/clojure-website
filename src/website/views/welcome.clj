(ns website.views.welcome
  (:use [noir.core :only [defpage]]
        [website.views.common :only [main-layout]]))

(defpage root "/" []
  (main-layout
    [:p "Welcome to website"]))
