(ns amp-clj.builder
  "Functions for building AMP messages."
  (:require [clj-struct.core :as struct]))


(defn length-prefix
  "Prefixes a sequence with its 2-byte length.

  e.g., (length-prefix [5 2 3]) -> [0 3 5 2 3]
  "
  [sequence]
  (let [length (count sequence)]
    (concat (struct/pack "!H" length)
            sequence)))

(defn build-box
  "Build an AMP box out of a mapping.

  - mapping: mapping of bytes to bytes, representing the keys and values in
    the box.

  An AMP box is a sequence of alternating length-prefixed keys and values, with
  an ending delimiter of two 0 bytes."
  [mapping]
  (concat
    (apply concat (for [[k v] mapping]
      (concat (length-prefix k)
              (length-prefix v))))
    (struct/pack "!H" 0)))



(defn build-command
  [command id args]
  (conj args {"_command" command "_ask" id}))

; TODO:
; - (build-command "command-name" id args) -> bytes
; - 
