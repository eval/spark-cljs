(ns spark.core)

(defn print-usage? [args]
  (or (not (seq args)) (= "-h" (first args))))

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

(defn print-graph [args]
  (println "▁▂█▅▂"))

(defn start [& args]
  (if (print-usage? args)
      (print-usage)
      (print-graph args)))

(set! *main-cli-fn* start)
