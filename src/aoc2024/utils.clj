(ns aoc2024.utils)

(defn- seq-from [item]
  (conj '() item))

(defn appender [s]
  (fn [x] (cons x s)))

(defn cartesian [& seqs]
  (case (count seqs)
    0 '()
    1 (apply map seq-from seqs)
    (let [right-as-seqs (apply cartesian (rest seqs))
          appenders (map appender right-as-seqs)
          juxt-appenders (if (empty? appenders)
                           (constantly '())
                           (apply juxt appenders))]
      (apply concat (map juxt-appenders (first seqs))))))

