(ns y2022.day-5.core
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(def input (s/split (slurp "src/y2022/day_5/input.txt") #"\n\n"))

(def stacks
  (let [lines (-> input
                  first
                  (s/split #"\n")
                  reverse)
        stack-index (fn [index] (+ 1 (* 4 index)))
        read-stack (fn [index] (->> (rest lines)
                                    (mapv #(get % index))
                                    (filterv #(not= \space %))))]
    (mapv #(read-stack (stack-index %)) (range 9))))

(def moves (s/split (second input) #"\n"))

(defn move-crate [from-stack to-stack]
  (when (seq from-stack)
    [(pop from-stack) (conj to-stack (last from-stack))]))

(move-crate [] [4 5 6])

(map #(let [[_ count from to] (re-matches #"move (\d+) from (\d+) to (\d+)$" %)]
        (move-crate count from to)) 
     moves)