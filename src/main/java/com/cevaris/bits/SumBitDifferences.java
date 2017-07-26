package com.cevaris.bits;

import java.util.ArrayList;

class SumBitDifferences {

  private int f(int x, int y) {
    int diff = x ^ y;

    int ones = 0;
    while (diff != 0) {
      ones += (diff & 1);
      diff >>= 1;
    }

    return ones;
  }

  public int cntBits(ArrayList<Integer> A) {
    int sum = 0;
    for (int i = 0; i < A.size(); i++) {
      for (int j = i + 1; j < A.size(); j++) {
        if (i == j) continue;
        sum += 2 * f(A.get(i), A.get(j));
      }
    }
    return sum;

  }
}
