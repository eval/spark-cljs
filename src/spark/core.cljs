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
  (let [ticks '(\▁  \▂  \▃  \▄  \▅  \▆  \▇  \█)
        numbers (map int args)
        lower (apply min numbers)
        upper (apply max numbers)
        tick-diff (int (/ (* (- upper lower) 256) (dec (count ticks))))
        tick-pos (fn [x] (int (/ (* (- x lower) 256) tick-diff)))]
    ;(println "min: " lower ", max: " upper ", diff: " tick-diff)
    ;(println (map #(tick-pos %) numbers))
    (println (apply str (map #(nth ticks (tick-pos %)) numbers)))))

(defn start [& args]
  (if (print-usage? args)
      (print-usage)
      (print-graph args)))

(set! *main-cli-fn* start)
