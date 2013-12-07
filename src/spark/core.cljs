(ns spark.core)

(defn print-usage []
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

(defn start [& args]
  (if (seq args)
      (println)
      (print-usage)))

(set! *main-cli-fn* start)
