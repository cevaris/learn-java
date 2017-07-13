package com.cevaris.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


interface Graph<E> {
  void addEdge(E a, E b);

  Object[] toArray(VisitOrder order, E start);

  int size();

  boolean isEmpty();
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
    final Graph<E> graph = new DirectedGraph<>();
    for (Edge<E> e : ls) {
      graph.addEdge(e.getFrom(), e.getTo());
    }
    return graph;
  }
}

class DirectedGraph<E> implements Graph<E> {
  private final LinkedList<Vertex<E>> vertices;
  private final Map<E, Vertex<E>> lookup;

  DirectedGraph() {
    vertices = new LinkedList<>();
    lookup = new HashMap<>();
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public void addEdge(E from, E to) {
    Vertex<E> vertexFrom = lookup.computeIfAbsent(from, Vertex::new);
    vertices.add(vertexFrom);

    Vertex<E> vertexTo = lookup.computeIfAbsent(to, Vertex::new);
    vertices.add(vertexTo);

    vertexFrom.addNeighbor(vertexTo);
  }

  @Override
  public int size() {
    return vertices.size();
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
    Queue<Vertex<E>> queue = new ArrayBlockingQueue<>(size());
    queue.add(v);

    do {
      Vertex<E> currV = queue.poll();

      if (currV.getVisit() == TraverseState.UNVISITED) {
        currV.setVisit(TraverseState.VISITED);
        visitor.apply(currV);
        queue.addAll(currV.getNeighbors());
      }

    } while (!queue.isEmpty());
  }

  private void dfs(Vertex<E> v, GraphVisitor<E> visitor) {
    v.setVisit(TraverseState.VISITED);
    visitor.apply(v);

    for (Vertex<E> e : v.getNeighbors()) {
      if (e.getVisit() == TraverseState.UNVISITED) {
        dfs(e, visitor);
      }
    }
  }

  private void markAllAs(TraverseState state) {
    for (Vertex<E> v : vertices) {
      v.setVisit(state);
    }
  }
}
