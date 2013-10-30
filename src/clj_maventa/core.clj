(ns clj-maventa.core
  (:require [necessary-evil [core :as xml-rpc]]
            [environ.core :as environ]
            [clj-time [format :as ft]]))

(def vendor-key (environ/env :maventa-vendor-api-key)) 
(def endpoint (if (= (environ/env :environment) "PRODUCTION")
                "https://secure.maventa.com/apis/denver/api"
                "https://testing.maventa.com/apis/denver/api"))

(defn- request
  [method api-keys & other]
  (apply xml-rpc/call endpoint method (merge {:vendor_api_key vendor-key} api-keys) other))


(defn company-info
  [params]
  (request :company_show params))

(def date-formatter (ft/formatter "yyyyMMdd"))

(defn create-invoice
  [api-keys customer invoice invoice-rows]
  (let [invoice-date (ft/unparse date-formatter (:date invoice))
        due-date (ft/unparse date-formatter (:date_due invoice))]
    (request :invoice_create api-keys (merge invoice {:items invoice-rows, :customer customer, :date invoice-date, :date_due due-date}))))

(defn show-invoice
  ([api-keys invoice-id] (show-invoice api-keys invoice-id false))
  ([api-keys invoice-id fetch-attachments]
   (request :invoice_show api-keys invoice-id fetch-attachments)))
