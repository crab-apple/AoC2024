(ns aoc2024.day04.solution_test
  (:use [aoc2024.day04.solution]
        [midje.sweet]))

(facts "soup-slice"
       (let [input "abcd
                    efgh
                    ijkl
                    mnop
                    "]
         (fact "slices the soup horizontally from left to right"
               (soup-slice input) => (contains "efgh"))
         (fact "slices the soup horizontally from right to left"
               (soup-slice input) => (contains "hgfe"))
         (fact "slices the soup vertically"
               (soup-slice input) => (contains "bfjn")
               (soup-slice input) => (contains "njfb"))
         (fact "slices the soup diagonally NW to SE"
               (soup-slice input) => (contains "afkp")
               (soup-slice input) => (contains "ch")
               (soup-slice input) => (contains "d")
               (soup-slice input) => (contains "m"))
         (fact "slices the soup diagonally NE to SW"
               (soup-slice input) => (contains "dgjm")
               (soup-slice input) => (contains "lo")
               (soup-slice input) => (contains "a"))
         (fact "slices the soup diagonally SE to NW"
               (soup-slice input) => (contains "pkfa")
               (soup-slice input) => (contains "hc")
               (soup-slice input) => (contains "d")
               (soup-slice input) => (contains "m"))
         (fact "slices the soup diagonally SW to NE"
               (soup-slice input) => (contains "mjgd")
               (soup-slice input) => (contains "ol")
               (soup-slice input) => (contains "a"))))

(facts "finding substrings"
       (fact "works for zero occurrences"
             (count-substring-occurrences "FOO" "abcd") => 0)
       (fact "works for one occurrence"
             (count-substring-occurrences "FOO" "abcFOOdefofo") => 1)
       (fact "works for many occurrences"
             (count-substring-occurrences "FOO" "abcFOOdefoFOO") => 2)
       (fact "counts overlapping finds"
             (count-substring-occurrences "BABA" "abcBABABA de") => 2
             (count-substring-occurrences "BB" "abcBBBf de") => 2)
       (fact "finds match at beginning"
             (count-substring-occurrences "FOO" "FOOx") => 1)
       (fact "finds match at end"
             (count-substring-occurrences "FOO" "xFOO") => 1))

(facts "solution 1"
       (fact "works for example input"
             (let [input
                   "
                   MMMSXXMASM
                   MSAMXMSMSA
                   AMXSXMAAMM
                   MSAMASMSMX
                   XMASAMXAMM
                   XXAMMXXAMA
                   SMSMSASXSS
                   SAXAMASAAA
                   MAMMMXMMMM
                   MXMXAXMASX
                   "]
               (solution-1 input) => 18)))


