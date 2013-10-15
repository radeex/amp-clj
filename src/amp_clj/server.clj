(ns amp-clj.server
  (:require [lamina.core :as lamina])
  (:require [aleph.tcp])
  (:require [amp-clj.codec :as codec])
  (:use [amp-clj.junk]))


; (defn- conn-received [channel client-info]
;   (lamina/receive-all channel
;     #(lamina/enqueue channel %)))


; (defn echo-server
;   []
;   (aleph.tcp/start-tcp-server conn-received {:port 1234}))


; (amp-server my-handlers port)


(def dispatch-table
  {(e8v "Sum") (fn [arguments]
    {(e8v "total")
     (e8v (+ (Long. (d8v (arguments (e8v "a"))))
             (Long. (d8v (arguments (e8v "b"))))))}),
  })

(defn dispatch
  "Given a box and a function table, invoke the appropriate handler in the
  table with the box."
  [box table]
  (let [command-name (box (e8v "_command"))
        handler (table command-name)]
        (handler box)
        ))

(defn conn-received [channel client-info]
  (lamina/receive-all channel (fn [argument]
    (println "Result of dispatching: " (dispatch (into {} argument) dispatch-table))
    )))


(defn -main []
  (aleph.tcp/start-tcp-server
    conn-received
    {:port 1234
     :frame codec/box})
  )