(ns amp-clj.command
  "Functions for dealing with AMP requests ('commands') and responses."
  (:require [amp-clj.junk :as junk]))


(defn build-command
  "Produce a command box as a mapping of bytes to bytes, ready to be serialized
  by the AMP codec.

  - command: The name of the command. Can be a string or bytes. Will be
    included as the _command key.
  - id: The unique message ID. String or bytes. Will be included as the _ask
    key.
  - args: A mapping of strings or bytes to strings or bytes. Will be included
    as the rest of the arguments to the command.
  "
  [command id args]
  (junk/bytemap
    (conj args {"_command" command "_ask" id})))

; TODO:
; - client
;   - state management for requests / responses (umm... Deferreds?)
;   - support for all the AMP types
; - server
;   - dispatcher for commands
;   - support for all the AMP types

; probably want amp-types.clj
; not sure if I should separate dispatcher and request/response state manager
; into separate files.
