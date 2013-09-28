(ns amp-clj.test.builder
  (:use [amp-clj.builder])
  (:use [clojure.test]))


(defn e8 "Encode a string to bytes with utf-8."
  [string] (.getBytes string "UTF-8"))


(defn d8 "Decode a bytes to a string with utf-8."
  [bytes] (String. (byte-array bytes) "UTF-8"))


(defn compbytes
  "Compare two sequences of integers.
  
  Characters in 'pseudobytes' are converted to integers."
  [realbytes pseudobytes]
  (= realbytes (map int pseudobytes)))


(deftest building
  (is (compbytes
    (length-prefix (e8 "foo"))
    [0 3 \f \o \o])
  "basic length-prefixing of a small string.")

  (is 
    (thrown? AssertionError
             (length-prefix (e8 (apply str (repeat 65536 "x")))))
    "length-prefix only works on sequences whose lengths fit in two bytes.")
  )
