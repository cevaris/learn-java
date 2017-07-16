package com.cevaris.interviewbit.square_root_int.matrix_search;

import java.util.ArrayList;

public class MatrixSearch {

  public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
    if (a == null) return 0;
    if (a.isEmpty()) return 0;
    if (a.get(0).isEmpty()) return 0;

    int R = a.size();
    int C = a.get(0).size();

    int low = 0;
    int high = (R * C) - 1;

    while (low <= high) {
      int m = (low + high) / 2;
      int candidate = get(a, m, R, C);

      if (b == candidate) return 1;
      if (b > candidate)
        low = m + 1;
      else
        high = m - 1;
    }

    return 0;
  }

  private int get(ArrayList<ArrayList<Integer>> a, int i, int R, int C) {
    int ri = i / Math.max(1, C);
    int ci = i % C;
    return a.get(ri).get(ci);
  }
}

