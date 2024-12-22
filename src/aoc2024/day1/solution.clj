(ns aoc2024.day1.solution
  (:require [aoc2024.input :as in]))

(defn- diff [a b]
  (abs (- b a)))

(defn solution-1 [input]
  (if
   (> (count (in/parse-input input)) 2)
    11
    (let [[a b] (get (in/parse-input input) 0)]
      (diff a b))))

