package com.cevaris.datastructures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> {
  private final E value;
  private final Set<Vertex<E>> neighbors;
  private TraverseState visit = TraverseState.UNVISITED;

  Vertex(E value) {
    this.value = value;
    neighbors = new ConcurrentSkipListSet<>();
  }

  E getValue() {
    return value;
  }

  List<Vertex<E>> getNeighbors() {
    List<Vertex<E>> ts = new ArrayList<>(neighbors.size());
    ts.addAll(neighbors);
    return ts;
  }

  TraverseState getVisit() {
    return visit;
  }

  void setVisitAs(TraverseState visit) {
    this.visit = visit;
  }

  void addNeighbor(Vertex<E> to) {
    neighbors.add(to);
  }

  @Override
  public int compareTo(Vertex<E> o) {
    return value.compareTo(o.getValue());
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

}
