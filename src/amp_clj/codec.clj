(ns amp-clj.codec
  (:use [gloss.core :as gloss]))


(def prefix16 (repeated :byte :prefix :int16))
(def keyval [prefix16 prefix16])
(def box (repeated keyval :prefix :none :delimiters ["\0\0"]))

