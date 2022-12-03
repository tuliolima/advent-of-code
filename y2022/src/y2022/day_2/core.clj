(ns y2022.day-2.core
  (:require [clojure.string :as s]))

(def input (slurp "src/y2022/day_2/input.txt"))

(def rounds (s/split input #"\n"))

(defn round-outcome [opponent me]
  (cond (contains? #{["A" "X"] ["B" "Y"] ["C" "Z"]} [opponent me]) 3
        (contains? #{["A" "Y"] ["B" "Z"] ["C" "X"]} [opponent me]) 6
        :else 0))

(defn shape-score [shape]
  (get {"X" 1 "Y" 2 "Z" 3} shape))

(defn round-score [opponent me]
  (+ (round-outcome opponent me)
     (shape-score me)))

(def total-score
  (->> rounds
       (map #(s/split % #" "))
       (map #(apply round-score %))
       (apply +)))

(prn (str "My total score is " total-score "."))

(defn new-round-outcome [result]
  (get {"X" 0 "Y" 3 "Z" 6} result))

(defn choose-shape [opponent result]
  (get {["A" "X"] "C" ["A" "Y"] "A" ["A" "Z"] "B"
        ["B" "X"] "A" ["B" "Y"] "B" ["B" "Z"] "C"
        ["C" "X"] "B" ["C" "Y"] "C" ["C" "Z"] "A"} [opponent result]))

(defn new-shape-score [shape]
  (get {"A" 1 "B" 2 "C" 3} shape))

(defn new-round-score [opponent result]
  (+ (new-round-outcome result)
     (new-shape-score (choose-shape opponent result))))

(def new-total-score
  (->> rounds
       (map #(s/split % #" "))
       (map #(apply new-round-score %))
       (apply +)))

(prn (str "Ops, my real total score is " new-total-score "."))