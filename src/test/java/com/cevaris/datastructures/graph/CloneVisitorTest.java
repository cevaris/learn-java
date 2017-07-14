package com.cevaris.datastructures.graph;

import org.junit.Assert;
import org.junit.Test;

public class CloneVisitorTest extends GraphSpec {

  private GraphBuilder<Integer> graphBuilder = new GraphBuilder<Integer>()
      .withEdges(edge(0, 1), edge(0, 2), edge(1, 2), edge(2, 0), edge(2, 3), edge(3, 3));

  private Graph<Integer> directed = graphBuilder.buildDirected();

  private Graph<Integer> undirected = graphBuilder.buildUndirected();

  @Test
  public void testCanDirectedClone() {
    Graph<Integer> actual = directed.copy();
    Assert.assertArrayEquals(directed.toArray(VisitOrder.BFS, 0), actual.toArray(VisitOrder.BFS, 0));
  }

  @Test
  public void testCanUndirectedClone() {
    Graph<Integer> actual = undirected.copy();
    Assert.assertArrayEquals(undirected.toArray(VisitOrder.BFS, 0), actual.toArray(VisitOrder.BFS, 0));
  }
}