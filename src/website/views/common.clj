(ns website.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "website"]
               (include-css "/css/reset.css")
               (include-js "/js/jquery-1.8.0.min.js")]
              [:body
               [:div#wrapper
                content]]))
