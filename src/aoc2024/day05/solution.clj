(ns aoc2024.day05.solution
  (:require [aoc2024.input :as in]
            [clojure.string :as str]))

; --- updates ---

(defn- page-set [update]
  (set update))

(defn- contains-page [update page]
  (contains? (page-set update) page))

; --- rules ---

(defn from-old-ruleset [old-ruleset]
  (let [reducer (fn [rules-as-pairs]
                  (reduce
                   (fn [current rule] (update current (first rule) (fn [old] (conj (or old #{}) (second rule)))))
                   {}
                   rules-as-pairs))
        reversed (map (fn [x] (vector (second x) (first x))) old-ruleset)]
    {:from-left  (reducer old-ruleset)
     :from-right (reducer reversed)}))

(defn- parse-rule-set-old [rule-lines]
  (map #(str/split %1 #"\|") rule-lines))

(defn- rule-applies-old [update rule]
  (and
   (contains-page update (first rule))
   (contains-page update (second rule))))

(defn- appliable-rules-old [update candidate-rules]
  (set (filter (partial rule-applies-old (set update)) candidate-rules)))

(defn- has-right-side [ruleset value]
  (contains? (:from-right ruleset) value))

(defn- right-sides [ruleset]
  (set (keys (:from-right ruleset))))

(defn remove-pair [map pair]
  (cond
    (not (contains? map (first pair))) map
    (= #{(second pair)} (get map (first pair))) (dissoc map (first pair))
    true (update map (first pair) (fn [s]
                                    (set (remove #{(second pair)} s))))))

(defn- remove-by-left-side [ruleset value]
  (let [right-sides (or (get-in ruleset [:from-left value]) #{})
        ruleset (update ruleset :from-left (fn [old-map] (dissoc old-map value)))
        pairs-to-remove (mapv (fn [x] [x value]) right-sides)
        ruleset (update ruleset :from-right (fn [old-map]
                                              (reduce remove-pair old-map pairs-to-remove)))]
    ruleset))

; --- solution ---

(defn parse-input [input]
  (let [lines (in/lines input)
        rule-lines (filter #(str/index-of %1 "|") lines)
        update-lines (filter #(str/index-of %1 ",") lines)]
    {:rules-old (parse-rule-set-old rule-lines)
     :rules     (from-old-ruleset (parse-rule-set-old rule-lines))
     :updates   (map #(str/split %1 #",") update-lines)}))

(defn- valid-update-inner? [rules update]
  true
  (case (count update)
    0 true
    1 true
    (let [head (first update)
          violates-rules (has-right-side rules head)]
      (if violates-rules
        false
        (valid-update-inner? (remove-by-left-side rules head) (rest update))))))

(defn valid-update? [rules-old update]
  true
  (let [rules-old (appliable-rules-old update rules-old)
        rules (from-old-ruleset rules-old)]
    (valid-update-inner? rules update)))

(defn- mid-value [seq]
  (let [mid (/ (dec (count seq)) 2)]
    (nth seq mid)))

(defn solution-1 [input]
  (let [rules (:rules-old (parse-input input))
        updates (:updates (parse-input input))
        valid-updates (filter (partial valid-update? rules) updates)]
    (apply + (map parse-long (map mid-value valid-updates)))))

(defn- correct-update-inner [rules pages]
  (case (count pages)
    0 pages
    1 pages
    (let [head (first (filter (fn [x] (not (has-right-side rules x))) pages))
          rules (remove-by-left-side rules head)]
      (cons head (correct-update-inner rules (disj pages head))))))

(defn correct-update [rules update]
  (let [rules (appliable-rules-old update rules)]
    (correct-update-inner (from-old-ruleset rules) (set update))))

(defn solution-2 [input]
  (let [rules-old (:rules-old (parse-input input))
        rules (:rules (parse-input input))
        updates (:updates (parse-input input))
        invalid-updates (filter (complement (partial valid-update? rules-old)) updates)
        corrected-updates (map (partial correct-update rules-old) invalid-updates)]
    (apply + (map parse-long (map mid-value corrected-updates)))))

