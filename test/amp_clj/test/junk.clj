(ns amp-clj.test.junk
  (:use clojure.test)
  (:use [amp-clj.junk]))

(deftest e8v-tests

  (is (=
    (e8v "foo")
    (map int "foo")))

  (is (=
    (e8v (map int "foo"))
    (map int "foo"))))


(deftest bytemap-tests

  (is (= (bytemap {"foo" "bar"})
         {(e8v "foo") (e8v "bar")})
   "Converts {string string} to {bytes bytes}")

  (is (= (bytemap {"foo" (e8v "bar")})
         {(e8v "foo") (e8v "bar")})
   "Converts {string bytes} to {bytes bytes}")

  (is (= (bytemap {(e8v "foo") "bar"})
         {(e8v "foo") (e8v "bar")})
   "Converts {bytes string} to {bytes bytes}"))