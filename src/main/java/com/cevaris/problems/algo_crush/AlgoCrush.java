package com.cevaris.problems.algo_crush;

import java.util.Arrays;
import java.util.Scanner;

public class AlgoCrush {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int N = in.nextInt();
    long[] arr = new long[N + 1];
    Arrays.fill(arr, 0);

    int Q = in.nextInt();
    for (int qi = 0; qi < Q; qi++) {
      int a = in.nextInt();
      int b = in.nextInt();
      int k = in.nextInt();

      arr[a - 1] += k;
      arr[b] -= k;
    }

    long max = 0;
    long sum = 0;
    for (int i = 0; i < N; i++) {
      sum += arr[i];
      if (max < sum) {
        max = sum;
      }
    }

    System.out.println(max);
  }

}
