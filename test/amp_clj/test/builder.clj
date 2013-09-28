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


(defn bytemap
  "Convert a conveniently-defined map of strings to strings to a map of
  bytes to bytes."
  [strings]
  (apply array-map
    (flatten 
      (for [[key value] strings] [(e8 key) (e8 value)]))))


(deftest prefixing
  (is (compbytes
    (length-prefix (e8 "foo"))
    [0 3 \f \o \o])
  "basic length-prefixing of a small string.")

  (is
    (thrown? AssertionError
             (length-prefix (e8 (apply str (repeat 65536 "x")))))
    "length-prefix only works on sequences whose lengths fit in two bytes."))


(deftest boxes
  (is (compbytes
    (build-box (bytemap {"foo" "bar"}))
    [0 3 \f \o \o 0 3 \b \a \r 0 0])
    "Single key/value pair followed by NUL NUL delimiter.")

  (is (compbytes
    (build-box (bytemap {"foo" "bar" "baz" "quux"}))
    [0 3 \f \o \o 0 3 \b \a \r 0 3 \b \a \z 0 4 \q \u \u \x 0 0])
    "Two key/value pairs, followed by NUL NUL delimiter."))


(deftest commands
  (is (=
    (build-command "mycmd" "abc" {"a" "b" "c" "d"})
    {
      "_command" "mycmd"
      "_ask" "abc"
      "a" "b"
      "c" "d"})))
