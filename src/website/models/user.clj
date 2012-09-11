(ns website.models.user
  ; create a macro for the `refer-clojure` below:
  (:refer-clojure :exclude [find first])
  (:use [website.util.model :only [defmodel]]))

(defmodel users)
  ;(has-many posts))
