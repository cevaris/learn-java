package com.cevaris.datastructures.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentSkipListMap;


interface Graph<E> {
  void addEdge(E a, E b);

  Object[] toArray(VisitOrder order, E start);

  int size();

  boolean isEmpty();

  Graph<E> copy();
}

enum VisitOrder {
  DFS, BFS
}

abstract class AbstractGraph<E extends Comparable<E>> implements Graph<E> {
  private final ConcurrentSkipListMap<E, Vertex<E>> lookup;

  AbstractGraph() {
    lookup = new ConcurrentSkipListMap<>();
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public int size() {
    return lookup.size();
  }

  @Override
  public Object[] toArray(VisitOrder order, E start) {
    List<E> arr = new ArrayList<>();
    Iterator<E> iter = iterator(order, start);
    while (iter.hasNext()) {
      arr.add(iter.next());
    }
    return arr.toArray();
  }

  protected Graph<E> copy(Graph<E> g) {
    if (isEmpty()) {
      return g;
    }

    CloneVisitor<E> visitor = new CloneVisitor<>(g);
    markAllAs(TraverseState.UNVISITED);
    bfs(lookup.firstEntry().getValue(), visitor);

    return visitor.toGraph();
  }

  protected Vertex<E> addVertex(E e) {
    return lookup.computeIfAbsent(e, Vertex::new);
  }

  private Iterator<E> iterator(VisitOrder order, E e) {
    if (isEmpty()) {
      return Collections.emptyIterator();
    }

    ListVisitor<E> visitor = new ListVisitor<>(size());
    markAllAs(TraverseState.UNVISITED);

    switch (order) {
      case DFS:
        dfs(lookup.get(e), visitor);
        break;
      case BFS:
      default:
        bfs(lookup.get(e), visitor);
        break;
    }

    return visitor.toList().iterator();
  }

  private void bfs(Vertex<E> v, GraphVisitor<E> visitor) {
    Queue<Vertex<E>> queue = new ArrayDeque<>(size());
    queue.add(v);

    do {
      Vertex<E> currV = queue.poll();

      if (currV.getVisit() == TraverseState.UNVISITED) {
        currV.setVisitAs(TraverseState.VISITED);
        visitor.apply(currV);
        queue.addAll(currV.getNeighbors());
      }
    } while (!queue.isEmpty());
  }

  private void dfs(Vertex<E> v, GraphVisitor<E> visitor) {
    if (visitor.state() == VisitorState.RETURN) {
      return;
    }

    v.setVisitAs(TraverseState.VISITED);
    visitor.apply(v);

    for (Vertex<E> e : v.getNeighbors()) {
      if (e.getVisit() == TraverseState.UNVISITED) {
        dfs(e, visitor);
      }
    }
  }

  private void markAllAs(TraverseState state) {
    for (Vertex<E> v : lookup.values()) {
      v.setVisitAs(state);
    }
  }
}

class DirectedGraph<E extends Comparable<E>> extends AbstractGraph<E> {

  @Override
  public void addEdge(E from, E to) {
    Vertex<E> vertexFrom = addVertex(from);
    Vertex<E> vertexTo = addVertex(to);

    vertexFrom.addNeighbor(vertexTo);
  }

  @Override
  public Graph<E> copy() {
    return copy(new DirectedGraph<>());
  }
}

class UndirectedGraph<E extends Comparable<E>> extends AbstractGraph<E> {

  @Override
  public void addEdge(E from, E to) {
    Vertex<E> vertexFrom = addVertex(from);
    Vertex<E> vertexTo = addVertex(to);

    vertexFrom.addNeighbor(vertexTo);
    vertexTo.addNeighbor(vertexFrom);
  }

  @Override
  public Graph<E> copy() {
    return copy(new UndirectedGraph<>());
  }

}
