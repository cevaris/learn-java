package com.cevaris.interviewbit.square_root_int;

public class SquareRootInt {
  public int sqrt(int a) {
    if (a < 0) throw new IllegalArgumentException("argument must be larger than 0");
    if (a == 0) return 0;
    if (a == 1) return 1;

    long low = 0;
    long high = a;
    long m = 0;

    while (low <= high) {
      m = (low + high) / 2;
      if (m * m == a) {
        return (int) m;
      }
      if (m * m > a) {
        high = m - 1;
      } else {
        low = m + 1;
      }
    }

    long result = m;
    while (result * result < a) {
      result++;
    }
    return (int) result - 1;
  }
}
