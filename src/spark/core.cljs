(ns spark.core
  (:require [clojure.string :as string]
            [cljs.nodejs :as node]))

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

(defn numbers [args]
  (if (= 1 (count args))
      (recur (string/split (first args) #","))
      (map int args)))

(defn print-graph [args]
  (let [ticks '(\▁  \▂  \▃  \▄  \▅  \▆  \▇  \█)
        numbers (numbers args)
        lower (apply min numbers)
        upper (apply max numbers)
        tick-diff (int (/ (* (- upper lower) 256) (dec (count ticks))))
        tick-pos (fn [x] (int (/ (* (- x lower) 256) tick-diff)))]
    (println (apply str (map #(nth ticks (tick-pos %)) numbers)))))

(defn read-from-stdin? []
  (not node/process.stdin.isTTY))

(defn handle-stdin-data [data]
  (print-graph (conj '() (string/trim (string/replace data #"\n" ",")))))

(defn start [& args]
  (if (read-from-stdin?)
    (do
      (. node/process.stdin (resume))
      (.setEncoding node/process.stdin "utf8")
      (.on node/process.stdin "data" handle-stdin-data))
    (if (print-usage? args)
        (print-usage)
        (print-graph args))))

(set! *main-cli-fn* start)
