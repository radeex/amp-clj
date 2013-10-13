(ns amp-clj.server
  (:require [lamina.core :as lamina])
  (:require [aleph.tcp])
  (:require [amp-clj.codec :as codec])
  (:require [amp-clj.junk :as junk]))


; (defn- conn-received [channel client-info]
;   (lamina/receive-all channel
;     #(lamina/enqueue channel %)))


; (defn echo-server
;   []
;   (aleph.tcp/start-tcp-server conn-received {:port 1234}))


; (amp-server my-handlers port)

(defn conn-received [channel client-info]
  (lamina/receive-all channel (fn [argument]
    (println argument)
    (println "holy shit" (junk/readable (flatten argument))))))


(defn -main []
  (aleph.tcp/start-tcp-server
    conn-received
    {:port 1234
     :frame codec/box})
  )