package com.cevaris.problems.sparse_arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SparseArrays {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int N = in.nextInt();
    Map<String, Integer> data = new HashMap<>(N);
    for (int n = 0; n < N; n++) {
      String dn = in.next();
      data.put(dn, data.getOrDefault(dn, 0) + 1);
    }

    int Q = in.nextInt();
    List<String> results = new ArrayList<>(Q);
    for (int q = 0; q < Q; q++) {
      String query = in.next();
      results.add(String.valueOf(data.getOrDefault(query, 0)));
    }

    System.out.println(String.join("\n", results));
  }

}
