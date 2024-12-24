(ns aoc2024.day06.solution_test
  (:use [aoc2024.day06.solution]
        [midje.sweet]))

(facts "parse"
       (fact "parses obstacle positions"
             (let [input ".#.
                          ..#"]
               (:obstacles (parse input)) => [[0 1] [1 2]]))
       (fact "parses guard position"
             (let [input "...
                          .^."]
               (:position (:guard (parse input))) => [1 1]))
       (fact "parses guard position in all orientations"
             (:position (:guard (parse ".^"))) => [0 1]
             (:position (:guard (parse ".>"))) => [0 1]
             (:position (:guard (parse ".v"))) => [0 1]
             (:position (:guard (parse ".<"))) => [0 1])
       (fact "guard position is nil when no guard"
             (:position (:guard (parse ".."))) => nil)
       (fact "guard orientation is nil when no guard"
             (:orientation (:guard (parse ".."))) => nil)
       (fact "parses orientation is nil when no guard"
             (:orientation (:guard (parse ".^"))) => :up
             (:orientation (:guard (parse ".>"))) => :right
             (:orientation (:guard (parse ".v"))) => :down
             (:orientation (:guard (parse ".<"))) => :left))

