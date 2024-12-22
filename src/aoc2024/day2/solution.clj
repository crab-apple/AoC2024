(ns aoc2024.day2.solution
  (:require [aoc2024.input :as in]))

(defn deltas [levels]
  (if (> (count levels) 1)
    (conj (deltas (rest levels)) (- (second levels) (first levels)))
    '()))

(defn- all-increasing [levels]
  (every? pos? (deltas levels)))

(defn max-step [levels]
  (reduce max (map abs (deltas levels))))

(defn report-is-safe [report]
  (and
   (or (all-increasing report) (all-increasing (reverse report)))
   (<= (max-step report) 3)))

(defn solution-1 [input]
  (let [reports (in/parse-input input)]
    (count (filter report-is-safe reports))))
