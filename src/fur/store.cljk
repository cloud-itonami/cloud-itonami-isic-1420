(ns fur.store
  "In-memory store for fur-garment/accessory manufacturing plant operations
  state. Plants in this domain receive already-dressed-and-dyed fur pelts
  (dressing/dyeing is a distinct upstream ISIC class, e.g. tanning and
  dressing of leather / dressing and dyeing of fur) and cut, match and sew
  them into finished garments and accessories on cutting/sewing lines. This
  is a reference implementation; production systems would use Datomic or a
  similar persistent event store for audit and replay.")

;; ----------------------------- store initialization -----------------------------

(defn mem-store
  "Create an in-memory store with reference data for fur-garment manufacturing."
  []
  {:data (atom {
           :plants {
             "plant-001" {:name "Community Fur Garment Atelier A"
                         :location "Italy"
                         :registered? true
                         :jurisdiction :ITA}}
           :production-batches {
             "batch-001" {:plant "plant-001"
                         :style "shearling-coat-L"
                         :quantity 40
                         :verified? true
                         :quality-grade "standard"
                         :pelt-source "farmed-mink"}
             "batch-002" {:plant "plant-001"
                         :style "fox-trim-hood-M"
                         :quantity 25
                         :verified? false
                         :quality-grade "standard"
                         :pelt-source "farmed-fox"}}
           :shipments {
             "ship-001" {:batch "batch-001"
                        :destination "wholesale-buyer-A"
                        :qty 40
                        :scheduled-date "2026-08-01"
                        :status :pending}
             "ship-002" {:batch "batch-002"
                        :destination "wholesale-buyer-B"
                        :qty 25
                        :scheduled-date "2026-08-05"
                        :status :pending}}
           :maintenance-log {
             "maint-001" {:equipment "fur-cutting-table-02"
                         :last-service "2026-06-20"
                         :status :operational}}})})

;; ----------------------------- accessors -----------------------------

(defn plant
  "Get plant record by ID."
  [st plant-id]
  (get-in @(:data st) [:plants plant-id]))

(defn production-batch
  "Get production batch record by ID."
  [st batch-id]
  (get-in @(:data st) [:production-batches batch-id]))

(defn shipment
  "Get shipment record by ID."
  [st shipment-id]
  (get-in @(:data st) [:shipments shipment-id]))

(defn equipment
  "Get equipment maintenance record by ID."
  [st equipment-id]
  (get-in @(:data st) [:maintenance-log equipment-id]))

;; ----------------------------- guards -----------------------------

(defn plant-verified?
  "Check if plant is registered and authorized."
  [st plant-id]
  (let [p (plant st plant-id)]
    (:registered? p false)))

(defn batch-verified?
  "Check if production batch is verified."
  [st batch-id]
  (let [b (production-batch st batch-id)]
    (:verified? b false)))

(defn batch-plant-verified?
  "Check if batch's plant is verified."
  [st batch-id]
  (let [b (production-batch st batch-id)
        plant-id (:plant b)]
    (plant-verified? st plant-id)))

(defn shipment-batch-id
  "Resolve the production-batch ID a shipment refers to. A shipment's
  :subject on a proposal is a shipment ID, NOT a batch ID directly -- this
  indirection must be resolved explicitly before checking plant/batch
  verification for a shipment-coordination proposal, otherwise the
  verification check silently no-ops or silently always-fails against the
  wrong keyspace."
  [st shipment-id]
  (:batch (shipment st shipment-id)))
