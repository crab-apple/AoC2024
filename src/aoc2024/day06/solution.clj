(ns aoc2024.day06.solution
  (:require [aoc2024.grid :as gr]
            [aoc2024.input :as in]
            [clojure.string :as str]))

(defn parse [input]
  (let [grid (in/as-grid input)
        cells (gr/as-point-map grid)
        obstacle-cells (filter #(= \# (second %1)) cells)
        guard-cell (first (filter #(str/index-of "^>v<" (second %1)) cells))]
    {:size      {:height (gr/height grid) :width (gr/width grid)}
     :obstacles (set (map first obstacle-cells))
     :guard     {:position    (first guard-cell)
                 :orientation (case (second guard-cell)
                                \^ :up
                                \> :right
                                \v :down
                                \< :left
                                nil)}}))

(defn- display-cell [lab point]
  (if (= point (:position (:guard lab)))
    (get {:up \^ :down \v :left \< :right \>} (:orientation (:guard lab)))
    (if (contains? (:obstacles lab) point)
      \#
      \.)))

(defn- display-row [lab row-num]
  (let [cell-nums (range 0 (:width (:size lab)))
        cells (map #(display-cell lab [row-num %1]) cell-nums)]
    (str (apply str cells) "\n")))

(defn display [lab]
  (let [row-nums (range 0 (:height (:size lab)))
        rows (map (partial display-row lab) row-nums)
        gridstr (str/join rows)]
    gridstr))
