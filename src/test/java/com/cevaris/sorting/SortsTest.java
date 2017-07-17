package com.cevaris.sorting;

import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SortsTest {

  private final int TEST_SIZE = 1000;

  private List<Integer> data = TestUtils.fullyRandomList(TEST_SIZE);
  private List<Integer> expected = TestUtils.sortedList(TEST_SIZE);

  @Test
  public void testInsertionSort() {
    Sorter<Integer> sorter = new InsertionSort<>();
    Assert.assertArrayEquals(expected.toArray(), sorter.sort(data).toArray());
  }

  @Ignore
  public void testMergeSort() {
    Sorter<Integer> sorter = new MergeSort<>();
    Assert.assertArrayEquals(expected.toArray(), sorter.sort(data).toArray());
  }

}