(ns alphabase.bytes-test
  (:require
    #?(:clj [clojure.test :refer :all]
       :cljs [cljs.test :refer-macros [deftest is testing]])
    [alphabase.bytes :as b]))


(deftest array-manipulation
  (let [bs (b/byte-array 3)]
    (is (= 0 (b/get-byte bs 0)))
    (b/set-byte bs 0 64)
    (b/set-byte bs 1 128)
    (b/set-byte bs 2 255)
    (is (= 64 (b/get-byte bs 0)))
    (is (= 128 (b/get-byte bs 1)))
    (is (= 255 (b/get-byte bs 2)))))


(deftest array-copying
  (testing "full copy"
    (let [a (b/init-bytes [0 1 2 3 4])
          b (b/copy a)]
      (is (not (identical? a b)))
      (is (b/bytes= a b))))
  (testing "full write"
    (let [a (b/init-bytes [0 1 2 3])
          b (b/init-bytes [100 110 120 130 140 150 160])]
      (b/copy a b 2)
      (is (b/bytes= [100 110 0 1 2 3 160] b))))
  (testing "slice write"
    (let [a (b/init-bytes [1 2 3 4 5 6 7 8 9 10])
          b (b/init-bytes [100 110 120 130 140 150])]
      (b/copy a 3 b 2 3)
      (is (b/bytes= [100 110 4 5 6 150] b)))))
