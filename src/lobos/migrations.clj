(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema config helpers)))

(defmigration create-users
  (up [] (create
           (table! :users
             (varchar :name 64 :unique :not-null)
             (varchar :email 255 :unique :not-null)
             (varchar :password 60 :not-null))))
  (down [] (drop (table :users))))

(defmigration create-posts
  (up [] (create
           (table! :posts
             (varchar :title 255 :unique :not-null)
             (smallint :rating :not-null)
             (integer :parent)
             (varchar :source 255)
             (text :tag)
             (integer :score)
             (integer :views)
             (integer :author [:refer :users :id] :not-null))))
  (down [] (drop (table :posts))))

(defmigration create-images
  (up [] (create
           (table! :images
             (integer :post_id [:refer :posts :id] :not-null)
             (varchar :path 255 :not-null)
             (integer :size :not-null)
             (integer :width :not-null)
             (integer :height :not-null))))
  (down [] (drop (table :images))))
