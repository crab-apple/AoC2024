(ns aoc2024.day02.solution_test
  (:use [aoc2024.day02.solution]
        [midje.sweet]))

(facts "report-safety"
       (fact "is safe if always increasing"
             (report-is-safe [1 2 3 4]) => true)
       (fact "is safe if always decreasing"
             (report-is-safe [4 3 2 1]) => true)
       (fact "is unsafe if both increasing and decreasing"
             (report-is-safe [1 2 3 2 1]) => false)
       (fact "is unsafe if there are equal adjacent levels"
             (report-is-safe [1 2 2 2 3]) => false)
       (fact "is unsafe if there are only equal adjacent levels"
             (report-is-safe [2 2 2]) => false)
       (fact "is unsafe if adjacent levels differ by more than 3"
             (report-is-safe [1 5]) => false
             (report-is-safe [5 1]) => false))

(facts "deltas"
       (fact "is the list of deltas between adjacent levels"
             (deltas [1 2 5 3]) => [1 3 -2]))

(facts "max-step"
       (fact "returns the step between two levels"
             (max-step [1 3]) => 2)
       (fact "returns the maximum step between multiple positive steps"
             (max-step [1 3 13 15]) => 10)
       (fact "returns the maximum step between a mix of positive and negative steps"
             (max-step [1 3 -7 -5]) => 10))

(facts "solution-1"
       (fact "works correctly for example input"
             (def input "7 6 4 2 1
                         1 2 7 8 9
                         9 7 6 2 1
                         1 3 2 4 5
                         8 6 4 4 1
                         1 3 6 7 9
                         ")
             (solution-1 input) => 2))

(facts "solution-2"
       (fact "works correctly for example input"
             (def input "7 6 4 2 1
                         1 2 7 8 9
                         9 7 6 2 1
                         1 3 2 4 5
                         8 6 4 4 1
                         1 3 6 7 9
                         ")
             (solution-2 input) => 4))

(facts "dampened-variations"
       (fact "returns all variations"
             (dampened-variations [1 2 3 4]) => [[2 3 4] [1 3 4] [1 2 4] [1 2 3]]
             (dampened-variations [1 1 2 2]) => [[1 2 2] [1 2 2] [1 1 2] [1 1 2]]))
