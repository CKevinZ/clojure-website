(ns website.models.user
  ; create a macro for the `refer-clojure` below:
  (:refer-clojure :exclude [find first])
  (:use [website.util.model :only [defmodel]]
        [korma.core :only [has-many]]
        [website.models.post :only [posts]]))

(defmodel users
  (has-many posts))
