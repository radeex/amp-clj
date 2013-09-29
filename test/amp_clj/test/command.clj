(ns amp-clj.test.command
  (:use clojure.test)
  (:use [amp-clj.junk])
  (:use [amp-clj.command]))

(deftest build-command-tests

  (is (=
    (build-command "foo" "my-id" {})
    {(e8v "_command") (e8v "foo")
     (e8v "_ask") (e8v "my-id")})))
