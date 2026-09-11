(ns statute.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest kir-has-spec-basis
  (let [sb (facts/spec-basis "KIR")]
    (is (= 5 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["KIR" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["kir.companies-ordinance-cap10a"]
         (mapv :statute/id (facts/by-topic "KIR" :corporate-governance))))
  (is (= ["kir.foreign-investment-act-2018"]
         (mapv :statute/id (facts/by-topic "KIR" :foreign-investment))))
  (is (= 2 (count (facts/by-topic "KIR" :labor)))
      "EIRC 2015 and OHS 2015 are both tagged :labor")
  (is (= ["kir.income-tax-ordinance"]
         (mapv :statute/id (facts/by-topic "KIR" :tax))))
  (is (empty? (facts/by-topic "KIR" :data-protection))
      "no data-protection statute independently confirmed this iteration -- honestly absent, see namespace docstring")
  (is (empty? (facts/by-topic "ATL" :labor))))
