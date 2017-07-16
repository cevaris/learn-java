package com.cevaris.hackerrank.array_left_rotation;


import java.util.Scanner;

public class ArrayLeftRotation {

  public static void main(String[] args) {
    ArrayLeftRotation instance = new ArrayLeftRotation();
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int R = in.nextInt();
    int offset = R % N;

    String arr[] = new String[N];
    for (int i = 0; i < N; i++) {
      arr[i] = in.next();
    }

    String[] result = instance.solution(arr, N, offset);
    System.out.println(String.join(" ", result));
  }

  private String[] solution(String[] source, int N, int offset) {
    if (offset == 0) {
      return source;
    }

    String[] dest = new String[source.length];
    System.arraycopy(source, 0, dest, N - offset, offset);
    System.arraycopy(source, offset, dest, 0, N - offset);
    return dest;
  }
}
