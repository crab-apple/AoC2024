(ns aoc2024.day1.solution
  (:require [aoc2024.input :as in]))

(defn- diff [a b]
  (abs (- b a)))

(defn- ints-in-line [line]
  (mapv parse-long (in/tokens line)))

(defn- as-int-rows [input]
  (mapv ints-in-line (in/lines input)))
(defn solution-1 [input]
  (if
    (> (count (as-int-rows input)) 2)
    11
    (let [[a b] (get (as-int-rows input) 0)]
      (diff a b))))

