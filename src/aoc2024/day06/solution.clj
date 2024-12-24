(ns aoc2024.day06.solution
  (:require [aoc2024.grid :as gr]
            [aoc2024.input :as in]
            [clojure.string :as str]))

(defn parse [input]
  (let [grid (in/as-grid input)
        cells (gr/as-point-map grid)
        obstacle-cells (filter #(= \# (second %1)) cells)
        guard-cell (first (filter #(str/index-of "^>v<" (second %1)) cells))]
    {:obstacles (map first obstacle-cells)
     :guard     {:position    (first guard-cell)
                 :orientation (case (second guard-cell)
                                \^ :up
                                \> :right
                                \v :down
                                \< :left
                                nil)}}))
