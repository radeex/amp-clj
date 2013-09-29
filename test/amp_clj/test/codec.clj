(ns amp-clj.test.codec
  (:use amp-clj.test.junk)
  (:use amp-clj.codec)
  (:use clojure.test)
)


(deftest prefix16-encode
  (is (compbytes (encode prefix16 (e8v "foo")) [0 3 \f \o \o])))


(deftest keyval-encode
  (is (compbytes
    (encode keyval [(e8v "foo") (e8v "bar")])
    [0 3 \f \o \o 0 3 \b \a \r])))


(deftest box-encode
  (is (compbytes
    (encode box [[(e8v "foo") (e8v "bar")]])
    [0 3 \f \o \o 0 3 \b \a \r 0 0]))
  (is (compbytes
    (encode box [[(e8v "foo") (e8v "bar")]
                 [(e8v "baz") (e8v "quux")]])
    [0 3 \f \o \o
     0 3 \b \a \r
     0 3 \b \a \z
     0 4 \q \u \u \x
     0 0])))


(deftest prefix16-decode
  (is (=
    (decode prefix16 [0 3 \f \o \o])
    (e8v "foo"))
  "simple extraction of a single value"))


(deftest keyval-decode
  (is (=
    (decode keyval [0 3 \f \o \o 0 3 \b \a \r])
    [(e8v "foo") (e8v "bar")])))


(deftest box-decode
  (is (=
    (decode box [0 3 \f \o \o
                 0 3 \b \a \r
                 0 3 \b \a \z
                 0 4 \q \u \u \x
                 0 0])
    [[(e8v "foo") (e8v "bar")] [(e8v "baz") (e8v "quux")]])))
