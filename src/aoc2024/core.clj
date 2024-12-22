(ns aoc2024.core

  (:use
   [aoc2024.day1.solution :as d1]))

(defn -main []
  (println "Day 01-1: " (d1/solution-1 (slurp "resources/day1/input"))))
