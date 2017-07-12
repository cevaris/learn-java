package com.cevaris.datastructures.graph;

import java.util.Objects;

class Edge<E> {
  private final E from;
  private final E to;

  Edge(E from, E to) {
    this.from = from;
    this.to = to;
  }

  E getFrom() {
    return from;
  }

  E getTo() {
    return to;
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Edge other = (Edge) obj;
    return Objects.equals(this.from, other.from)
        && Objects.equals(this.to, other.to);
  }
}
