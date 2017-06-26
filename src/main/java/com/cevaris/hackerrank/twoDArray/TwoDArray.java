package com.cevaris.hackerrank.twoDArray;

import java.util.Scanner;

/**
 * @formatter:off
 *
 * https://www.hackerrank.com/challenges/2d-array
 *
 * Context
 * Given a  2D Array, :
 * <p>
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * <p>
 * We define an hourglass in  to be a subset of values with indices falling in this pattern in 's graphical representation:
 * <p>
 * a b c
 *   d
 * e f g
 * There are  hourglasses in , and an hourglass sum is the sum of an hourglass' values.
 * <p>
 * Task
 * Calculate the hourglass sum for every hourglass in , then print the maximum hourglass sum.
 * <p>
 * Note: If you have already solved the Java domain's Java 2D Array challenge, you may wish to skip this challenge.
 * <p>
 * Input Format
 * <p>
 * There are  lines of input, where each line contains  space-separated integers describing 2D Array ; every value in  will be in the inclusive range of  to .
 * <p>
 * Constraints
 * <p>
 * Output Format
 * <p>
 * Print the largest (maximum) hourglass sum found in .
 * <p>
 * Sample Input
 * <p>
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 2 4 4 0
 * 0 0 0 2 0 0
 * 0 0 1 2 4 0
 * Sample Output
 * <p>
 * 19
 * Explanation
 * <p>
 * contains the following hourglasses:
 * <p>
 * 1 1 1   1 1 0   1 0 0   0 0 0
 *   1       0       0       0
 * 1 1 1   1 1 0   1 0 0   0 0 0
 * <p>
 * 0 1 0   1 0 0   0 0 0   0 0 0
 *   1       1       0       0
 * 0 0 2   0 2 4   2 4 4   4 4 0
 * <p>
 * 1 1 1   1 1 0   1 0 0   0 0 0
 *   0       2       4       4
 * 0 0 0   0 0 2   0 2 0   2 0 0
 * <p>
 * 0 0 2   0 2 4   2 4 4   4 4 0
 *   0       0       2       0
 * 0 0 1   0 1 2   1 2 4   2 4 0
 * <p>
 * The hourglass with the maximum sum () is:
 * <p>
 * 2 4 4
 *   2
 * 1 2 4
 * @formatter:on
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
