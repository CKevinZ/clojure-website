(ns website.db
  (:use [korma.db :only [defdb mysql]]))

(def ^:dynamic *config* (mysql {:db "website" :user "ck"}))

(defdb website *config*)
