(ns user
  (:require
   [clojure.core.async :as async]
   [clojure.pprint :as pprint]))

(require '[clojure.test :refer  [run-tests run-all-tests]])
(require '[clojure.tools.namespace.repl :refer  [refresh]])
(def r refresh)
(require 'cljfmt.core)

(require 'clj-kad.core-test)
(run-tests 'clj-kad.core-test)
