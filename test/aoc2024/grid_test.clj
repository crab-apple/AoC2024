(ns aoc2024.grid-test
  (:use [aoc2024.grid]
        [midje.sweet]))

(defn- all-vectors? [grid]
  (and
   (vector? grid)
   (vector? (first grid))))

(facts "width and height"
       (let [grid [[1 2] [3 4] [5 6]]]
         (fact "returns the width"
               (width grid) => 2)
         (fact "returns the height"
               (height grid) => 3)))

(facts "rotating"
       (let [grid [[1 2] [3 4] [5 6]]]
         (fact "rotates the grid clockwise"
               (rotate-clockwise grid) => [[5 3 1] [6 4 2]])
         (fact "rotates the grid counter-clockwise"
               (rotate-counterclockwise grid) => [[2 4 6] [1 3 5]])
         (fact "returns vectors"
               (rotate-clockwise grid) => all-vectors?
               (rotate-counterclockwise grid) => all-vectors?)))
(facts "diagonal-nw-se"
       (let [grid [[1 2] [3 4]]]
         (fact "returns empty seq if point out of grid"
               (diagonal-nw-se grid [2 2]) => []
               (diagonal-nw-se grid [0 2]) => []
               (diagonal-nw-se grid [2 0]) => []
               (diagonal-nw-se grid [-1 0]) => []
               (diagonal-nw-se grid [0 -1]) => [])
         (fact "returns seq with one element if point at end of grid"
               (diagonal-nw-se grid [0 1]) => [2]
               (diagonal-nw-se grid [1 0]) => [3]
               (diagonal-nw-se grid [1 1]) => [4])
         (fact "returns seq with several elements if point not end of grid"
               (diagonal-nw-se grid [0 0]) => [1 4])))

(facts "diagonal-ne-sw"
       (let [grid [[1 2] [3 4]]]
         (fact "returns empty seq if point out of grid"
               (diagonal-ne-sw grid [2 2]) => []
               (diagonal-ne-sw grid [0 2]) => []
               (diagonal-ne-sw grid [2 0]) => []
               (diagonal-ne-sw grid [-1 0]) => []
               (diagonal-ne-sw grid [0 -1]) => [])
         (fact "returns seq with one element if point at end of grid"
               (diagonal-ne-sw grid [0 0]) => [1]
               (diagonal-ne-sw grid [1 0]) => [3]
               (diagonal-ne-sw grid [1 1]) => [4])
         (fact "returns seq with several elements if point not end of grid"
               (diagonal-ne-sw grid [0 1]) => [2 3])))
