(ns amp-clj.test.junk)

(defn e8 "Encode a string to bytes with utf-8."
  [string] (.getBytes string "UTF-8"))

(def e8v (comp vec e8))


(defn d8 "Decode a bytes to a string with utf-8."
  [bytes] (String. (byte-array bytes) "UTF-8"))


(defn compbytes
  "Compare two sequences of integers.

  Characters in 'pseudobytes' are converted to integers."
  [realbytes pseudobytes]
  (= realbytes (map int pseudobytes)))


(defn bytemap
  "Encode {string string} to {bytes bytes}."
  [strings]
  (into {} (for [[k v] strings] [(e8 k) (e8 v)])))


(defn get-bytes-from-bytebuf
  [bytebuf]
  (let [len (.remaining bytebuf)
        bytes (byte-array len)]
    (.get bytebuf bytes 0 len)
    (vec bytes)))