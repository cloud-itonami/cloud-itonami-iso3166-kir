(ns marketentry.facts-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.facts :as facts]))

(deftest kir-has-spec-basis
  (let [sb (facts/spec-basis "KIR")]
    (is (some? sb))
    (is (string? (:provenance sb)))
    (is (seq (:required-evidence sb)))
    (is (some? (facts/corporate-number-spec-basis "KIR")))
    (is (some? (facts/procurement-tier-spec-basis "KIR")))
    (is (some? (facts/reserved-activities-spec-basis "KIR")))))

(deftest kir-rep-spec-basis-is-honestly-absent
  (testing "a local-agent requirement was confirmed only for a specific Restricted-List activity (Maritime Agency Services), not as a general public-procurement-bidder requirement -- deliberately not claimed"
    (is (nil? (facts/rep-spec-basis "KIR")))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest required-evidence-satisfied
  (let [sb (facts/spec-basis "KIR")
        all (:required-evidence sb)]
    (is (true? (facts/required-evidence-satisfied? "KIR" all)))
    (is (not (facts/required-evidence-satisfied? "KIR" (take 1 all))))
    (is (nil? (facts/required-evidence-satisfied? "ATL" all)))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["KIR" "USA" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 2 (:covered c)))
    (is (= ["ATL"] (:missing-jurisdictions c)))))

(deftest procurement-tier-spec-basis-criteria
  (let [pt (facts/procurement-tier-spec-basis "KIR")]
    (is (= "AUD" (get-in pt [:procurement-tier-criteria :currency])))
    (is (= 4 (count (get-in pt [:procurement-tier-criteria :tiers]))))))

(deftest reserved-activities-spec-basis-criteria
  (let [ra (facts/reserved-activities-spec-basis "KIR")]
    (is (contains? (:reserved-activities-criteria ra) :taxi-transport-services))
    (is (contains? (:reserved-activities-criteria ra) :traditional-tibuta-garment))))
