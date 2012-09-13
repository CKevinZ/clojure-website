(ns website.views.posts
  (:require [website.models.post :as post])
  (:use [noir.core :only [defpage defpartial]]
        [website.views.common :only [main-layout form-for]]))

(defpage posts-path "/posts" []
  (main-layout "Hello from posts"
               (for [p (post/all)]
                 [:p (str p)])))
