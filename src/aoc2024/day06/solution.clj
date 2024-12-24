(ns aoc2024.day05.solution
  (:require [aoc2024.input :as in]
            [clojure.string :as str]))

(defn parse-input [input]
  (let [lines (in/lines input)
        rule-lines (filter #(str/index-of %1 "|") lines)
        update-lines (filter #(str/index-of %1 ",") lines)]
    {:rules   (map #(str/split %1 #"\|") rule-lines)
     :updates (map #(str/split %1 #",") update-lines)}))

(defn- page-set [update]
  (set update))

(defn contains-page [update page]
  (contains? (page-set update) page))

(defn rule-applies [update rule]
  (and
   (contains-page update (first rule))
   (contains-page update (second rule))))

(defn- appliable-rules [update candidate-rules]
  (set (filter (partial rule-applies update) candidate-rules)))

(defn valid-update? [rules update]
  (let [rules (appliable-rules update rules)]
    (case (count update)
      0 true
      1 true
      (let [head (first update)
            violated-rules (set (filter (fn [rule] (= (second rule) head)) rules))
            satisfied-rules (set (filter (fn [rule] (= (first rule) head)) rules))]
        (if (not-empty violated-rules)
          false
          (valid-update? (apply disj rules satisfied-rules) (rest update)))))))

(defn- mid-value [seq]
  (let [mid (/ (dec (count seq)) 2)]
    (nth seq mid)))

(defn solution-1 [input]
  (let [rules (:rules (parse-input input))
        updates (:updates (parse-input input))
        valid-updates (filter (partial valid-update? rules) updates)]
    (apply + (map parse-long (map mid-value valid-updates)))))

(defn correct-update [rules update]
  (case (count update)
    0 update
    1 update
    (let [rules (appliable-rules update rules)
          pages (page-set update)
          non-heads (set (map second rules))
          possible-heads (apply disj pages non-heads)
          head (first possible-heads)]
      (cons head (correct-update rules (disj pages head))))))

(defn solution-2 [input]
  (let [rules (:rules (parse-input input))
        updates (:updates (parse-input input))
        invalid-updates (filter (complement (partial valid-update? rules)) updates)
        corrected-updates (map (partial correct-update rules) invalid-updates)]
    (apply + (map parse-long (map mid-value corrected-updates)))))

