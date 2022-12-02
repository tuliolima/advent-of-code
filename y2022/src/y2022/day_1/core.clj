(ns y2022.day-1.core
  (:require [clojure.string :as s]))

(def input (slurp "src/y2022/day_1/input.txt"))

(def elves (->> (s/split input #"\n\n")
                (map #(s/split % #"\n"))))

(defn parse-strings [string-list]
  (map #(Integer/parseInt %) string-list))

(def calories-of-first
  (->> elves
       (map #(apply + (parse-strings %)))
       (apply max)))

(def calories-of-first-three
  (->> elves
       (map #(apply + (parse-strings %)))
       (sort >)
       (take 3)
       (apply +)))


(prn "Calories of the first:" calories-of-first)
(prn "Calories of the first three:" calories-of-first-three)