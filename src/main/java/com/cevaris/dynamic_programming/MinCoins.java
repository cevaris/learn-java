package com.cevaris.dynamic_programming;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MinCoins {

  static int sentinel = Integer.MAX_VALUE;

  static int calculate(Set<Integer> coins, int T) {

    if (T < 0) throw new IllegalArgumentException();
    if (coins == null || coins.isEmpty()) throw new IllegalArgumentException();

    int[] t = new int[T + 1];
    Arrays.fill(t, sentinel);
    t[0] = 0;

    for (int i = 1; i <= T; i++) {
      List<Integer> tmp = new ArrayList<>();

      for (Integer c : coins) {
        if (c <= i) {
          tmp.add(t[i - c]);
        }
      }

      if (!tmp.isEmpty()) {
        t[i] = Collections.min(tmp) + 1;
      }
    }

    if (t[T] == sentinel) {
      return -1;
    } else {
      return t[T];
    }
  }
}
