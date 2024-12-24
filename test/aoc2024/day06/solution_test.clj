(ns aoc2024.day05.solution_test
  (:use [aoc2024.day05.solution]
        [midje.sweet]))

(facts "valid-update?"
       (fact "an empty update is always valid"
             (valid-update? [[1 2] [3 4]] []) => true)
       (fact "an update with only one page is always valid"
             (valid-update? [[1 2] [3 4]] [2]) => true)
       (fact "an update with no rules is always valid"
             (valid-update? [] [1 2 3 4]) => true)
       (fact "an update with rules that don't match in any number is always valid"
             (valid-update? [[1 2] [3 4]] [5 6 7 8]) => true)
       (fact "an update with rules that match in only one number is always valid"
             (valid-update? [[1 2] [3 4]] [1 4 7 8]) => true)
       (fact "an update with numbers in opposite order as a rule is invalid"
             (valid-update? [[1 2]] [2 1]) => false
             (valid-update? [[1 2]] [3 2 1]) => false
             (valid-update? [[1 2]] [2 3 1]) => false))

(facts "update"
       (let [update [1 2 3 8 7 6]]
         (facts "contains-page"
                (fact "is true for contained pages"
                      (contains-page update 1) => true
                      (contains-page update 3) => true
                      (contains-page update 8) => true)
                (fact "is false for not contained pages"
                      (contains-page update 4) => false
                      (contains-page update 0) => false))
         (facts "rule-applies"
                (fact "rule applies if the update contains both sides of the rule"
                      (rule-applies update [2 3]) => true
                      (rule-applies update [2 7]) => true
                      (rule-applies update [7 2]) => true)
                (fact "rule does not apply if the update contains only one side of the rule"
                      (rule-applies update [2 9]) => false
                      (rule-applies update [9 2]) => false)
                (fact "rule does not apply if the update contains none of the sides of the rule"
                      (rule-applies update [0 9]) => false
                      (rule-applies update [9 0]) => false))))

(facts "parse-input"
       (let [input
             "
             75|13
             53|13

             75,47,61
             97,61,53
             "]
         (fact "parses the correct number of rules"
               (count (:rules (parse-input input))) => 2)
         (fact "parses the correct rules"
               (:rules (parse-input input)) => [["75" "13"] ["53" "13"]])
         (fact "parses the correct number of updates"
               (count (:updates (parse-input input))) => 2)
         (fact "parses the correct updates"
               (:updates (parse-input input)) => [["75" "47" "61"] ["97" "61" "53"]])))

(facts "solution-1"
       (fact "works for example input"
             (let [input "
                   47|53
                   97|13
                   97|61
                   97|47
                   75|29
                   61|13
                   75|53
                   29|13
                   97|29
                   53|29
                   61|53
                   97|53
                   61|29
                   47|13
                   75|47
                   97|75
                   47|61
                   75|61
                   47|29
                   75|13
                   53|13

                   75,47,61,53,29
                   97,61,53,29,13
                   75,29,13
                   75,97,47,61,53
                   61,13,29
                   97,13,75,29,47
                   "]
               (solution-1 input) => 143)))

(facts "correcting updates"
       (fact "corrects example"
             (let [input "
                   47|53
                   97|61
                   97|47
                   75|53
                   61|53
                   97|53
                   75|47
                   97|75
                   47|61
                   75|61
                   01|02

                   75,97,47,61,53
                   "
                   parsed (parse-input input)]
               (correct-update (:rules parsed) (first (:updates parsed))) => ["97" "75" "47" "61" "53"])))
(facts "solution-2"
       (fact "works for example input"
             (let [input "
                   47|53
                   97|13
                   97|61
                   97|47
                   75|29
                   61|13
                   75|53
                   29|13
                   97|29
                   53|29
                   61|53
                   97|53
                   61|29
                   47|13
                   75|47
                   97|75
                   47|61
                   75|61
                   47|29
                   75|13
                   53|13

                   75,47,61,53,29
                   97,61,53,29,13
                   75,29,13
                   75,97,47,61,53
                   61,13,29
                   97,13,75,29,47
                   "]
               (solution-2 input) => 123)))
