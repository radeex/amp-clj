(ns amp-clj.junk
  (:require [gloss.core :as gloss])
  (:require [gloss.io :as io])
  (:import java.nio.ByteBuffer)
)

(defn e8v
  "Encode a string to a vector of bytes with utf-8.

  If the argument is not a string, it will be returned."
  [string]
  (if (string? string)
    (vec (.getBytes string "UTF-8"))
    string))

(defn d8v
  "Decode a vector of bytes (optionally as integers) to a String with UTF-8"
  [bytes]
  (String. (byte-array (map byte bytes)) "UTF-8"))

(defn compbytes
  "Compare two sequences of integers.

  Characters in 'pseudobytes' are converted to integers."
  [realbytes pseudobytes]
  (= realbytes (map int pseudobytes)))


(defn bytemap
  "Encode {(string or bytes) (string or bytes)} to {bytes bytes}."
  [strings]
  (into {} (for [[k v] strings] [(e8v k) (e8v v)])))


(defn get-bytes-from-bytebuf
  "Return a vector of bytes from a ByteBuffer."
  [bytebuf]
  (let [len (.remaining bytebuf)
        bytes (byte-array len)]
    (.get bytebuf bytes 0 len)
    (vec bytes)))


(defn encode
  "Encode the given input with the given codec, and return a byte vector."
  [codec input]
  (get-bytes-from-bytebuf (io/contiguous (io/encode (gloss/compile-frame codec) input)))
  )


(defn bytebuf-from-bytes
  "Turn a sequence of byte-able values into a ByteBuffer.

  The ByteBuffer's position will be reset to 0 before returning."
  [bytes]
  ; Man. Is this really the easiest way to populate a ByteBuffer?
  (let [bb (ByteBuffer/allocate (count bytes))]
    (doseq [byte bytes]
      (.put bb (.byteValue (int byte))))
    (.position bb 0)))


(defn decode
  "Decode the given bytes with the given codec. Returns the parsed structure."
  [codec bytes]
    (io/decode (gloss/compile-frame codec) (bytebuf-from-bytes bytes)))


(defn readable
  [bytes]
  (println bytes)
  (map #(if (and (> % 32) (< % 126)) (str (char %)) %) bytes))