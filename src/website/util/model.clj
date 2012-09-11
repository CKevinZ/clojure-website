(ns website.util.model
  (:refer-clojure :exclude [find] :rename {first fst})
  (:require [korma.core :as k])
  (:use [website.util.core :only [memoize! var->symbol unqualify]]))

(def ^:private fns (atom []))

(defmacro ^:private defnr
  "Define a function and register the var
  in a previously declared fns vector atom"
  [name args & body]
  `(do
     (defn ~name ~args ~@body)
     (swap! fns conj (resolve (quote ~name)))))

(defnr size [entity]
  (get-in (k/select entity
            (k/aggregate (count :id) :count))
          [0 :count]))

(defnr all [entity]
  (k/select entity))

(defnr first [entity]
  (fst
    (k/select entity
      (k/limit 1))))

(defnr find [entity id]
  (fst
    (k/select entity
      (k/where {:id id}))))
(memoize! 'find)

(defnr find-all-by [entity column value]
  (k/select entity
    (k/where {(keyword column) value})))

(defnr find-by [entity column value]
  (fst
    (k/select entity
      (k/where {(keyword column) value})
      (k/limit 1))))

(defnr sample [entity]
  (fst
    (k/select entity
      (k/offset (rand-int (size entity)))
      (k/limit 1))))

(defnr insert [entity values]
  (let [now (java.sql.Timestamp. (.getTime (java.util.Date.)))]
    (k/insert entity
      (k/values (merge values
                       {:created_at now :updated_at now})))))

(defnr update [entity new-values conditions]
  (k/update entity
    (k/set-fields new-values)
    (k/where conditions)))

(defnr delete [entity conditions]
  (k/delete entity
    (k/where conditions)))

(defnr delete-all [entity]
  (k/delete entity
    (k/where true)))

(defmacro defmodel [name & args]
  `(do
    (k/defentity ~name ~@args)
    ~@(for [[fn-name body]
            (zipmap (map #(-> % var->symbol unqualify) @fns) @fns)]
      `(def ~fn-name (partial ~body ~name)))
     ~name))
