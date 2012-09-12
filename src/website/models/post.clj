(ns website.models.post
  (:refer-clojure :exclude [find first])
  (:use [website.util.model :only [defmodel]]
        [korma.core :only [has-one]]
        [website.models.image :only [images]]))

(defmodel posts
  (has-one images))
