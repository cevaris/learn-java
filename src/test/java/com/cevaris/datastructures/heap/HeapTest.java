package com.cevaris.datastructures.heap;

import java.util.Collections;
import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class HeapTest {

  @Test
  public void testAddMany() {
    Heap<Integer> actual = new MinHeap<>(3);
    List<Integer> expected = TestUtils.newList(2, 1, 0);
    actual.addAll(expected);

    Collections.sort(expected);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());
  }

}