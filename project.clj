(defproject ankimo "0.1.1"
  :description "Browser extension for sending Japanese words to Anki"
  :url "https://github.com/dvcrn/ankimo"
  :license {:name "MIT"
            :url "https://mit-license.org"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [org.clojure/core.match "0.3.0-alpha5"]
                 [prismatic/dommy "1.1.0"]
                 [lein-figwheel "0.5.15"]
                 [anki-cljs "0.1.1"]]

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.2"]
                                  [org.clojure/tools.nrepl "0.2.10"]
                                  [figwheel-sidecar "0.5.8"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

  :plugins [[lein-npm "0.6.2"]
            [lein-cljsbuild "1.1.7"]
            [cider/cider-nrepl "0.14.0"]]

  :source-paths ["src"]

  :cljsbuild {:builds [;;{:id "repl"
                       ;; :source-paths ["src"],
                       ;; :figwheel true
                       ;; :compiler {:output-to "out/build.js",
                       ;;            :output-dir "out/",
                       ;;            :optimizations :none,
                       ;;            :pretty-print true}}
                       {:id "safari-worker"
                        :source-paths ["src/ankimo/safari/worker" "src/ankimo/common/worker"],
                        :compiler {:output-to "ankimo.safariextension/worker.js",
                                   :output-dir "out/ankimo.safariextension/worker/",
                                   :closure-output-charset "us-ascii"
                                   :optimizations :simple,
                                   :pretty-print false}}

                       {:id "safari-main"
                        :source-paths ["src/ankimo/safari/main"],
                        :compiler {:output-to "ankimo.safariextension/main.js",
                                   :output-dir "out/ankimo.safariextension/main/",
                                   :closure-output-charset "us-ascii"
                                   :optimizations :simple,
                                   :pretty-print false}}

                       {:id "chrome-main"
                        :source-paths ["src/ankimo/chrome/main"],
                        :compiler {:output-to "chrome/background.js",
                                   :output-dir "out/chrome/main/",
                                   :closure-output-charset "us-ascii"
                                   :optimizations :simple,
                                   :pretty-print false}}

                       {:id "chrome-options"
                        :source-paths ["src/ankimo/chrome/options"],
                        :compiler {:output-to "chrome/options.js",
                                   :output-dir "out/chrome/options/",
                                   :closure-output-charset "us-ascii"
                                   :optimizations :simple,
                                   :pretty-print false}}

                       {:id "chrome-worker"
                        :source-paths ["src/ankimo/chrome/worker" "src/ankimo/common/worker"],
                        :compiler {:output-to "chrome/worker.js",
                                   :output-dir "out/chrome/worker/",
                                   :closure-output-charset "us-ascii"
                                   :optimizations :simple,
                                   :pretty-print false}}
                       ]}

  :figwheel {:http-server-root "public"
             :server-port 5309
             :server-ip   "localhost"
             :nrepl-port 7888

             :nrepl-middleware ["cider.nrepl/cider-middleware"
                                "cemerick.piggieback/wrap-cljs-repl"]})
