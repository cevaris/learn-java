package com.cevaris.datastructures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<E> {
  private final E value;
  private final List<Vertex<E>> neighbors;
  private TraverseState visit = TraverseState.UNVISITED;

  public Vertex(E value) {
    this.value = value;
    neighbors = new ArrayList<>();
  }

  public E getValue() {
    return value;
  }

  public List<Vertex<E>> getNeighbors() {
    return neighbors;
  }

  public TraverseState getVisit() {
    return visit;
  }

  public void setVisit(TraverseState visit) {
    this.visit = visit;
  }

  public void addNeighbor(Vertex<E> to) {
    neighbors.add(to);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Vertex other = (Vertex) obj;
    return Objects.equals(this.value, other.value);
  }

  public int numOfNeighbors() {
    return neighbors.size();
  }
}
