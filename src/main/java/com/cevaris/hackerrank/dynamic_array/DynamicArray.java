package com.cevaris.hackerrank.dynamic_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/dynamic-array
 */
public class DynamicArray {
  private final List[] seqs;

  private DynamicArray(int N) {
    this.seqs = new List[N];

    for (int n = 0; n < N; n++) {
      seqs[n] = new ArrayList<>();
    }
  }

  private String solution(int[][] queries) {
    final int QUERY_TYPE = 0;
    final int X = 1;
    final int Y = 2;
    final StringBuilder result = new StringBuilder();

    int lastAnswer = 0;

    for (int[] query : queries) {
      if (query[QUERY_TYPE] == 1) {
        List currSeq = getSequence(query[X], lastAnswer);
        currSeq.add(currSeq.size(), query[Y]);
      }

      if (query[QUERY_TYPE] == 2) {
        List currSeq = getSequence(query[X], lastAnswer);
        int nextAnswerIndex = query[Y] % currSeq.size();
        lastAnswer = (int) currSeq.get(nextAnswerIndex);
        result.append(String.format("%d\n", lastAnswer));
      }
    }

    return result.toString();
  }

  private List getSequence(int x, int lastAnswer) {
    return seqs[((x ^ lastAnswer) % seqs.length)];
  }


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int Q = in.nextInt();

    int[][] queries = new int[Q][3];
    for (int q = 0; q < Q; q++) {
      queries[q][0] = in.nextInt();
      queries[q][1] = in.nextInt();
      queries[q][2] = in.nextInt();
    }

    DynamicArray instance = new DynamicArray(N);
    String result = instance.solution(queries);
    System.out.println(result);
  }

}
