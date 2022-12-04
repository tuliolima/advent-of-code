(ns y2022.day-4.core
  (:require [clojure.string :as s]))

(def input (slurp "src/y2022/day_4/input.txt"))

(def pairs (->> (s/split input #"\n")
                (map #(s/split % #","))))

(defn parse-range [range]
  (map #(Integer/parseInt %) (s/split range #"-")))

(defn range-contains? [range-a range-b]
  (let [[a-start a-end] (parse-range range-a)
        [b-start b-end] (parse-range range-b)]
    (and (<= a-start b-start) (>= a-end b-end))))

(defn full-overlaps? [assignment-pair]
  (or (range-contains? (first assignment-pair) (second assignment-pair))
      (range-contains? (second assignment-pair) (first assignment-pair))))

(def full-overlap-count
  (->> pairs
       (filter full-overlaps?)
       count))

(println "There are" full-overlap-count "pairs with full overlap.")

(defn any-overlaps? [assignment-pair]
  (let [[a-start a-end] (parse-range (first assignment-pair))
        [b-start b-end] (parse-range (second assignment-pair))]
    (and (<= a-start b-end) (>= a-end b-start))))

(def any-overlap-count
  (->> pairs
       (filter any-overlaps?)
       count))

(println "There are" any-overlap-count "pairs with any overlap.")