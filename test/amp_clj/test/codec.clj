(ns amp-clj.test.codec
  (:use amp-clj.test.junk)
  (:use amp-clj.codec)
  (:use clojure.test)
  (:require [gloss.core :as gloss])
  (:require [gloss.io :as io])
  )


(defn enc
  "Encode the given input with the given codec, and return a byte vector."
  [codec input]
  (get-bytes-from-bytebuf (io/contiguous (io/encode (gloss/compile-frame codec) input)))
  )


(deftest prefix16-encode
  (is (compbytes (enc prefix16 (e8v "foo")) [0 3 \f \o \o])))


(deftest keyval-encode
  (is (compbytes
    (enc keyval [(e8v "foo") (e8v "bar")])
    [0 3 \f \o \o 0 3 \b \a \r])))


(deftest box-encode
  (is (compbytes
    (enc box [[(e8v "foo") (e8v "bar")]])
    [0 3 \f \o \o 0 3 \b \a \r 0 0])))