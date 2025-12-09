(ns y2025.day-1.core
  (:require [clojure.string :as s]))

(defn parse-command [cmd-str]
  (let [direction (first cmd-str)
        magnitude (->> cmd-str
                       rest
                       (apply str)
                       Integer/parseInt)]
    (if (= direction \R) magnitude (- magnitude))))

(defn move [state movement]
  (let [position (:position state)
        new-position (mod (+ position movement) 100)
        landed-zeros (:landed-zeros state)
        new-landed-zeros (if (zero? new-position) (+ landed-zeros 1) landed-zeros)
        passed-by-zeros (:passed-by-zeros state)
        new-passed-by-zeros (if (pos? movement)
                              (+ passed-by-zeros (quot (+ position movement) 100))
                              (+ passed-by-zeros (quot (+ (- (+ position movement)) 100) 100)))]
    {:position new-position
     :landed-zeros new-landed-zeros
     :passed-by-zeros new-passed-by-zeros}))

(comment
  (def commands (s/split-lines (slurp "src/y2025/day_1/input.txt")))
  (def movements (map parse-command commands))
  (def initial-state {:position 50 :landed-zeros 0 :passed-by-zeros 0})
  (def final-state (reduce move initial-state movements))
  (do (println "Final Position:" (:position final-state))
      (println "Landed on 0:" (:landed-zeros final-state) "times")
      (println "Passed by 0:" (:passed-by-zeros final-state) "times")))
