package com.cevaris.datastructures.graph;

import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class GenericGraphTest {
  private Graph<Integer> test = new GraphBuilder<Integer>()
      .withEdges(edge(0, 1), edge(0, 2), edge(1, 2), edge(2, 0), edge(2, 3), edge(3, 3))
      .build();

  @Test
  public void testDfsTraversal() {
    List<Integer> expected = TestUtils.newList(0, 1, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.DFS, 0));

    expected = TestUtils.newList(1, 2, 0, 3);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.DFS, 1));

    expected = TestUtils.newList(2, 0, 1, 3);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.DFS, 2));

    expected = TestUtils.newList(3);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.DFS, 3));
  }

  @Test
  public void testBfsTraversal() {
    List<Integer> expected = TestUtils.newList(0, 1, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.BFS, 0));

    expected = TestUtils.newList(1, 2, 0, 3);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.BFS, 1));

    expected = TestUtils.newList(2, 0, 3, 1);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.BFS, 2));

    expected = TestUtils.newList(3);
    Assert.assertArrayEquals(expected.toArray(), test.toArray(VisitOrder.BFS, 3));
  }

  private Edge<Integer> edge(Integer from, Integer to) {
    return new Edge<>(from, to);
  }

}