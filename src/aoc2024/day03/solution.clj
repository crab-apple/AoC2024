(ns aoc2024.day03.solution)

(defn extract-valid-muls [str]
  (map first (re-seq #"(mul\(\d+,\d+\))" str)))

(defn parse-mul [str]
  (map parse-long (re-seq #"\d+" str)))

(defn execute-mul [str]
  (apply * (parse-mul str)))

(defn solution-1 [input]
  (apply + (map execute-mul (extract-valid-muls input))))


