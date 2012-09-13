(ns website.util.core
  (:use [clojure.string :only [join]]))

(def to-string (memfn toString))

(def get-name (memfn getName))

(def unqualify
  "Unqualify a symbol name.
  ex: (unqualify 'clojure.string/join) ; => join
      (unqualify 'def) ; => def"
  (comp symbol get-name))

(defmacro with-ns
  "Very loosely based on clojure.contrib.with-ns/with-ns
  Allows to evaluate code in a different ns.
  ex: (with-ns clojure.string (join [\\a \\b \\c])) ; => \"abc\""
  [ns & body]
  `(binding [*ns* (the-ns '~ns)]
    ~@(map (fn [form] `(eval '~form)) body)))

(defmacro mockfn
  "Mock variadic functions with ease.
  ex: (mockfn foo)
      (foo 1 2 3) ; => nil
      (foo) ; => nil

      (mockfn foo :bar)
      (foo 1 2 3) ; => :bar
      (foo) ; => ;bar"
  ([name] `(defn ~name ~'[& _]))
  ([name ret] `(defn ~name ~'[& _] ~ret)))

(defn memoize!
  "Alter and memoize a var."
  [sym]
  (alter-var-root (resolve sym) memoize))

(defn var->symbol
  "Returns the symbol of var."
  [^clojure.lang.Var var]
  (-> var to-string (subs 2) symbol))

(def ^:private alphanums
  (concat (range 0 10) (map char (range (int \a) (inc (int \z))))))

(defn rand-word
  "Generate a random \"word\" (length between 0 and 36)."
  [length]
  {:pre [(<= length 36) (>= length 0)]}
  (->> alphanums shuffle (take length) join))
