(ns aoc2024.day1.solution_test
  (:use [aoc2024.day1.solution]
        [midje.sweet]
        [clojure.test :as test]))

(facts "solution-1"
       (fact "is a function"
             solution-1 => test/function?)
       (fact "works correctly for example input"
             (def input "3 4
                         4 3
                         2 5
                         1 3
                         3 9
                         3 3
                         ")
             (solution-1 input) => 11)

       (fact "finds the difference between a small and a big number"
             (solution-1 "1 3") => 2)
       (fact "finds the difference between a big and a small number"
             (solution-1 "3 1") => 2)
       (fact "sums the differences of multiple pairs"
             (def input "1 3
                        300 100
                        ")
             (solution-1 input) => 202)
       (fact "pairs lowest with lowest and largest with largest"
             (def input "1 100
                        300 3
                        ")
             (solution-1 input) => 202))

(facts "sort-rows"
       (fact "does not modify one already sorted row"
             (sort-rows [[1 2 3]]) => [[1 2 3]])
       (fact "sorts one unsorted row"
             (sort-rows [[1 3 2]]) => [[1 2 3]])
       (fact "sorts multiple unsorted rows"
             (sort-rows [[1 5 3] [2 6 4]]) => [[1 3 5] [2 4 6]]))
(facts "sort-columns"
       (fact "retains input when already sorted"
             (sort-columns [[1 2] [10 20]]) => [[1 2] [10 20]])
       (fact "sorts columns when not already sorted"
             (sort-columns [[1 20] [10 2]]) => [[1 2] [10 20]]
             (sort-columns [[1 100] [300 3]]) => [[1 3] [300 100]]))

(facts "zip"
       (fact "zips vectors"
             (zip [[1 100] [300 3]]) => [[1 300] [100 3]]))

(facts "solution-2"
       (fact "works correctly for example input"
             (def input "3 4
                         4 3
                         2 5
                         1 3
                         3 9
                         3 3
                         ")
             (solution-2 input) => 31))
