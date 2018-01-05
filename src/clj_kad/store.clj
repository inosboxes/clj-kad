(ns clj-kad.store
  (:require
   [konserve.filestore :refer [new-fs-store]]
   [konserve.memory :refer [new-mem-store]]
   [konserve.core :as k]
   [clojure.core.async :as async])
  (:gen-class))

(comment
 (def store-chan (chan))

 (defn close-store-chan "doc-string" []
   (close! store-chan))

 (defn get-mem-store []
   (go (<! store-chan (new-mem-store (atom {})))))

 (defn get-fs-store [path]
   (go (<! store-chan (new-fs-store path))))

 (defn exists? "doc-string" [store & args]
   (println args)
   (go (<! store-chan (k/exists? store args))))

 (defn get-item "doc-string" [store & args]
   (println args)
   (go (<! store-chan (k/get-in store args))))

 (defn set-item "doc-string" [store kee & args]
   (println kee args)
   (go (<! store-chan (k/assoc-in store kee args))))
 )
