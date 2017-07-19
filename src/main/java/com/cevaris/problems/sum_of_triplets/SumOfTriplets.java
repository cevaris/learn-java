package com.cevaris.problems.sum_of_triplets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SumOfTriplets {

  static List<Set<Integer>> findTriplets(List<Integer> ls) {
    if (ls.size() < 3) throw new IllegalArgumentException("list must be >= 3 in size");

    ArrayList<Set<Integer>> results = new ArrayList<>();
    for (int i = 0; i < ls.size() - 1; i++) {

      Set<Integer> toLookFor = new HashSet<>();
      for (int j = i + 1; j < ls.size(); j++) {

        int x = -(ls.get(i) + ls.get(j));
        if (toLookFor.contains(x)) {
          results.add(new HashSet<>(Arrays.asList(x, ls.get(i), ls.get(j))));
        } else {
          toLookFor.add(ls.get(j));
        }

      }
    }

    return results;
  }

}
