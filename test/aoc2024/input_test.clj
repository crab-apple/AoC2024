(ns aoc2024.input-test
  (:use [aoc2024.input]
        [midje.sweet]))

(facts "lines"
       (fact "returns lines of text"
             (lines "foo\nbar") => ["foo" "bar"])

       (fact "handles trailing newline"
             (lines "foo\nbar\n") => ["foo" "bar"])

       (fact "handles multiple extra newlines at either end"
             (lines "\n\n\nfoo\nbar\n\n\n\n") => ["foo" "bar"])

       (fact "removes leading and trailing space in lines"
             (lines "   \n\n   \n  foo  \n bar  \n\n  \n\n") => ["foo" "bar"]))

(facts "tokens"
       (fact "finds tokens in string"
             (tokens "  foo   bar  ") => ["foo" "bar"]))

(facts "numstr?"
       (fact "returns true when a string represents an integer"
             (numstr? "123") => true)
       (fact "returns false when a string does not represent an integer"
             (numstr? "foo") => false
             (numstr? "1a2") => false
             (numstr? "") => false
             (numstr? " ") => false
             (numstr? " 12 ") => false))

(facts "parse-input"
       (fact "returns a fector"
             (parse-input "") => vector?))
