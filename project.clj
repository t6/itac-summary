(defproject t6/itac-summary "1.0.0-SNAPSHOT"
  :description "Automatic summarizer"
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :java-source-paths ["src-java"]
  :jvm-opts ["-XX:+CMSClassUnloadingEnabled"
             "-XX:PermSize=256m"
             "-XX:MaxPermSize=256m"]
  :main itac.summary.ui.pipeline
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.combinatorics "0.0.8"]
                 [org.clojure/data.json "0.2.5"]

                 [t6/snippets "0.1.0-SNAPSHOT"]
                 [t6/snippets-corenlp "0.1.0-SNAPSHOT"]

                 [com.gravity/goose "2.1.23"]

                 [net.sourceforge.nekohtml/nekohtml "1.9.21"]
                 [enlive "1.1.5" :exclusions [org.clojure/clojure]]
                 [hiccup "1.0.2"]
                 [seesaw "1.4.3" :exclusions [org.clojure/clojure]]

                 [clj-http "1.0.0"]

                 [net.sf.jung/jung-algorithms "2.0.1"]
                 [net.sf.jung/jung-graph-impl "2.0.1"]
                 [net.sf.jung/jung-visualization "2.0.1"]])
