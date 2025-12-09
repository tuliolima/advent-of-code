(ns y2025.day-2.core
  (:require [clojure.string :as s]))

(defn check-invalid [num]
  (let [str-num (str num)
        len (count str-num)
        half (/ len 2)]
    [num (= (subs str-num 0 half)
            (subs str-num half len))]))

(def sum-of-invalids
  (->> (slurp "src/y2025/day_2/input.txt")
       (s/trim-newline)
       (#(s/split % #","))
       (map #(s/split % #"-"))
       (map #(-> (range (Long. (first %)) (Long. (second %)))))
       (apply concat)
       (map check-invalid)
       (filter #(true? (second %)))
       (map first)
       (apply +)))

(println "Sum of invalid numbers:" sum-of-invalids)
