(ns clj-kad.core
  (:gen-class)
  )

(defn get-hash [type data]
  (.digest (java.security.MessageDigest/getInstance type) (.getBytes data) ))

(defn sha1-hash [data]
  (get-hash "sha1" data))

(defn get-hash-str [data-bytes]
  (apply str
         (map
           #(.substring
              (Integer/toString
                (+ (bit-and % 0xff) 0x100) 16) 1)
           data-bytes)
         ))

(def key-bits-length 160)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
