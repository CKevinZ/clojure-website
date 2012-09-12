(ns website.server
  (:require [noir.server :as server]
            [website.db :as db])
  (:use [clojure.tools.cli :only [cli]]))

(server/load-views-ns 'website.views)

(defn -main [& args]
  (try
    (let [[options _ help]
          (cli args
            ["-h" "--help" "Display help" :default false :flag true]
            ["-p" "--port" "Port to listen on" :default 3000 :parse-fn #(Integer. %)]
            ["-m" "--mode" "Modes: dev prod test ..." :default :dev :parse-fn keyword])]
      (if (options :help)
        (println help)
        (let [port (options :port)
              mode (options :mode)]
          (server/start port {:mode mode :ns 'website}))))
    (catch Exception e (println (str "Error: " e)))))
