(ns clj-kad.store
  (:require
   [konserve.filestore :refer [new-fs-store]]
   [konserve.memory :refer [new-mem-store]]
   [konserve.core :as k]
   [clojure.core.async :as async])
  (:gen-class))

(defn get-mem-store-chan []
  (async/<!! (new-mem-store (atom {}))))

(defn get-fs-store-chan [path]
  (async/<!! (new-fs-store path)))

(defn set-item "doc-string" [chan kee args]
  (async/<!! (k/assoc-in chan kee args)))

(defn get-item "doc-string" [chan kee]
  (async/<!! (k/get-in chan kee)))

(comment

  (defn close-store-chan "doc-string" [store-chan]
    (async/close! store-chan))

  (defn exists? "doc-string" [store-chan & args]
    (println args)
    (go (<! store-chan (k/exists? store args)))))
