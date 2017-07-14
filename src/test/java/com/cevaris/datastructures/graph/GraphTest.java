package com.cevaris.datastructures.graph;

import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest extends GraphSpec {

  private GraphBuilder<Integer> graphBuilder = new GraphBuilder<Integer>()
      .withEdges(edge(0, 1), edge(0, 2), edge(1, 2), edge(2, 0), edge(2, 3), edge(3, 3));

  private Graph<Integer> directed = graphBuilder.buildDirected();

  private Graph<Integer> undirected = graphBuilder.buildUndirected();

  @Test
  public void testDirectedDfsTraversal() {
    List<Integer> expected = TestUtils.newList(0, 1, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.DFS, 0));

    expected = TestUtils.newList(1, 2, 0, 3);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.DFS, 1));

    expected = TestUtils.newList(2, 0, 1, 3);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.DFS, 2));

    expected = TestUtils.newList(3);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.DFS, 3));
  }

  @Test
  public void testDirectedBfsTraversal() {
    List<Integer> expected = TestUtils.newList(0, 1, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.BFS, 0));

    expected = TestUtils.newList(1, 2, 0, 3);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.BFS, 1));

    expected = TestUtils.newList(2, 0, 3, 1);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.BFS, 2));

    expected = TestUtils.newList(3);
    Assert.assertArrayEquals(expected.toArray(), directed.toArray(VisitOrder.BFS, 3));
  }


  @Test
  public void testUnDirectedDfsTraversal() {
    List<Integer> expected = TestUtils.newList(0, 1, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.DFS, 0));

    expected = TestUtils.newList(1, 0, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.DFS, 1));

    expected = TestUtils.newList(2, 0, 1, 3);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.DFS, 2));

    expected = TestUtils.newList(3, 2, 0, 1);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.DFS, 3));
  }

  @Test
  public void testUndirectedBfsTraversal() {
    List<Integer> expected = TestUtils.newList(0, 1, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.BFS, 0));

    expected = TestUtils.newList(1, 0, 2, 3);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.BFS, 1));

    expected = TestUtils.newList(2, 0, 1, 3);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.BFS, 2));

    expected = TestUtils.newList(3, 2, 0, 1);
    Assert.assertArrayEquals(expected.toArray(), undirected.toArray(VisitOrder.BFS, 3));
  }

}