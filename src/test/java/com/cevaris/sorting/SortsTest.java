package com.cevaris.sorting;

import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class SortsTest {

  private final InsertionSort<Integer> sorter = new InsertionSort<>();
  private final int TEST_SIZE = 1000;

  @Test
  public void testInsertionSort() {
    List<Integer> data = TestUtils.fullyRandomList(TEST_SIZE);
    List<Integer> expected = TestUtils.sortedList(TEST_SIZE);
    Assert.assertArrayEquals(expected.toArray(), sorter.sort(data).toArray());
  }

}