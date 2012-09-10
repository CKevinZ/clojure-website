(ns lobos.helpers
  (:refer-clojure :exclude [double bigint char float boolean time])
  (:require [clojure.string :as string])
  (:use lobos.schema))

;(defn refer-to [table ptable]
;  (let [cname (-> ptable name butlast string/join (str "_id") keyword)]
;    (integer table cname [:refer ptable :id :on-delete :not-null])))

(defmacro table! [name & elements]
  "An extended version of table.
  Adds an id column as an auto incremented primary key.
  It also adds two timestamp columns 'updated-at' and 'updated-on'."
  `(table ~name
    (integer :id :primary-key :auto-inc)
    ~@elements
    (timestamp :updated_at (default 0))
    (timestamp :created_at (default (~'now)))))
