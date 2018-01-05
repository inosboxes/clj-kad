(ns user)

(require '[clojure.test :refer  [run-tests run-all-tests]])
(require '[clojure.tools.namespace.repl :refer  [refresh]])
(require 'cljfmt.core)

(require 'clj-kad.core-test)
(run-tests 'clj-kad.core-test)

(defn pprint "doc-string" [&args]  (println "test"))
