(ns lobos.config
  (:use [lobos.connectivity :only [open-global]]
        [website.db :only [*config*]]))

(open-global *config*)
