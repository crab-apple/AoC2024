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
             ;(solution-1 input) => 202
             ))
