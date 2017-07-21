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
  private List<Integer> singleton = Arrays.asList(1);

  private List<Integer> binary = Arrays.asList(1, 0);
  private List<Integer> expectedBinary = TestUtils.sortedList(2);


  private List<Integer> expectedRandomList = TestUtils.sortedList(TEST_SIZE);

  @Test
  public void testInsertionSort() {
    Sorts<Integer> sorts = new InsertionSort<>();
    assertLists(sorts);
  }

  @Test
  public void testMergeSort() {
    Sorts<Integer> sorts = new MergeSort<>();
    assertLists(sorts);
  }

  @Test
  public void testQuickSort() {
    Sorts<Integer> sorts = new QuickSort<>();
    assertLists(sorts);
  }

  private void assertLists(Sorts<Integer> sorts) {
    Assert.assertArrayEquals(empty.toArray(), sorts.sort(empty).toArray());
    Assert.assertArrayEquals(singleton.toArray(), sorts.sort(singleton).toArray());
    Assert.assertArrayEquals(expectedBinary.toArray(), sorts.sort(binary).toArray());
    for (int i = 0; i < 100; i++) {
      String expected = Arrays.toString(expectedRandomList.toArray());
      List<Integer> unsorted = TestUtils.sortedList(TEST_SIZE);
      String actual = Arrays.toString(sorts.sort(unsorted).toArray());
      Assert.assertEquals(String.format("%s -> %s", unsorted, actual), expected, actual);
    }
  }

}