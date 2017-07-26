package com.cevaris.problems.min_xor;


import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

class MinXor {
  int findMinXor(ArrayList<Integer> A) {
    if (A == null || A.size() < 2) throw new IllegalArgumentException();

    ConcurrentSkipListSet<Integer> queue = new ConcurrentSkipListSet<>();
    for (int i = 0; i < A.size(); i++) {
      for (int j = i + 1; j < A.size(); j++) {
        if (queue.size() > 100) {
          queue.pollLast();
        }
        int value = A.get(i) ^ A.get(j);
        queue.add(value);
      }
    }

    return queue.pollFirst();
  }
}
