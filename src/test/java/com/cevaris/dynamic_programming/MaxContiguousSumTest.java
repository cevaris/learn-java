package com.cevaris.dynamic_programming;

import org.junit.Assert;
import org.junit.Test;

public class MaxContiguousSumTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    MaxContiguousSum.calculate(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmpty() {
    int[] arr = {};
    MaxContiguousSum.calculate(arr);
  }

  @Test
  public void testSingle() {
    int[] arr = {1};
    Assert.assertEquals(1, MaxContiguousSum.calculate(arr));
  }

  @Test
  public void testCase0() {
    int[] arr = {-2, -3, 4, -1, -2, 1, 5, -3};
    Assert.assertEquals(7, MaxContiguousSum.calculate(arr));
  }

  @Test
  public void testCase1() {
    int[] arr = {3, -2, 1, 4, 5, -3, -3};
    Assert.assertEquals(11, MaxContiguousSum.calculate(arr));
  }

  @Test
  public void testCase2() {
    int[] arr = {3, -3, 2, 4, -5, 3};
    Assert.assertEquals(6, MaxContiguousSum.calculate(arr));
  }

}