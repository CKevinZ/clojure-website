(ns website.server
  (:require [noir.server :as server]
            [website.db :as db]))

(server/load-views-ns 'website.views)

(defn -main [& args]
  (let [args (apply hash-map args)
        port (Integer. (or (args "-port") 3000))
        mode (keyword (or (args "-mode") :dev))]
    (server/start port {:mode mode :ns 'website})))
