(ns spark.core)

(defn start [& _]
  (println "
      USAGE:
        spark [-h] VALUE,...

      EXAMPLES:
        spark 1 5 22 13 53
        ▁▁▃▂█
        spark 0,30,55,80,33,150
        ▁▂▃▄▂█
        echo 9 13 5 17 1 | spark
        ▄▆▂█▁"))

(set! *main-cli-fn* start)
