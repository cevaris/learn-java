package com.cevaris.dynamic_programming;

class LongestCommonSubSeq {

  static String calculate(String A, String B) {
    if (A == null || B == null) throw new IllegalArgumentException();
    if (A.length() == 0 || B.length() == 0) return String.valueOf("");

    return new String(calculate(A.toCharArray(), B.toCharArray()));
  }

  private static char[] calculate(char[] R, char[] C) {
    int[][] t = new int[R.length][C.length];

    // creates table
    for (int r = 0; r < R.length; r++) {
      for (int c = 0; c < C.length; c++) {
        boolean isMatch = R[r] == C[c];

        if (isMatch) {
          int diagonalValue = diagonal(t, r, c);
          if (diagonalValue != -1) {
            t[r][c] = 1 + diagonalValue;
          } else {
            t[r][c] = 1;
          }
        } else {
          int upValue = up(t, r, c);
          int leftValue = left(t, r, c);

          if (upValue != -1 && leftValue != -1) {
            t[r][c] = Math.max(upValue, leftValue);
          } else if (upValue != -1) {
            t[r][c] = upValue;
          } else if (leftValue != -1) {
            t[r][c] = leftValue;
          }
        }

      }
    }


    // reconstruct string
    StringBuilder builder = new StringBuilder();

    int r = R.length - 1;
    int c = C.length - 1;

    while (r >= 0 && c >= 0) {
      if (R[r] == C[c]) {
        builder.append(R[r]);
        c--;
        r--;
      } else {
        int upValue = up(t, r, c);
        int leftValue = left(t, r, c);
        int upOrLeft = Math.max(leftValue, upValue);
        if (upOrLeft == upValue) {
          r--;
        } else {
          c--;
        }
      }

    }

    return builder.reverse().toString().toCharArray();
  }

  private static int up(int[][] t, int r, int c) {
    if (r <= 0)
      return -1;
    else
      return t[r - 1][c];
  }

  private static int left(int[][] t, int r, int c) {
    if (c <= 0)
      return -1;
    else
      return t[r][c - 1];
  }

  private static int diagonal(int[][] t, int r, int c) {
    if (r <= 0 || c <= 0)
      return -1;
    else
      return t[r - 1][c - 1];
  }
}
