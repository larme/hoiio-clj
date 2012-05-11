(ns hoiio-clj.client
  (:import (com.hoiio.sdk HoiioService)))

(defn- hoiio-client*
  "Get a HoiioService instance for the supplied credentials"
  [cred]
  (HoiioService. (:app-id cred) (:app-key cred)))

(def hoiio-client
  (memoize hoiio-client*))
