(defproject clj-kad "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :plugins [[lein-cljfmt "0.5.7"]
            [lein-plz "0.3.3"]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [aleph "0.4.4"]
                 [io.replikativ/konserve "0.4.11"]
                 [lein-cljfmt "0.5.7"]
                 [compojure "1.6.0"]
                 [ring "1.6.3"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [byte-streams "0.2.4-alpha3"]])
