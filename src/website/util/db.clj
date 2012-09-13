(ns website.util.db
  (:use [website.util.core :only [with-ns]]
        [website.db :only [*config*]]
        [clojure.java.jdbc :only [with-connection]]))

(defmacro with-jdbc
  "Evaluates clojure.java.jdbc code without any use or require
  inside an open connection based on a dynamic *config* var.
  ex: (with-jdbc (with-query-results users
        [\"SELECT * FROM users\"]
        (first users)))"
  [& body]
  `(with-ns clojure.java.jdbc
    (with-connection *config* ~@body)))
