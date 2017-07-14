package com.cevaris.datastructures.graph;

abstract class GraphSpec {

  Edge<Integer> edge(Integer from, Integer to) {
    return new Edge<>(from, to);
  }

}
