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
    ;(println "args" args "numbers:" numbers "min: " lower ", max: " upper ", diff: " tick-diff)
    ;(println (map #(tick-pos %) numbers))
    (println (apply str (map #(nth ticks (tick-pos %)) numbers)))))

(defn read-from-stdin? []
  (not node/process.stdin.isTTY))

;(def http (node/require "http"))
;(def stdin (. node/process (stdin)))

(defn start [& args]
  ;(println (not node/process.stdin.isTTY))
  ;(if (not node/process.stdin.isTTY)
  (if (read-from-stdin?)
    (do
      ;(println "reading from stdin!")
      (. node/process.stdin (resume))
      (.setEncoding node/process.stdin "utf8")
      ;(println (. node/process.stdin (read)))
      (.on node/process.stdin "data" (fn [e]
                                       ;(println (string/ e)))))
                                       (print-graph (conj '() (string/trim-newline e))))))
      ;(.on node/process.stdin "readable" (fn [e]
      ;                                  (println "stuff is readable")))
  ;(.on node/process.stdin "close" (fn [e]
  ;                                  (println "stuff is closed")))
      ;(.on node/process.stdin "end" (fn [e]
      ;                                  (println "ended"))))
  ;(. stdin (resume))
  ;(.setEncoding stdin "utf8")
  ;(println (line-seq (java.io.BufferedReader. *in*)))
    (if (print-usage? args)
        (print-usage)
        (print-graph args))))

(set! *main-cli-fn* start)
