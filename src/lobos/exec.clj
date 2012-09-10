(ns lobos.exec
  (:require [lobos.core :as lobos])
  (:use lobos.migrations))

(defn migrate-all []
  (lobos/migrate))

(defn migrate [& migrations]
  (apply lobos/migrate (map symbol migrations)))

(defn rollback-all []
  (lobos/rollback :all))

(defn rollback [& migrations]
  (apply lobos/rollback (map symbol migrations)))
