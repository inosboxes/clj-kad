(ns clj-kad.core-test
  (:require [clojure.test :refer :all]
            [clj-kad.transport-http :refer :all]
            [clojure.pprint :as pprint]
            [clj-kad.store :as store]
            [konserve.core :as k]
            [clojure.core.async :as async]
            [clj-kad.core :refer :all]))

(comment (deftest transport-http-test
  (testing "start and stop http-htransport-server on port"
    (let [port  1337
          transport-http-server (start-server port)]
      (is (= port 1337))
      (Thread/sleep 5000)
      (is (= nil (.close transport-http-server)))))))

(deftest mem-store-functional-test
  (let [chan (store/get-mem-store-chan)
        kee ["foo"]
        value {:foo "baz"}]
    (async/go
      (store/set-item chan kee value)
      (is  (= value  (async/go  (store/get-item chan kee))))
      )
    (async/close! chan)
    ))
