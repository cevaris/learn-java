package com.cevaris.datastructures.graph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vertex<E> {
  private final E value;
  private final Set<Edge<E>> neighbors;
  private VisitState visit = VisitState.UNVISITED;

  public Vertex(E value) {
    this.value = value;
    neighbors = new HashSet<>();
  }

  public E getValue() {
    return value;
  }

  public Set<Edge<E>> getNeighbors() {
    return neighbors;
  }

  public VisitState getVisit() {
    return visit;
  }

  public void setVisit(VisitState visit) {
    this.visit = visit;
  }

  public void addNeighbor(Vertex<E> to) {
    neighbors.add(new Edge<>(value, to.getValue()));
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
