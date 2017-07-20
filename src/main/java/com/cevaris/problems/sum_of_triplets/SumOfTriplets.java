package com.cevaris.problems.sum_of_triplets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SumOfTriplets {

  static List<Set<Integer>> findTriplets(List<Integer> ls) {
    return findTriplets(ls, 0);
  }

  /**
   * a + b + c = t
   * a = t - b - c
   * a = t - (b  + c)
   *
   * @param ls list of possible sums
   * @param targetSum target sum
   */
  static List<Set<Integer>> findTriplets(List<Integer> ls, int targetSum) {
    if (ls.size() < 3) throw new IllegalArgumentException("list must be >= 3 in size");

    ArrayList<Set<Integer>> results = new ArrayList<>();
    for (int i = 0; i < ls.size() - 1; i++) {

      Set<Integer> toLookFor = new HashSet<>();
      for (int j = i + 1; j < ls.size(); j++) {

        int x = targetSum - (ls.get(i) + ls.get(j));
        if (toLookFor.contains(x)) {
          results.add(new HashSet<>(Arrays.asList(x, ls.get(i), ls.get(j))));
        } else {
          toLookFor.add(ls.get(j));
        }

      }
    }

    return results;
  }

  // -1,-1,0,1,2
  //
  static List<Set<Integer>> findTripletsNoSpace(List<Integer> ls, int targetSum) {
    Collections.sort(ls);

    for (int i = 0; i < ls.size() - 1; i++) {
      int j = i = 1;
      while (j < ls.size()) {

      }
    }


    return new ArrayList<>();
  }


}
