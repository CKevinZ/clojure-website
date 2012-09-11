(ns website.util.core)

(def to-string (memfn toString))

(def get-name (memfn getName))

(def unqualify (comp symbol get-name))

(defmacro with-ns [ns & body]
  `(binding [*ns* (the-ns '~ns)]
    ~@(map (fn [form] `(eval '~form)) body)))

(defmacro mockfn
  ([name] `(defn ~name ~'[& _]))
  ([name ret] `(defn ~name ~'[& _] ~ret)))

(defn memoize! [sym]
  (alter-var-root (resolve sym) memoize))

(defn var->symbol [^clojure.lang.Var var]
  (-> var to-string (subs 2) symbol))
