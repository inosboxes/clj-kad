(ns clj-kad.transport-http
  (:import
   [io.netty.handler.ssl SslContextBuilder])
  (:require
   [compojure.core :as compojure :refer  [GET]]
   [ring.middleware.params :as params]
   [compojure.route :as route]
   [compojure.response :refer  [Renderable]]
   [aleph.http :as http]
   [byte-streams :as bs]
   [manifold.stream :as s]
   [manifold.deferred :as d]
   [clojure.core.async :as a]
   [clojure.java.io :refer  [file]]))

(defn hello-world-handler
  [req]
  {:status 200
   :headers  {"content-type" "text/plain"}
   :body "hello world!"})

; Compojure will normally dereference deferreds and
; return the realized value. Unfortunately, this blocks the thread.
;  Since Aleph can accept the unrealized deferred, we extend
;   Compojure's Renderable protocol to pass the deferred through
;   unchanged so it can be handled asynchronously.
(extend-protocol Renderable
  manifold.deferred.IDeferred
  (render  [d _] d))

(def handler
  (params/wrap-params
   (compojure/routes
    (GET "/hello" [] hello-world-handler)
    (route/not-found "No such page."))))

(defn start-server [port]
  (http/start-server handler {:port port}))
