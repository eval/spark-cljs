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

```bash
# Build it
$ lein cljsbuild once

# Run it
$ node out/spark.js 0 30 55 80 33 150
  ▁▂▃▅▂▇

# Help
$ node out/spark.js -h
```

Testing
------------

```bash
# Build it
$ lein cljsbuild once

$ test/spark-test.sh
spark: Generates sparklines for a set of data.
  it_shows_help_with_no_argv:                      [PASS]
  it_shows_help_with_dash_h:                       [PASS]
  it_graphs_argv_data:                             [PASS]
  it_charts_pipe_data:                             [PASS]
  it_charts_pipe_data_with_newlines:               [PASS]
  it_charts_spaced_data:                           [PASS]
  it_charts_way_spaced_data:                       [PASS]
  it_handles_decimals:                             [PASS]
  it_charts_100_lt_300:                            [PASS]
  it_charts_50_lt_100:                             [PASS]
  it_charts_4_lt_8:                                [PASS]
  it_charts_no_tier_0:                             [PASS]
=========================================================
Tests:   12 | Passed:  12 | Failed:   0
```
