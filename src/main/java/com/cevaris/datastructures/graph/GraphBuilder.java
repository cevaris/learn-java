package com.cevaris.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GraphBuilder<E extends Comparable<E>> {
  private List<Edge<E>> ls = new ArrayList<>();

  @SafeVarargs
  final GraphBuilder<E> withEdges(Edge<E>... edges) {
    ls.addAll(Arrays.asList(edges));
    return this;
  }

  Graph<E> buildDirected() {
    return build(new DirectedGraph<>());
  }

  Graph<E> buildUndirected() {
    return build(new UndirectedGraph<>());
  }

  Graph<E> build(Graph<E> g) {
    for (Edge<E> e : ls) {
      g.addEdge(e.getFrom(), e.getTo());
    }
    return g;
  }
}