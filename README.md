spark-cljs
==========

Port of https://github.com/holman/spark in clojurescript

Requirements
------------

- [Leiningen](https://github.com/technomancy/leiningen)
- [Nodejs](https://github.com/joyent/node)
- [roundup](https://github.com/bmizerany/roundup) (for tests)

Usage
------------

    # Build it
    $ lein cljsbuild once

    # Run it
    $ node out/spark.js 0 30 55 80 33 150
      ▁▂▃▅▂▇

    # Help
    $ node out/spark.js -h
