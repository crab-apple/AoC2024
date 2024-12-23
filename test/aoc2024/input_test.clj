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
       (fact "returns a vector"
             (parse-input "") => vector?)
       (fact "returns one element for each line in the input"
             (count (parse-input "foo\nbar")) => 2)
       (fact "leaves non-numeric strings as strings"
             (parse-input "foo bar \n bar barbaz") => [["foo", "bar"], ["bar", "barbaz"]])
       (fact "converts numeric strings to longs"
             (parse-input "foo 12 \n 23 barbaz") => [["foo", 12], [23, "barbaz"]]))

(facts "as-grid"
       (fact "returns a grid given a single line"
             (as-grid "abc") => [[\a \b \c]])
       (fact "returns a grid given multiple lines"
             (as-grid "abc\ndef") => [[\a \b \c] [\d \e \f]])
       (fact "the grid is made of vectors (not sequences)"
             (as-grid "abc\ndef") => vector?
             (get (as-grid "abc\ndef") 0) => vector?))
