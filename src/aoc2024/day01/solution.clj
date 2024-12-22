(ns aoc2024.day01.solution
  (:require [aoc2024.input :as in]))

(defn- diff [a b]
  (abs (- b a)))

(defn- linediff [line]
  (let [[a b] line]
    (diff a b)))

(defn zip [rows]
  (apply map vector rows))

(defn sort-rows [rows]
  (mapv sort rows))

(defn sort-columns [rows]
  (zip (sort-rows (zip rows))))

(defn solution-1 [input]
  (apply + (mapv linediff (sort-columns (in/parse-input input)))))

(defn solution-2 [input]
  (let [cols (zip (in/parse-input input))
        freqs (frequencies (second cols))
        score #(* %1 (or (get freqs %1) 0))]
    (apply + (map score (first cols)))))
