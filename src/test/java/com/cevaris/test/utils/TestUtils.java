package com.cevaris.test.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

  @SafeVarargs
  public static <E> List<E> newList(E... collection) {
    return new ArrayList<E>(Arrays.asList(collection));
  }

  public static List<Integer> until(int fromInclusive, int toExclusive) {
    return new ArrayList<>(IntStream.range(fromInclusive, toExclusive).boxed().collect(Collectors.toList()));
  }

  public static void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      // nothing
    }
  }

}
