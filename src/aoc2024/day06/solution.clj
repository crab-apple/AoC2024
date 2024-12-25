(ns aoc2024.day06.solution
  (:require [aoc2024.grid :as gr]
            [aoc2024.input :as in]
            [aoc2024.utils :as utils]
            [clojure.string :as str]))

(defn parse [input]
  (let [grid (in/as-grid input)
        cells (gr/as-point-map grid)
        obstacle-cells (filter #(= \# (second %1)) cells)
        guard-cell (first (filter #(str/index-of "^>v<" (second %1)) cells))]
    {:dimensions [(gr/height grid) (gr/width grid)]
     :obstacles  (set (map first obstacle-cells))
     :guard      {:position    (first guard-cell)
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
  (let [cell-nums (range 0 (second (:dimensions lab)))
        cells (map #(display-cell lab [row-num %1]) cell-nums)]
    (str (apply str cells) "\n")))

(defn display [lab]
  (let [row-nums (range 0 (first (:dimensions lab)))
        rows (map (partial display-row lab) row-nums)
        gridstr (str/join rows)]
    gridstr))

(defn step [lab]
  (letfn [(front-of-guard []
            (let [pos (get-in lab [:guard :position])
                  orien (get-in lab [:guard :orientation])]
              (when pos
                (map + pos (case orien
                             :up [-1 0]
                             :right [0 1]
                             :down [1 0]
                             :left [0 -1]
                             nil)))))

          (in-bounds? [pos]
            (utils/in-rectangle? (:dimensions lab) pos))

          (with-no-guard []
            (assoc-in lab [:guard] {:position nil :orientation nil}))

          (move-guard [pos]
            (assoc-in lab [:guard :position] pos))

          (obstacle? [pos]
            (contains? (:obstacles lab) pos))

          (rotate-guard []
            (update-in lab [:guard :orientation] #(case %1
                                                    :up :right
                                                    :right :down
                                                    :down :left
                                                    :left :up)))]

    (cond
      (nil? (front-of-guard)) (with-no-guard)
      (obstacle? (front-of-guard)) (rotate-guard)
      (in-bounds? (front-of-guard)) (move-guard (front-of-guard)))))

(defn- has-guard [lab]
  (not (nil? (get-in lab [:guard :position]))))

(defn- step-till-end [initial-lab]
  (loop [lab initial-lab
         visited-labs '()]
    (if (has-guard lab)
      (recur (step lab) (conj visited-labs lab))
      visited-labs)))

(defn solution-1 [input]
  (let [initial-lab (parse input)
        all-labs (step-till-end initial-lab)
        guard-positions (set (map #(get-in %1 [:guard :position]) all-labs))]
    (count guard-positions)))
