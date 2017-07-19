package com.cevaris.problems.sum_of_triplets;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class SumOfTripletsTest {

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyCase() {
    List<Integer> data = Collections.emptyList();
    SumOfTriplets.findTriplets(data);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLessThanTree() {
    List<Integer> data = TestUtils.newList(0, 1);
    SumOfTriplets.findTriplets(data);
  }

  @Test
  public void testCase0() {
    List<Set<Integer>> expected = TestUtils.newList(
        new HashSet<>(Arrays.asList(0, -1, 1)),
        new HashSet<>(Arrays.asList(2, -3, 1))
    );
    List<Integer> data = TestUtils.newList(0, -1, 2, -3, 1);
    Assert.assertArrayEquals(expected.toArray(), SumOfTriplets.findTriplets(data).toArray());
  }

  @Test
  public void testCase1() {
    List<Set<Integer>> expected = TestUtils.newList(
        new HashSet<>(Arrays.asList(1, -2, 1))
    );
    List<Integer> data = TestUtils.newList(1, -2, 1, 0, 5);
    Assert.assertArrayEquals(expected.toArray(), SumOfTriplets.findTriplets(data).toArray());
  }

  @Test
  public void testCase2() {
    List<Set<Integer>> expected = Collections.emptyList();
    List<Integer> data = TestUtils.newList(1, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), SumOfTriplets.findTriplets(data).toArray());
  }

  @Test
  public void testCase3() {
    List<Set<Integer>> expected = TestUtils.newList(
        new HashSet<>(Arrays.asList(4, 10, 8))
    );
    List<Integer> data = TestUtils.newList(1, 4, 45, 6, 10, 8);
    int targetSum = 22;
    Assert.assertArrayEquals(expected.toArray(), SumOfTriplets.findTriplets(data, targetSum).toArray());
  }

}