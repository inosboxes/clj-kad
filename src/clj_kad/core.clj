(ns clj-kad.core
  (:gen-class))

(defn get-hash [type data]
  (.digest (java.security.MessageDigest/getInstance type) (.getBytes data)))

(defn sha1-hash [data]
  (get-hash "sha1" data))

(defn get-hash-str [data-bytes]
  (apply str
         (map
          #(.substring
            (Integer/toString
             (+ (bit-and % 0xff) 0x100) 16) 1)
          data-bytes)))

(def key-bits-length 160)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defprotocol AbstractNetworkNodeP
  "Abstract Node"
  (listen [this])
  (plugin [this plugin])
  (receive-message [this request response])
  (send-message [this method params contact callback])
  (use-middleware [this method middleware]))

(defprotocol KademliaNodeP
  "Kademlia Node"
  (join [this seed]))

(defrecord NodeInstance
           [transport storage logger messenger node-identity]
  AbstractNetworkNodeP
  KademliaNodeP
  (listen [this] (println "start listening"))
  (join [this seed] (println seed)))

(defprotocol IdentityP
  "Kademlia Identity"
  (get-identity [this])
  (get-host [this])
  (get-port [this]))

(defrecord NodeIdentityInstance
           [id host port]
  IdentityP
  (get-identity [this] id)
  (get-host [this] host)
  (get-port [this] port))

(defprotocol Bucket)
(defprotocol KademliaRule)
(defprotocol Transport)
(defprotocol Messenger)
(defprotocol Logger)
(defprotocol RoutingTable)
(defprotocol ErrorRule)

(defn -main
  "Main function"
  []
  (let  [;seed-node-identity  (NodeIdentityInstance. 1 "localhost" 1337)
         node-identity  (NodeIdentityInstance. 1 "localhost" 1338)
         transport nil
         storage nil
         logger nil
         messenger nil
         kadSeedNode  (NodeInstance.
                       transport storage
                       logger messenger node-identity)]
    (listen kadSeedNode)))
