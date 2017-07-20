package com.cevaris.problems.nth_largest_element;

import java.util.Arrays;
import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class NthLargestElementTest {

  @Test
  public void testPriorityQueue() {
    final int N = 5;
    List<Integer> data = TestUtils.until(0, N);
    List<Integer> test = TestUtils.fullyRandomList(N);

    assertKth(test, data, 1);
    assertKth(test, data, 0);
    assertKth(test, data, 1);
  }

  @Test
  public void testSortThenLook() {
    int N = 15;
    List<Integer> data = TestUtils.until(0, N);
    Assert.assertEquals(data.get(N - 1 - 0), NthLargestElement.sortThenLook(0, data));
    Assert.assertEquals(data.get(N - 1 - 1), NthLargestElement.sortThenLook(1, data));
    Assert.assertEquals(data.get(N - 1 - 5), NthLargestElement.sortThenLook(5, data));
  }

  private void assertKth(List<Integer> test, List<Integer> expected, int K) {
    Assert.assertEquals(
        Arrays.deepToString(test.toArray()),
        expected.get(test.size() - 1 - K),
        NthLargestElement.priorityQueue(K, test)
    );
  }
}