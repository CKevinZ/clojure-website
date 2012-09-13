(ns website.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defmulti form-for (fn [route & _] (class route)))

(defmethod form-for String [route & args]
  (apply form-for [:get route] args))

(defmethod form-for clojure.lang.PersistentVector [[method action] & args]
  [:form {:action action :method method} args])

(defpartial main-layout [& content]
  (html5
    [:head
     [:title "Website"]
     (include-css "/css/reset.css")
     (include-js "/js/jquery-1.8.0.min.js")]
    [:body
     [:div#wrapper
      content]]))
