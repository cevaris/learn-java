package com.cevaris.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class SortsTest {

  private final int TEST_SIZE = 1000;

  private List<Integer> empty = Collections.emptyList();
  private List<Integer> singleton = Collections.singletonList(1);

  private List<Integer> binary = Arrays.asList(1, 0);
  private List<Integer> expectedBinary = TestUtils.sortedList(2);

  private List<Integer> randomList = TestUtils.fullyRandomList(TEST_SIZE);
  private List<Integer> expectedRandomList = TestUtils.sortedList(TEST_SIZE);

  @Test
  public void testInsertionSort() {
    Sorter<Integer> sorter = new InsertionSort<>();
    assertLists(sorter);
  }

  @Test
  public void testMergeSort() {
    Sorter<Integer> sorter = new MergeSort<>();
    assertLists(sorter);
  }

  private void assertLists(Sorter<Integer> sorter) {
    Assert.assertArrayEquals(empty.toArray(), sorter.sort(empty).toArray());
    Assert.assertArrayEquals(singleton.toArray(), sorter.sort(singleton).toArray());
    Assert.assertArrayEquals(expectedBinary.toArray(), sorter.sort(binary).toArray());
    Assert.assertArrayEquals(expectedRandomList.toArray(), sorter.sort(randomList).toArray());
  }

}