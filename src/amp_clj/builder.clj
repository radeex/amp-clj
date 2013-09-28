(ns amp-clj.builder
  (:require [clj-struct.core :as struct]))


(defn length-prefix
  "Prefixes a sequence with its 2-byte length."
  [sequence]
  (let [length (count sequence)]
    (concat (struct/pack "!H" length)
            sequence)))
