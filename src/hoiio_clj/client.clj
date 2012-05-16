(ns hoiio-clj.client
  (:import (com.hoiio.sdk HoiioService)))

(defn- hoiio-client*
  "Get a HoiioService instance for the supplied credentials"
  [cred]
  (HoiioService. (:app-id cred) (:app-key cred)))

(def hoiio-client
  (memoize hoiio-client*))

(defn send-sms
  "send a sms"
  [cred dest msg & {:keys [sender-name, tag, notify-url]}]
  (.smsSend
   (hoiio-client cred)
   dest sender-name msg tag notify-url))

(defn ivr-dial
  "make a outgoing IVR call"
  [cred dest & {:keys [msg, caller-id, tag, notify-url]}]
  (.ivrDial
   (hoiio-client cred)
   msg, dest, caller-id, tag, notify-url))

(defn ivr-play
  "IVR play the message"
  [cred session & {:keys [msg tag notify-url]}]
  (.ivrPlay
   (hoiio-client cred)
   session msg tag notify-url))

(defn ivr-gather
  "IVR gather"
  [cred session notify-url & {:keys [msg max-digits timeout attempts tag]}]
  (.ivrGather
   (hoiio-client cred)
   session msg max-digits timeout attempts tag notify-url))

(defn ivr-record
  "IVR Record"
  [cred session notify-url & {:keys [msg max-duration tag]}]
  (.ivrRecord
   (hoiio-client cred)
   session msg max-duration tag notify-url))

(defn ivr-transfer
  "IVR Transfer"
  [cred session dest & {:keys [msg caller-id tag notify-url]}]
  (.ivrTransfer
   (hoiio-client cred)
   session msg dest caller-id tag notify-url))

(defn ivr-hangup
  "IVR Hangup"
  [cred session & {:keys [msg tag notify-url]}]
  (.ivrHangup
   (hoiio-client cred)
   session msg tag notify-url))
