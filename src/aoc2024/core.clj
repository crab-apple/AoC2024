(ns aoc2024.core)

(defrecord Exercise [day num result])

(defn run-exercise [day num]
  (let [ns-name (format "aoc2024.day%02d.solution" day)
        fn-name (format "solution-%d" num)
        input-file (format "resources/day%02d/input" day)
        fn (try (requiring-resolve (symbol (str ns-name "/" fn-name))) (catch Exception e nil))]
    {:day    day
     :num    num
     :result (if (nil? fn) nil
                 (last (doall [(printf "running %d-%d\n" day num)
                               (time (fn (slurp input-file)))])))}))

(defn run-all-exercises []
  (flatten

   (map (fn [day]
          [(run-exercise day 1)
           (run-exercise day 2)]) (range 1 26))))

(defn explain-exercise [exercise]
  (format "Day %02d-%d: %s" (:day exercise) (:num exercise) (or (:result exercise) "--TODO--")))
(defn -main []
  (dorun (map println (map explain-exercise (run-all-exercises)))))
