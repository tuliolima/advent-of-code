(ns y2022.day-3.core
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(def input (slurp "src/y2022/day_3/input.txt"))

(def rucksacks (s/split input #"\n"))

(defn priority [letter]
  (let [letters "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"]
    (s/index-of letters letter)))

(def total-priority
  (->> rucksacks
       (map #(split-at (/ (count %) 2) %))
       (map #(set/intersection (set (first %))
                               (set (second %))))
       (map #(priority (first %)))
       (apply +)))

(prn (str "The total priority is " total-priority "."))

(def total-group-priority
  (->> rucksacks
       (partition-all 3)
       (map #(set/intersection (set (first %))
                               (set (second %))
                               (set (last %))))
       (map first)
       (map priority)
       (apply +)))

(prn (str "The total group priority is " total-group-priority "."))