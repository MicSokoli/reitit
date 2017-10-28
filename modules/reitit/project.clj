(load-file "../../.deps-versions.clj")
(defproject metosin/reitit reitit-version
  :description "Snappy data-driven router for Clojure(Script)"
  :url "https://github.com/metosin/reitit"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[metosin/reitit-core ~reitit-version]
                 [metosin/reitit-ring ~reitit-version]
                 [metosin/reitit-spec ~reitit-version]])