package com.cevaris.test.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class TestUtils {

  private static final Random rand = new Random();

  public static List<Integer> sortedList(int size) {
    List<Integer> list = until(0, size);
    Collections.sort(list);
    return list;
  }

  public static List<Integer> reverseList(int size) {
    List<Integer> list = sortedList(size);
    Collections.reverse(list);
    return list;
  }

  public static List<Integer> semiSortedList(int size) {
    return randomList(size, 0.25);
  }

  public static List<Integer> fullyRandomList(int size) {
    return randomList(size, 2.0);
  }

  public static List<Integer> randomList(int size, double factor) {
    List<Integer> list = until(0, size);
    Collections.sort(list);
    for (int i = 0; i < (int) (size * factor); i++) {
      int ii = rand.nextInt(size);
      int jj = rand.nextInt(size);
      Collections.swap(list, ii, jj);
    }
    return list;
  }

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
