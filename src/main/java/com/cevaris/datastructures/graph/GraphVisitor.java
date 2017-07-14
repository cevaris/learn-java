package com.cevaris.datastructures.graph;


import java.util.ArrayList;
import java.util.List;

enum VisitorState {
  CONTINUE, // continue to next node
  BREAK,    // break to next level
  RETURN    // stop all execution
}

interface GraphVisitor<E extends Comparable<E>> {
  void apply(Vertex<E> v);

  VisitorState state();
}

class ListVisitor<E extends Comparable<E>> implements GraphVisitor<E> {
  private final List<E> ls;

  ListVisitor(int size) {
    this.ls = new ArrayList<>(size);
  }

  @Override
  public void apply(Vertex<E> v) {
    ls.add(v.getValue());
  }

  @Override
  public VisitorState state() {
    return VisitorState.CONTINUE;
  }

  List<E> toList() {
    return ls;
  }
}

class CloneVisitor<E extends Comparable<E>> implements GraphVisitor<E> {
  private final GraphBuilder<E> builder;
  private final Graph<E> g;

  CloneVisitor(Graph<E> g) {
    this.builder = new GraphBuilder<>();
    this.g = g;
  }

  @Override
  public void apply(Vertex<E> v) {
    for (Vertex<E> n : v.getNeighbors()) {
      builder.withEdges(new Edge<>(v.getValue(), n.getValue()));
    }
  }

  @Override
  public VisitorState state() {
    return VisitorState.CONTINUE;
  }

  Graph<E> toGraph() {
    return builder.build(g);
  }
}
