package com.cevaris.problems.kth_largest_element;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElement {

  static Integer priorityQueue(int k, List<Integer> ls) {
    if (ls == null) return -1;
    if (k < 0) return -1;
    if (ls.size() < k) return -1;

    Queue<Integer> queue = new PriorityQueue<>();

    for (Integer x : ls) {
      if (queue.size() > k) {
        if (queue.peek() > x) {
          continue;
        }
        queue.poll();
      }

      queue.offer(x);
    }

    return queue.peek();
  }

  static Integer sortThenLook(int k, List<Integer> ls) {
    if (ls == null) return -1;
    if (k < 0) return -1;
    if (ls.size() < k) return -1;
    Collections.sort(ls);
    return ls.get(ls.size() - 1 - k);
  }

  static Integer awesomeNTime(int k, List<Integer> ls) {
    return 0;
  }

}
