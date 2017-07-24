package com.cevaris.dynamic_programming;

public class LongestCommonSubSeq {

  static String calculate(String A, String B) {
    if (A == null || B == null) throw new IllegalArgumentException();
    if (A.length() == 0 || B.length() == 0) return String.valueOf("");

    return new String(calculate(A.toCharArray(), B.toCharArray()));
  }

  private static char[] calculate(char[] R, char[] C) {
    int[][] t = new int[R.length][C.length];

    // creates table
    for (int c = 0; c < C.length; c++) {
      for (int r = 0; r < R.length; r++) {
        boolean isMatch = R[r] == C[c];

        if (isMatch) {
          Integer diagonalValue = diagonal(t, r, c);
          if (diagonalValue != null) {
            t[r][c] = 1 + diagonalValue;
          } else {
            t[r][c] = 1;
          }
        } else {
          Integer upValue = up(t, r, c);
          Integer leftValue = left(t, r, c);

          if (upValue != null && leftValue != null) {
            t[r][c] = Math.max(upValue, leftValue);
          } else if (upValue != null) {
            t[r][c] = upValue;
          } else if (leftValue != null) {
            t[r][c] = leftValue;
          }
        }

      }
    }


    // reconstruct string
    StringBuilder builder = new StringBuilder();

    int r = R.length - 1;
    int c = C.length - 1;

    while (up(t, r, c) != null && left(t, r, c) != null && diagonal(t, r, c) != null) {
      Integer currValue = t[r][c];
      Integer upValue = up(t, r, c);
      Integer leftValue = left(t, r, c);

      boolean matchLeftOrUp = !currValue.equals(upValue) || !currValue.equals(leftValue);
      if (!matchLeftOrUp) {
        builder.insert(0, R[r]);
        r--;
        c--;
      } else {
        if (currValue.equals(leftValue)) {
          c--;
        } else if (currValue.equals(upValue)) {
          r--;
        } else {
          break;
        }
      }
    }

    return builder.toString().toCharArray();
  }

  private static Integer up(int[][] t, int r, int c) {
    if (r == 0)
      return null;
    else
      return t[r - 1][c];
  }

  private static Integer left(int[][] t, int r, int c) {
    if (c == 0)
      return null;
    else
      return t[r][c - 1];
  }

  private static Integer diagonal(int[][] t, int r, int c) {
    if (r == 0 || c == 0)
      return null;
    else
      return t[r - 1][c - 1];
  }
}
