(defproject spark "0.0.1-SNAPSHOT"
  :description "Cool new project to do things and stuff"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2080"
                  :exclusions [org.apache.ant/ant]]
                 ]
  :plugins [[lein-cljsbuild "1.0.1-SNAPSHOT"]]
  :cljsbuild {
    :builds [{:source-paths ["src"]
              :compiler {:output-to "out/spark.js"
                         :optimizations :simple
                         :pretty-print true
                         :target :nodejs }}]}
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}})
