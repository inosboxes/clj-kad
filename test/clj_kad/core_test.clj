(ns clj-kad.core-test
  (:require [clojure.test :refer :all]
            [clj-kad.transport-http :refer :all]
            [clj-kad.store :as store]
            [clj-kad.core :refer :all]))

(deftest transport-http-test
  (testing "start and stop http-htransport-server on port"
    (let [port  1337
          transport-http-server (start-server port)]
      (is (= port 1337))
      (Thread/sleep 5000)
      (is (= nil (.close transport-http-server))))))

(comment (deftest mem-store-functional-test
  (let [mem-store (store/get-mem-store)
        kee [:test]
        test-value {:test (range 6)}
        added (store/set-item kee test-value)
        readed (store/get-item kee)]
    (is (not (= nil added)))
    ;(is (= test-value readed))
    (is (= test-value (>!! readed)))
    (comment (.close mem-store)))))
