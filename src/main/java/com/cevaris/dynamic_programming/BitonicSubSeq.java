package com.cevaris.dynamic_programming;


import java.util.Arrays;

class BitonicSubSeq {

  static int calculate(int[] arr) {
    if (arr == null) throw new IllegalArgumentException();
    if (arr.length < 2) return arr.length;

    int[] lis = new int[arr.length];
    int[] lds = new int[arr.length];

    Arrays.fill(lis, 1);
    Arrays.fill(lds, 1);

    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && lis[j] + 1 > lis[i]) {
          lis[i] = lis[j] + 1;
        }
      }
    }

    for (int i = arr.length - 2; i >= 0; i--) {
      for (int j = arr.length - 1; j > i; j--) {
        if (arr[i] > arr[j] && lds[i] < lds[j] + 1) {
          lds[i] = lds[j] + 1;
        }
      }
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      max = Math.max(max, lds[i] + lis[i]);
    }
    return max - 1;
  }
}