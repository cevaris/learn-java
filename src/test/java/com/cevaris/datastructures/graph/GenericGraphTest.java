package com.cevaris.datastructures.graph;

import org.junit.Assert;
import org.junit.Test;

public class GenericGraphTest {
  private Graph<Integer> test = new GraphBuilder<Integer>()
      .withEdges(edge(1, 2), edge(1, 3), edge(3, 4))
      .build();

  @Test
  public void testGraphBuilder() {
    Graph<Integer> expected = new GenericGraph<>();
    expected.addEdge(1, 2);
    expected.addEdge(1, 3);
    expected.addEdge(3, 4);

    Assert.assertArrayEquals(expected.toArray(), test.toArray());
  }


  private Edge<Integer> edge(Integer from, Integer to) {
    return new Edge<>(from, to);
  }

}