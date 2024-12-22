(ns aoc2024.day03.solution)

(defn extract-valid-muls [str]
  (map first (re-seq #"(mul\(\d+,\d+\))" str)))

(defn extract-muls-and-dos [str]
  (map first (re-seq #"(mul\(\d+,\d+\)|do\(\)|don't\(\))" str)))

(defn parse-mul [str]
  (map parse-long (re-seq #"\d+" str)))

(defn execute-mul [str]
  (apply * (parse-mul str)))

(defn solution-1 [input]
  (apply + (map execute-mul (extract-valid-muls input))))

(declare filter-muls-active)
(declare filter-muls-inactive)
(defn filter-muls-active [muls-and-dos]
  (if (empty? muls-and-dos)
    '()
    (case (subs (first muls-and-dos) 0 3)
      "mul" (conj (filter-muls-active (rest muls-and-dos)) (first muls-and-dos))
      "do(" (filter-muls-active (rest muls-and-dos))
      "don" (filter-muls-inactive (rest muls-and-dos)))))

(defn filter-muls-inactive [muls-and-dos]
  (if (empty? muls-and-dos)
    '()
    (case (subs (first muls-and-dos) 0 3)
      "mul" (filter-muls-inactive (rest muls-and-dos))
      "do(" (filter-muls-active (rest muls-and-dos))
      "don" (filter-muls-inactive (rest muls-and-dos)))))

(defn solution-2 [input]
  (apply + (map execute-mul (filter-muls-active (extract-muls-and-dos input)))))


