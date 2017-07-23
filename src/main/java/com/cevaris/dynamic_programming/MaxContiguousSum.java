package com.cevaris.dynamic_programming;

public class MaxContiguousSum {
  static int calculate(int[] arr) {
    if (arr == null || arr.length == 0) {
      throw new IllegalArgumentException();
    }
    if (arr.length == 1) return arr[0];

    int t[] = new int[arr.length];
    t[0] = arr[0];

    for (int i = 1; i < arr.length; i++) {
      t[i] = Math.max(arr[i], t[i - 1] + arr[i]);
    }

    int result = Integer.MIN_VALUE;
    for (int i = 0; i < t.length; i++) {
      result = Math.max(result, t[i]);
    }

    return result;
  }
}
