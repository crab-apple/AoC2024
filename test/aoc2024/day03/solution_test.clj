(ns aoc2024.day03.solution_test
  (:use [aoc2024.day03.solution]
        [midje.sweet]))

(facts "extract valid muls"
       (fact "returns a sequence containing all the valid \"mul\" strings in the input"
             (let [input "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"]
               (extract-valid-muls input) => ["mul(2,4)" "mul(5,5)" "mul(11,8)" "mul(8,5)"])))

(facts "parse-mul"
       (fact "returns a sequence with the factors contained in the mul string"
             (parse-mul "mul(2,4") => [2, 4]
             (parse-mul "mul(11,12") => [11, 12]))

(facts "solution-1"
       (fact "works with the example input"
             (let [input "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"]
               (solution-1 input) => 161))
       (fact "works with input spread across multiple lines"
             (let [input "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)\n+mul(32,64]then(mul(11,8)mul(8,5))"]
               (solution-1 input) => 161)))
