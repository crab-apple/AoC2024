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

(facts "from-old-ruleset"
       (fact "empty"
             (from-old-ruleset []) => {:from-left {} :from-right {}})
       (fact "one rule"
             (from-old-ruleset [[:a :b]]) => {:from-left {:a #{:b}} :from-right {:b #{:a}}})
       (fact "non overlapping rules"
             (from-old-ruleset [[:a :b] [:c :d]]) => {:from-left {:a #{:b} :c #{:d}} :from-right {:b #{:a} :d #{:c}}})
       (fact "non overlapping rules"
             (from-old-ruleset [[:a :b] [:a :d]]) => {:from-left {:a #{:b :d}} :from-right {:b #{:a} :d #{:a}}}))

(facts "remove pair"
       (fact "set of one"
             (remove-pair {:a #{:x} :b #{:x :y}} [:a :x]) => {:b #{:x :y}})
       (fact "set of many"
             (remove-pair {:a #{:x :y} :b #{:x :y}} [:a :x]) => {:a #{:y} :b #{:x :y}})
       (fact "non-existing key "
             (remove-pair {:a #{:b}} [:foo :b]) => {:a #{:b}})
       (fact "non-existing value "
             (remove-pair {:a #{:b}} [:a :foo]) => {:a #{:b}}))

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
               (correct-update (:rules-old parsed) (first (:updates parsed))) => ["97" "75" "47" "61" "53"])))
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
