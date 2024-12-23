(ns aoc2024.utils-test
  (:require [clojure.test :as test])
  (:use [aoc2024.utils]
        [midje.sweet]))

(facts "appender"
       (fact "returns a function"
             (appender :a) => test/function?)
       (fact "the returned function appends to an empty sequence"
             ((appender '()) :x) => '(:x))
       (fact "the returned function appends to sequence with one elem"
             ((appender '(:a)) :x) => '(:x :a))
       (fact "the returned function appends to sequence with n elems"
             ((appender '(:a :b)) :x) => '(:x :a :b)))

(facts "cartesian"
       (fact "works for zero args"
             (cartesian) => '())
       (fact "works for one arg"
             (cartesian '(:a :b :c)) => '((:a) (:b) (:c)))
       (fact "works for two seqs of size 1"
             (cartesian '(:a) '(:1)) => '((:a :1)))
       (fact "works for one seq of size n and one of size 1"
             (cartesian '(:a :b) '(:1)) => '((:a :1) (:b :1))
             (cartesian '(:a :b :c) '(:1)) => '((:a :1) (:b :1) (:c :1)))
       (fact "works for one seq of size 1 and one of size n"
             (cartesian '(:a) '(:1 :2)) => '((:a :1) (:a :2))
             (cartesian '(:a) '(:1 :2 :3)) => '((:a :1) (:a :2) (:a :3)))
       (fact "works for two seqs of size n"
             (cartesian '(:a :b :c) '(:1 :2 :3)) => '((:a :1) (:a :2) (:a :3) (:b :1) (:b :2) (:b :3) (:c :1) (:c :2) (:c :3)))
       (fact "works for n seqs"
             (cartesian '(:a :b) '(:x :y) '(:1 :2)) => '((:a :x :1) (:a :x :2) (:a :y :1) (:a :y :2) (:b :x :1) (:b :x :2) (:b :y :1) (:b :y :2)))
       (fact "works when one of the seqs is empty"
             (cartesian '(:a :b :c) '() '(:1 :2 :3)) => '()))
