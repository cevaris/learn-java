package com.cevaris.test.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class TestUtils {

  @SafeVarargs
  public static <E> List<E> newList(E... collection) {
    return new ArrayList<E>(Arrays.asList(collection));
  }

  public static List<Integer> until(int fromInclusive, int toExclusive) {
    return new ArrayList<>(IntStream.range(fromInclusive, toExclusive).boxed().collect(Collectors.toList()));
  }

  public static List<Long> until(long fromInclusive, long toExclusive) {
    return new ArrayList<>(LongStream.range(fromInclusive, toExclusive).boxed().collect(Collectors.toList()));
  }

  public static void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      // nothing
    }
  }

  public static ArrayList<ArrayList<Integer>> buildMatrix(int c, int r) {
    int counter = 0;
    ArrayList<ArrayList<Integer>> result = new ArrayList<>(r);
    for (int ri = 0; ri < r; ri++) {
      for (int ci = 0; ci < c; ci++) {
        if (result.size() <= ri) {
          result.add(new ArrayList<>(ci));
        }
        result.get(ri).add(counter++);
      }
    }
    return result;
  }

}
