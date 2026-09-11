(ns marketentry.registry
  "Pure-function market-entry filing-draft + filing-submit record
  construction -- an append-only market-entry book-of-record draft.

  Like every sibling actor's registry, there is no single international
  reference-number standard for a public-procurement market-entry
  filing -- every jurisdiction assigns its own format. This namespace
  does NOT invent one; it builds a jurisdiction-scoped sequence number
  and validates the record's required fields, the same honest,
  non-fabricating discipline `marketentry.facts` uses.

  `engagement-fee-matches-claim?` is an HONEST reapplication of the
  SAME ground-truth-recompute DISCIPLINE sibling actors use (verify a
  claimed monetary total against the entity's own recorded quantity x
  unit fields), reapplied to a market-entry engagement fee line.

  `procurement-tier-for-value` / `procurement-tier-understated?` are the
  SAME discipline applied to a genuinely Kiribati-specific mechanism:
  the Public Procurement Manual 2020's own §7.1.6 'Thresholds', a
  FOUR-TIER AUD ladder (VLVP < 1,000; LVP 1,000-9,999; MVP 10,000-
  49,999; HVP >= 50,000) read directly off the Manual's own primary
  text (`marketentry.facts` has the full research trail). Distinct from
  this family's Fiji catalog (a flat 2-tier split) and Samoa catalog (a
  3-category x 6-tier ladder), the Manual's own §7.1.6 additionally
  states an EXPLICIT accumulation rule this namespace reapplies
  verbatim: 'the values are accumulated for the total Contract or
  Framework Agreement' -- its own worked example is a recurring
  monthly-rate consultant engagement (AUD 4,000/month x 18 months =
  AUD 72,000, therefore HVP). `compute-procurement-value` honestly
  reapplies that SAME accumulation arithmetic (rate x duration) to a
  declared engagement, and `procurement-tier-understated?` independently
  recomputes which tier the engagement's own accumulated value actually
  falls into, HARD-flagging when the engagement declares a LOWER-
  oversight tier than its own numbers support -- exactly the evasion the
  Manual's own worked example warns a naively-declared monthly rate can
  quietly produce.

  `reserved-activity-violation?` is a SECOND, independently-researched
  Kiribati mechanism this vertical adds: the Foreign Investment Act
  2018's own subsidiary Foreign Investment Guideline, Schedule 1
  ('Reserved List') -- an ABSOLUTE BAR (not a threshold, not a joint-
  venture-percentage condition) on foreign investors engaging in
  activities reserved for Kiribati citizens only. This is a genuinely
  DIFFERENT check SHAPE than the flagship's numeric-threshold recompute:
  a set-membership bar, entity-scope-gated on the engagement's own
  declared `:foreign-investor?` flag.

  This namespace is pure data + pure functions -- no I/O, no network
  call to any real procurement portal. It builds the RECORD an operator
  would keep, not the act of submitting a portal registration itself
  (that is `marketentry.operation`'s `:filing/submit`, always
  human-gated -- see README Actuation)."
  (:require [kotoba.lang.text :as str]))

(defn- unsigned-certificate
  "Every certificate this actor produces is UNSIGNED -- signature is
  the market-entry operator's act, not this actor's."
  [kind subject record-id]
  {"@context" ["https://www.w3.org/ns/credentials/v2"]
   "type" ["VerifiableCredential" kind]
   "credentialSubject" {"id" subject "record" record-id}
   "proof" nil
   "issued_by_registry" false
   "status" "draft-unsigned"})

(defn- zero-pad [n w]
  (let [s (str n)]
    (str (apply str (repeat (max 0 (- w (count s))) "0")) s)))

(defn compute-engagement-fee
  "The ground-truth engagement fee for `engagement`'s own `:base-fee`
  and `:monitoring-months` x `:monthly-rate` -- a single flat
  base + months x rate calculation, not a full pricing engine."
  [{:keys [base-fee monthly-rate monitoring-months]}]
  (+ (double base-fee)
     (* (double monthly-rate) (double monitoring-months))))

(defn engagement-fee-matches-claim?
  "Does `engagement`'s own `:claimed-fee` equal the independently
  recomputed `compute-engagement-fee`?"
  [{:keys [claimed-fee] :as engagement}]
  (== (double claimed-fee) (compute-engagement-fee engagement)))

(def procurement-tiers-aud
  "Public Procurement Manual 2020, own §7.1.6 'Thresholds' (own primary
  text, read directly, text-native PDF, not a delegated/unread number):
  'VLVP AUD<1.000 / LVP AUD1.000-9.999 / MVP AUD10.000-49.999 / HVP
  AUD50,000>'. Ordered lowest-oversight-first; VLVP/LVP are managed
  entirely by the Procuring Entity itself (Manual §6.3.1.1), MVP
  requires Contract Award Committee review (Public Procurement Act 2019
  s.18), HVP requires Central Contract Award Board review (Act s.17)."
  [{:tier :vlvp :max-exclusive 1000.0}
   {:tier :lvp :max-exclusive 10000.0}
   {:tier :mvp :max-exclusive 50000.0}
   {:tier :hvp :max-exclusive nil}])

(def tier-rank
  "Lowest oversight (VLVP) to highest (HVP) -- used to detect when a
  declared tier UNDERSTATES the independently-recomputed true tier."
  {:vlvp 0 :lvp 1 :mvp 2 :hvp 3})

(defn compute-procurement-value
  "Independently recompute the TOTAL accumulated procurement contract
  value (AUD) for `engagement`, honoring the Public Procurement Manual
  2020's own §7.1.6 accumulation rule: a recurring monthly-rate
  engagement's value is `:declared-procurement-monthly-rate-aud` x
  `:declared-procurement-duration-months` (the Manual's own worked
  example: AUD 4,000/month x 18 months = AUD 72,000); a lump-sum
  engagement uses its own declared `:declared-procurement-value-aud`
  directly. Returns nil when neither is present (does not throw --
  nothing to independently recompute)."
  [{:keys [declared-procurement-value-aud
           declared-procurement-monthly-rate-aud
           declared-procurement-duration-months]}]
  (cond
    (and (some? declared-procurement-monthly-rate-aud)
         (some? declared-procurement-duration-months))
    (* (double declared-procurement-monthly-rate-aud)
       (double declared-procurement-duration-months))

    (some? declared-procurement-value-aud)
    (double declared-procurement-value-aud)

    :else nil))

(defn procurement-tier-for-value
  "Which of VLVP/LVP/MVP/HVP `value` (AUD) independently falls into, per
  the Public Procurement Manual 2020's own §7.1.6 thresholds. A nil
  `value` yields a nil tier (fails, does not throw)."
  [value]
  (when (some? value)
    (let [v (double value)]
      (cond
        (< v 1000.0) :vlvp
        (< v 10000.0) :lvp
        (< v 50000.0) :mvp
        :else :hvp))))

(defn procurement-tier-understated?
  "Does `engagement` declare a LOWER-oversight tier
  (`:declared-procurement-tier`) than the INDEPENDENTLY recomputed tier
  its own accumulated declared value actually requires? VLVP/LVP is
  entirely Procuring-Entity self-managed (Manual §6.3.1.1); MVP requires
  Contract Award Committee review (Act s.18); HVP requires Central
  Contract Award Board review and Ministerial sign-off (Act s.17, Manual
  §6.3.1.3). Understating the tier -- e.g. declaring a recurring
  monthly-rate engagement as VLVP/LVP self-managed when its own
  accumulated value actually sits in the MVP/HVP band, exactly the
  Manual's own worked-example scenario -- is the evasion this check
  independently recomputes and HARD-holds. An engagement with no
  declared tier claim is never flagged (nothing to contradict, the same
  entity/engagement-scope-gating discipline every sibling actor's own
  threshold/eligibility check uses)."
  [{:keys [declared-procurement-tier] :as engagement}]
  (boolean
   (when declared-procurement-tier
     (when-let [true-tier (procurement-tier-for-value
                            (compute-procurement-value engagement))]
       (< (get tier-rank declared-procurement-tier 0)
          (get tier-rank true-tier 0))))))

(def reserved-activities
  "Foreign Investment Act 2018's own subsidiary Foreign Investment
  Guideline (Kiribati), Schedule 1 'Reserved List' -- activities
  reserved for Kiribati citizens only; the Guideline's own text (read
  directly, text-native PDF, this session): 'Foreign Investors and
  companies with foreign shareholders, directors, and/or employees are
  not allowed to engage in any of these reserved activities.'"
  #{:bus-transport-services :taxi-transport-services :rental-vehicles
    :distribution-sector-retail-wholesale :tour-guide-services
    :handicraft-production :saw-milling
    :artisanal-coastal-reef-fishing :inshore-commercial-fishing-12nm
    :traditional-tibuta-garment})

(defn reserved-activity-violation?
  "Does `engagement` declare itself a foreign investor
  (`:foreign-investor?` true) engaging in a Schedule-1 Reserved activity
  (`:declared-activity`)? The Foreign Investment Guideline's own
  Schedule 1 text is an ABSOLUTE BAR, not a joint-venture-percentage
  condition (that is Schedule 2's own Restricted List, a DIFFERENT,
  less-strict regime this namespace does not conflate with Schedule 1).
  An engagement that does not declare itself a foreign investor, or
  whose declared activity is not on the Reserved List, is never flagged
  (entity/activity-scope-gated, the same discipline this family's
  Bhutan foreign-company-gated FDI check and CAF reserved-market-gated
  check use)."
  [{:keys [foreign-investor? declared-activity]}]
  (boolean (and foreign-investor?
                (contains? reserved-activities declared-activity))))

(defn register-draft
  "Validate + construct the FILING-DRAFT registration DRAFT -- the
  market-entry operator's own act of preparing a portal registration
  package. Pure function -- does not touch any real procurement
  portal."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "draft: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "draft: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "draft: sequence must be >= 0" {})))
  (let [draft-number (str (str/upper jurisdiction) "-DFT-" (zero-pad sequence 6))
        record {"record_id" draft-number
                "kind" "filing-draft"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "draft_number" draft-number
     "certificate" (unsigned-certificate "FilingDraft" draft-number draft-number)}))

(defn register-submit
  "Validate + construct the FILING-SUBMIT registration DRAFT -- the
  market-entry operator's own act of actually submitting a portal
  registration (always human-gated upstream)."
  [engagement-id jurisdiction sequence]
  (when-not (and engagement-id (not= engagement-id ""))
    (throw (ex-info "submit: engagement_id required" {})))
  (when-not (and jurisdiction (not= jurisdiction ""))
    (throw (ex-info "submit: jurisdiction required" {})))
  (when (< sequence 0)
    (throw (ex-info "submit: sequence must be >= 0" {})))
  (let [submit-number (str (str/upper jurisdiction) "-SUB-" (zero-pad sequence 6))
        record {"record_id" submit-number
                "kind" "filing-submit"
                "engagement_id" engagement-id
                "jurisdiction" jurisdiction
                "immutable" true}]
    {"record" record "submit_number" submit-number
     "certificate" (unsigned-certificate "FilingSubmit" submit-number submit-number)}))

(defn append [history result]
  (conj (vec history) (get result "record")))
