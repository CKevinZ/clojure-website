(ns website.views.welcome
  (:require [website.views.common :as common])
  (:use [noir.core :only [defpage]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to website"]))
