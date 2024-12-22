(ns aoc2024.input
  (:require [clojure.string :as str]))

(defn lines [in]
  (map str/trim (str/split-lines (str/trim in))))

(defn tokens [in]
  (str/split (str/trim in) #"\s+"))

(defn numstr? [in]
  (not (nil? (re-matches #"\d+" in))))

(defn parse-input [in]
  [])
