(ns y2025.day-3.core
  (:require [clojure.string :as s]))

(defn calc-joltage [bank]
  (let [first-num (last (sort (drop-last bank)))
        first-index (s/index-of bank first-num)
        second-num (last (sort (subs bank (+ 1 first-index))))]
    (Integer. (str first-num second-num))))

(def total-joltage
  (->> (slurp "src/y2025/day_3/input.txt")
       s/split-lines
       (map calc-joltage)
       (apply +)))

(println "Total joltage of the banks:" total-joltage)