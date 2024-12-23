(ns aoc2024.grid)
(defn in-grid [grid point]
  (and
   (not (neg? (first point)))
   (not (neg? (second point)))
   (< (first point) (count grid))
   (< (second point) (count (first grid)))))

(defn grid-get [grid point]
  (get (get grid (first point)) (second point)))

(defn width [grid] (count (first grid)))

(defn height [grid] (count grid))

(defn- vectorize [grid-as-seqs]
  (vec (map vec grid-as-seqs)))

(defn- zip [grid]
  (apply map vector grid))

(defn rotate-clockwise [grid]
  (vectorize (map reverse (zip grid))))

(defn rotate-counterclockwise [grid]
  (vectorize (reverse (zip grid))))

(defn diagonal [grid starting-point step]
  (if (in-grid grid starting-point)
    (conj (diagonal grid (step starting-point) step)
          (grid-get grid starting-point))
    '()))

(defn diagonal-nw-se [grid starting-point]
  (diagonal grid starting-point (fn [point] [(inc (first point)) (inc (second point))])))

(defn diagonal-ne-sw [grid starting-point]
  (diagonal grid starting-point (fn [point] [(inc (first point)) (dec (second point))])))
