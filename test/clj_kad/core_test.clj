(ns clj-kad.core-test
  (:require [clojure.test :refer :all]
            [clj-kad.transport-http :refer :all]
            [clj-kad.core :refer :all]))

(deftest transport-http-test
  (testing "start and stop http-htransport-server on port"
    (let [port  1337
          transport-http-server (start-server port)]
      (is (= port 1337))
      (Thread/sleep 5000)
      (is (= nil (.close transport-http-server))))))
