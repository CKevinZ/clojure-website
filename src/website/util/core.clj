(ns website.util.core)

(defmacro with-ns [ns & body]
  `(binding [*ns* (the-ns '~ns)]
    ~@(map (fn [form] `(eval '~form)) body)))

(defmacro mockfn
  ([name] `(defn ~name ~'[& _]))
  ([name ret] `(defn ~name ~'[& _] ~ret)))
