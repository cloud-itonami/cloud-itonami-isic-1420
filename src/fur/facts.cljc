(ns fur.facts
  "Per-jurisdiction fur-garment/accessory manufacturing compliance
  requirements. Every jurisdiction in this catalog is backed by an official
  spec-basis. NEVER invent requirements without an official citation.

  :GBR is a genuinely different shape from USA/ITA/CAN: alongside labeling,
  the UK prohibits domestic fur farming outright (a production-side ban),
  and its labeling requirement is a retained-EU-law citation shared
  verbatim with :ITA's Article 12.

  :NLD adds a second fur-farming-prohibition jurisdiction, but via a
  completed PHASE-OUT rather than GBR's clean immediate ban: the Wet
  verbod pelsdierhouderij (2013) originally allowed existing mink farms
  to continue under a transitional exemption, then a 2020 amendment
  accelerated that exemption's end date. Citations verified 2026-07-22
  directly against wetten.overheid.nl (the Dutch government's own
  official consolidated-legislation portal, BWBR0032739).

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
                      :evidence [:wage-record :safety-training]}}}

   ;; United Kingdom -- genuinely different regulatory story from USA/ITA/CAN,
   ;; WebFetch-verified 2026-07-21 directly against legislation.gov.uk (The
   ;; National Archives' official UK legislation site): the UK does not just
   ;; regulate fur-garment labeling/sourcing, it PROHIBITS domestic fur
   ;; farming outright (a production-side ban, not a labeling rule), while
   ;; separately retaining the same EU Article 12 labeling regulation ITA
   ;; cites -- confirmed still current UK law post-Brexit (legislation.gov.uk
   ;; hosts retained EU law directly; this specific regulation has not been
   ;; superseded by new UK legislation).
   :GBR
   {:name "United Kingdom"
    :requirements
    {:fur-farming-prohibition {:description "Keeping animals solely or primarily for slaughter for the value of their fur is a criminal offence (a domestic-production ban, not a labeling/sourcing rule like the other jurisdictions in this catalog)"
                              :required true
                              :spec-basis "Fur Farming (Prohibition) Act 2000, c. 33, s. 1 (Offences relating to fur farming); in force in England and Wales from 1 January 2003"
                              :evidence [:no-domestic-fur-farming-attestation]}
     :non-textile-animal-origin-labeling {:description "Garments containing fur parts must bear the mandatory disclosure phrase on the label -- the same EU regulation ITA cites, retained as UK law post-Brexit"
                                          :required true
                                          :spec-basis "Regulation (EU) No 1007/2011, Article 12, retained/incorporated as UK law (legislation.gov.uk/eur/2011/1007/article/12) -- not superseded by any new UK legislation as of this iteration's research"
                                          :evidence [:garment-label :animal-origin-disclosure]}}}

   ;; Netherlands -- WebFetch/curl-verified 2026-07-22 directly against
   ;; wetten.overheid.nl (BWBR0032739, the Dutch government's own official
   ;; consolidated-legislation portal). A second fur-farming-prohibition
   ;; jurisdiction alongside GBR, but a completed PHASE-OUT rather than a
   ;; clean immediate ban: the original 2013 law allowed existing mink
   ;; farms to continue under a transitional exemption (Art. 3-4), then a
   ;; 16 December 2020 amendment (Stb. 2020, 555) accelerated that
   ;; exemption's end date -- confirmed via the amending law's own
   ;; cross-reference text in Art. 4 ("vervroegde beëindiging van de
   ;; pelsdierhouderij", accelerated termination of fur farming).
   :NLD
   {:name "Netherlands"
    :requirements
    {:fur-farming-prohibition {:description "Keeping, killing, or causing to be killed of a fur-bearing animal is prohibited (a domestic-production ban, like GBR's, following a completed transitional phase-out for pre-existing mink farms)"
                              :required true
                              :spec-basis "Wet verbod pelsdierhouderij (Wet van 4 januari 2013), Art. 2: 'Het houden, doden of doen doden van een pelsdier is verboden' -- transitional exemption for existing farms (Art. 3-4) accelerated to a full end by the Wet van 16 december 2020 tot wijziging van de Wet verbod pelsdierhouderij in verband met een vervroegde beëindiging van de pelsdierhouderij (Stb. 2020, 555)"
                              :evidence [:no-domestic-fur-farming-attestation]}
     :non-textile-animal-origin-labeling {:description "Garments containing fur parts must bear the mandatory disclosure phrase on the label -- the same EU regulation ITA/GBR cite"
                                          :required true
                                          :spec-basis "Regulation (EU) No 1007/2011, Article 12 (directly applicable EU law in the Netherlands)"
                                          :evidence [:garment-label :animal-origin-disclosure]}}}})

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
