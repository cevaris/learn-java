package com.cevaris.datastructures.graph;


import java.util.ArrayList;
import java.util.List;

enum VisitorState {
  CONTINUE, // continue to next node
  BREAK,    // break to next level
  RETURN    // stop all execution
}

interface GraphVisitor<E> {
  void apply(Vertex<E> v);

  VisitorState state();
}

class ListVisitor<E> implements GraphVisitor<E> {
  private final List<E> ls;

  public ListVisitor(int size) {
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

  public List<E> toList() {
    return ls;
  }
}
