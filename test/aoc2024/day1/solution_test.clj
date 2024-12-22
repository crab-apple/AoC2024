(ns aoc2024.day1.solution_test
  (:require [aoc2024.core :refer :all]
            [aoc2024.day1.solution :refer :all]
            [clojure.test :refer :all]))

(deftest solution-1-test
  (testing "Should be a function"
    (is (function? solution-1)))

  (testing "Should return correct answer for example input"
    (def input "
      3 4
      4 3
      2 5
      1 3
      3 9
      3 3
      ")
    (is (= 11 (solution-1 input))))

  (testing "Should find the difference between a small and a big number"
    (is (= 2 (solution-1 "1 3"))))

  (testing "Should find the difference between a big and a small number"
    (is (= 2 (solution-1 "3 1"))))

  (testing "Should sum the differences of multiple pairs"
    (def input "
      1 3
      300 100
      ")
    ;  (is (= 202 (solution-1 input)))
    ))
