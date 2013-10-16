# clj-maventa

A Clojure library designed to ... well, that part is up to you.

## Usage

```clojure
(use 'clj-maventa.core :reload-all)

(create-invoice {:user_api_key "replace this", :company_uuid "replace this"} {:email "customer@company.com", :lang "FI", :name "My Customer"} {:invoice_nr "1234", :date invoice-date, :date_due due-date, :sum "100", :sum_tax "124"} [{:subject "Test", :tax "24", :sum "100", :sum_tax "124", :amount 1, :price "100"}])
;{:invoice_id "f64e8e17-25ac-4edb-b9bb-e90e3da3e67a", :status "OK: INVOICE CREATED"}
```

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
