package com.cevaris.problems.two_d_array;

import java.util.Scanner;

/**
 *
 * https://www.hackerrank.com/challenges/2d-array
 *
 */
public class TwoDArray {

  private static final int SENTINEL = Integer.MIN_VALUE;
  private static final int R = 6;
  private static final int C = 6;

  private int maxSum = SENTINEL;

  private int solution(int[][] arr) {
    for (int ri = 0; ri < R; ri++) {
      for (int ci = 0; ci < C; ci++) {
        int candidate = checkAndSum(arr, ri, ci);

        synchronized (this) {
          if (candidate > maxSum) {
            maxSum = candidate;
          }
        }

      }
    }
    return maxSum;
  }

  private int checkAndSum(int[][] arr, int ri, int ci) {
    int[][] offsets = {
        {ri, ci}, {ri, ci + 1}, {ri, ci + 2},
        {ri + 1, ci + 1},
        {ri + 2, ci}, {ri + 2, ci + 1}, {ri + 2, ci + 2}
    };

    int sum = 0;
    for (int[] offset : offsets) {
      int ro = offset[0];
      int co = offset[1];
      if (ro > R - 1 || co > C - 1) {
        return SENTINEL;
      } else {
        sum += arr[ro][co];
      }
    }

    return sum;
  }

  public static void main(String[] args) {
    TwoDArray instance = new TwoDArray();
    Scanner in = new Scanner(System.in);
    int arr[][] = new int[R][C];
    for (int ri = 0; ri < R; ri++) {
      for (int ci = 0; ci < C; ci++) {
        arr[ri][ci] = in.nextInt();
      }
    }
    int result = instance.solution(arr);
    System.out.println(result);
  }

}
