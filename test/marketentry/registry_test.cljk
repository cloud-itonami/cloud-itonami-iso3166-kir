(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 860000.0}]
    (is (== 860000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 500000 :monthly-rate 30000 :monitoring-months 12 :claimed-fee 999000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "KIR" 0)
        s (registry/register-submit "eng-1" "KIR" 0)]
    (is (= "KIR-DFT-000000" (get d "draft_number")))
    (is (= "KIR-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "KIR" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

;; ----------------- procurement-tier (FLAGSHIP) -----------------

(deftest procurement-tier-for-value-boundaries
  (testing "Public Procurement Manual 2020 §7.1.6: VLVP<1,000 / LVP 1,000-9,999 / MVP 10,000-49,999 / HVP>=50,000"
    (is (= :vlvp (registry/procurement-tier-for-value 0.0)))
    (is (= :vlvp (registry/procurement-tier-for-value 999.99)))
    (is (= :lvp (registry/procurement-tier-for-value 1000.0)))
    (is (= :lvp (registry/procurement-tier-for-value 9999.99)))
    (is (= :mvp (registry/procurement-tier-for-value 10000.0)))
    (is (= :mvp (registry/procurement-tier-for-value 49999.99)))
    (is (= :hvp (registry/procurement-tier-for-value 50000.0)))
    (is (= :hvp (registry/procurement-tier-for-value 1000000.0)))
    (is (nil? (registry/procurement-tier-for-value nil)))))

(deftest compute-procurement-value-accumulates-recurring-engagements
  (testing "Manual's own worked example: AUD 4,000/month x 18 months = AUD 72,000"
    (is (== 72000.0 (registry/compute-procurement-value
                     {:declared-procurement-monthly-rate-aud 4000.0
                      :declared-procurement-duration-months 18})))
    (is (= :hvp (registry/procurement-tier-for-value
                (registry/compute-procurement-value
                 {:declared-procurement-monthly-rate-aud 4000.0
                  :declared-procurement-duration-months 18})))))
  (testing "lump-sum declared value is used directly when no monthly-rate/duration present"
    (is (== 25000.0 (registry/compute-procurement-value
                     {:declared-procurement-value-aud 25000.0}))))
  (testing "no declared value at all -> nil (fails, does not throw)"
    (is (nil? (registry/compute-procurement-value {})))))

(deftest procurement-tier-understated-catches-the-manuals-own-scenario
  (testing "declaring LVP for a recurring engagement whose accumulated value is actually HVP -> understated"
    (is (true? (registry/procurement-tier-understated?
                {:declared-procurement-tier :lvp
                 :declared-procurement-monthly-rate-aud 4000.0
                 :declared-procurement-duration-months 18}))))
  (testing "declaring the correct (or higher) tier is never flagged"
    (is (false? (registry/procurement-tier-understated?
                {:declared-procurement-tier :hvp
                 :declared-procurement-monthly-rate-aud 4000.0
                 :declared-procurement-duration-months 18})))
    (is (false? (registry/procurement-tier-understated?
                {:declared-procurement-tier :mvp
                 :declared-procurement-value-aud 25000.0}))))
  (testing "an engagement with no declared tier claim is never flagged (entity-scope-gated)"
    (is (false? (registry/procurement-tier-understated?
                {:declared-procurement-value-aud 999999.0})))))

;; ----------------- reserved-activity (SECOND check) -----------------

(deftest reserved-activity-violation-is-entity-and-activity-scope-gated
  (testing "a foreign investor declaring a Schedule 1 Reserved activity -> violation"
    (is (true? (registry/reserved-activity-violation?
                {:foreign-investor? true :declared-activity :taxi-transport-services}))))
  (testing "a domestic (non-foreign-investor) engagement is never flagged, even for the same activity"
    (is (false? (registry/reserved-activity-violation?
                {:foreign-investor? false :declared-activity :taxi-transport-services}))))
  (testing "a foreign investor declaring a non-reserved activity is not flagged by this check"
    (is (false? (registry/reserved-activity-violation?
                {:foreign-investor? true :declared-activity :consulting-services})))))
