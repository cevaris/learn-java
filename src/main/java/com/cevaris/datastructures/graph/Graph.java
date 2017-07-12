package com.cevaris.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


interface Graph<E> {
  Iterator<Vertex<E>> iterator();

  Iterator<Vertex<E>> iterator(VisitOrder order);

  void addEdge(E a, E b);

  List<Vertex<E>> toList();

  Object[] toArray();

  int size();
}

enum VisitOrder {
  DFS, BFS
}

class GraphBuilder<E> {
  private List<Edge<E>> ls = new ArrayList<>();

  @SafeVarargs
  final GraphBuilder<E> withEdges(Edge<E>... ls) {
    this.ls = new ArrayList<>(Arrays.asList(ls));
    return this;
  }

  Graph<E> build() {
    final Graph<E> graph = new GenericGraph<>();
    for (Edge<E> e : ls) {
      graph.addEdge(e.getFrom(), e.getTo());
    }
    return graph;
  }
}

class GenericGraph<E> implements Graph<E> {
  private final Set<Vertex<E>> vertices;
  private final Map<E, Vertex<E>> lookup;

  public GenericGraph() {
    vertices = new HashSet<>();
    lookup = new HashMap<>();
  }

  @Override
  public void addEdge(E from, E to) {
    // for quick node lookup
    Vertex<E> vertexFrom = lookup.computeIfAbsent(from, Vertex::new);
    Vertex<E> vertexTo = lookup.computeIfAbsent(to, Vertex::new);

    // from -> to
    vertexFrom.addNeighbor(vertexTo);

    vertices.add(vertexFrom);
    vertices.add(vertexTo);
  }

  @Override
  public Iterator<Vertex<E>> iterator() {
    return vertices.iterator();
  }

  @Override
  public Iterator<Vertex<E>> iterator(VisitOrder order) {
    return null;
  }

  @Override
  public int size() {
    return vertices.size();
  }

  @Override
  public List<Vertex<E>> toList() {
    List<Vertex<E>> arr = new ArrayList<>();
    Iterator<Vertex<E>> iter = iterator();
    while (iter.hasNext()) {
      arr.add(iter.next());
    }
    return arr;
  }

  @Override
  public Object[] toArray() {
    return toList().toArray();
  }
}
