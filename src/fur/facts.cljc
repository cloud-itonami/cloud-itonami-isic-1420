(ns fur.facts
  "Per-jurisdiction fur-garment/accessory manufacturing compliance
  requirements. Every jurisdiction in this catalog is backed by an official
  spec-basis. NEVER invent requirements without an official citation.

  This is deliberately a starting catalog (honest coverage reporting) to
  prove the governor contract end-to-end, not a claim of global coverage.
  Adding a jurisdiction is additive: one map entry citing a real official
  source -- never fabricate a jurisdiction's requirements to make coverage
  look bigger.")

;; ----------------------------- jurisdiction catalog -----------------------------

(def catalog
  "Per-jurisdiction fur-garment manufacturing compliance requirements with
  official spec-basis citations."
  {
   :USA
   {:name "United States"
    :requirements
    {:fur-labeling {:description "True species name and country-of-origin labeling on fur garments/accessories"
                   :required true
                   :spec-basis "Fur Products Labeling Act, 15 U.S.C. § 69; FTC Fur Rules, 16 CFR Part 301"
                   :evidence #{:garment-label :species-name-verified :country-of-origin-marking}}
     :species-sourcing {:description "CITES import/export permits and origin certification for regulated-species pelts"
                       :required true
                       :spec-basis "Endangered Species Act, 16 U.S.C. § 1538; CITES implementing regulations, 50 CFR Part 23"
                       :evidence [:cites-permit :species-origin-cert]}
     :labor-standards {:description "Compliance with Fair Labor Standards Act and workplace safety"
                      :required true
                      :spec-basis "FLSA 29 CFR § 516, OSHA 1910 Subpart A"
                      :evidence [:wage-hour-record :safety-training]}}}

   :ITA
   {:name "Italy"
    :requirements
    {:non-textile-animal-origin-labeling {:description "Garments containing fur parts must bear the mandatory disclosure phrase on the label"
                                          :required true
                                          :spec-basis "Regulation (EU) No 1007/2011 of the European Parliament and of the Council, Article 12"
                                          :evidence [:garment-label :animal-origin-disclosure]}
     :species-sourcing {:description "Wildlife-trade protection for pelts sourced from regulated species"
                       :required true
                       :spec-basis "Council Regulation (EC) No 338/97 on the protection of species of wild fauna and flora by regulating trade therein"
                       :evidence [:cites-permit :species-origin-cert]}
     :labor-standards {:description "Workplace health and safety compliance for plant personnel"
                      :required true
                      :spec-basis "Decreto Legislativo 9 aprile 2008, n. 81 (Testo Unico sulla Salute e Sicurezza sul Lavoro)"
                      :evidence [:risk-assessment :safety-training]}}}

   :CAN
   {:name "Canada"
    :requirements
    {:fur-labeling {:description "Fur content and species identification on garment/accessory labels"
                   :required true
                   :spec-basis "Textile Labelling Act, R.S.C. 1985, c. T-10; Textile Labelling and Advertising Regulations, C.R.C., c. 1551"
                   :evidence [:garment-label :fur-content-disclosure]}
     :labor-standards {:description "Federal labour-standards compliance for plant personnel"
                      :required true
                      :spec-basis "Canada Labour Code, R.S.C. 1985, c. L-2"
                      :evidence [:wage-record :safety-training]}}}})

;; ----------------------------- coverage reporting (honest) -----------------------------

(defn coverage
  "Report what fraction of worldwide jurisdictions have official spec-basis
  in this catalog. Honest about out-of-scope coverage."
  []
  (let [catalog-count (count catalog)
        world-jurisdictions 194]
    {:implemented catalog-count
     :worldwide-jurisdictions world-jurisdictions
     :coverage-pct (* 100.0 (/ catalog-count world-jurisdictions))
     :note "Starting catalog to prove governor contract end-to-end, not global coverage claim"}))

;; ----------------------------- helpers -----------------------------

(defn requirement-citations
  "Get all official citations for a jurisdiction's requirements."
  [jurisdiction]
  (get-in catalog [jurisdiction :requirements]))

(defn required-evidence-satisfied?
  "Check if a checklist satisfies this jurisdiction's evidence requirements."
  [jurisdiction checklist]
  (let [reqs (get-in catalog [jurisdiction :requirements])]
    (every? (fn [[_req-key req-spec]]
              (if (:required req-spec)
                (let [evidence-keys (set (:evidence req-spec))]
                  (every? #(contains? checklist %) evidence-keys))
                true))
            reqs)))
