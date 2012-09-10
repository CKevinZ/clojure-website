(ns website.util.db
  (:use [website.db :only [*config*]]
        [clojure.java.jdbc :only [with-connection]]))

(defmacro with-jdbc [& body]
  `(with-ns clojure.java.jdbc
    (with-connection *config* ~@body)))
