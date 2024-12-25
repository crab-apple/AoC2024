(ns aoc2024.day05.solution
  (:require [aoc2024.input :as in]
            [clojure.string :as str]))

; --- updates ---

(defn- page-set [update]
  (set update))

(defn- contains-page [update page]
  (contains? (page-set update) page))

; --- rules ---

(defn- parse-rule-set [rule-lines]
  (map #(str/split %1 #"\|") rule-lines))

(defn- rule-applies [update rule]
  (and
   (contains-page update (first rule))
   (contains-page update (second rule))))

(defn- appliable-rules [update candidate-rules]
  (set (filter (partial rule-applies update) candidate-rules)))

(defn- filter-by-right-side [ruleset value]
  (set (filter (fn [rule] (= (second rule) value)) ruleset)))

(defn- filter-by-left-side [ruleset value]
  (set (filter (fn [rule] (= (first rule) value)) ruleset)))

(defn- empty-ruleset? [ruleset]
  (empty? ruleset))

(defn- right-sides [ruleset]
  (set (map second ruleset)))

(defn- remove-rules [target-ruleset ruleset-to-remove]
  (apply disj target-ruleset ruleset-to-remove))

; --- solution ---

(defn parse-input [input]
  (let [lines (in/lines input)
        rule-lines (filter #(str/index-of %1 "|") lines)
        update-lines (filter #(str/index-of %1 ",") lines)]
    {:rules   (parse-rule-set rule-lines)
     :updates (map #(str/split %1 #",") update-lines)}))

(defn valid-update? [rules update]
  (let [rules (appliable-rules update rules)]
    (case (count update)
      0 true
      1 true
      (let [head (first update)
            violated-rules (filter-by-right-side rules head)
            satisfied-rules (filter-by-left-side rules head)]
        (if (empty-ruleset? violated-rules)
          (valid-update? (remove-rules rules satisfied-rules) (rest update))
          false)))))

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
          non-heads (right-sides rules)
          possible-heads (apply disj pages non-heads)
          head (first possible-heads)]
      (cons head (correct-update rules (disj pages head))))))

(defn solution-2 [input]
  (let [rules (:rules (parse-input input))
        updates (:updates (parse-input input))
        invalid-updates (filter (complement (partial valid-update? rules)) updates)
        corrected-updates (map (partial correct-update rules) invalid-updates)]
    (apply + (map parse-long (map mid-value corrected-updates)))))

