(ns aoc2024.input-test
  (:require [aoc2024.core :refer :all]
            [aoc2024.input :refer :all]
            [clojure.test :refer :all]))

(deftest lines-test
  (testing "Should return lines of text"
    (is (= ["foo" "bar"] (lines "foo\nbar"))))

  (testing "Should handle trailing newline"
    (is (= ["foo" "bar"] (lines "foo\nbar\n"))))

  (testing "Should handle multiple extra newlines at either end"
    (is (= ["foo" "bar"] (lines "\n\n\nfoo\nbar\n\n\n\n"))))

  (testing "Should remove leading and trailing space in lines"
    (is (= ["foo" "bar"] (lines "   \n\n   \n  foo  \n bar  \n\n  \n\n")))))

(deftest tokens-test
  (testing "Should find tokens in string"
    (is (= ["foo" "bar"] (tokens "  foo   bar  ")))))

(deftest numstr?-test
  (testing "Should return true when a string represents an integer"
    (is (= true (numstr? "123"))))
  (testing "Should return false when a string does not represent an integer"
    (are [x] (= false (numstr? x))
      "foo"
      "1a2"
      ""
      " "
      " 12 ")))

(deftest parse-input-test
  (testing "Should return a vector"
    (is (= true (vector? (parse-input "")))))
  (testing "Should return one element for each line in the input")
  (testing "Should leave non-numeric strings as strings")
  (testing "Should convert numeric strings to longs"))
