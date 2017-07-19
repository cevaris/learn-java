package com.cevaris.datastructures.heap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

interface Heap<E> {
  E head();

  boolean add(E e);

  boolean addAll(List<E> c);

  Object[] toArray();

  int size();
}

abstract class AbstractHeap<E extends Comparable<E>> implements Heap<E> {
  private final List<E> ls;
  private final int capacity;

  private int size = 0;

  public AbstractHeap(int capacity) { this.capacity = capacity;
    ls = new ArrayList<>(capacity);
  }

  abstract protected void percolate(int i);

  @Override
  public boolean addAll(List<E> c) {
    for (E e : c) {
      if (!add(e)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean add(E e) {
    ls.add(e);
    percolate(size++);
    return true;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public E head() {
    if (size < 1) throw new NoSuchElementException();
    return ls.get(0);
  }

  @Override
  public Object[] toArray() {
    Iterator<E> iter = iterator();
    Object[] objects = new Object[size()];
    for (int i = 0; i < size(); i++) {
      objects[i] = iter.next();
    }
    return objects;
  }

  protected Iterator<E> iterator() {
    if (size() == 0) return Collections.emptyIterator();

    final Queue<Integer> toVisit = new ArrayDeque<>(size);
    toVisit.add(0);

    return new Iterator<E>() {
      @Override
      public boolean hasNext() {
        return !toVisit.isEmpty();
      }

      @Override
      public E next() {
        Integer i = toVisit.poll();

        if (i < 0 || i >= capacity) {
          throw new NoSuchElementException();
        }

        toVisit.add(getLeft(i));
        toVisit.add(getRight(i));

        return ls.get(i);
      }
    };

  }

  protected int getLeft(int i) {
    int left = 2 * i + 1;
    if (left < 0 || left >= size()) {
      return -1;
    }
    return left;
  }

  protected int getRight(int i) {
    int right = 2 * i + 2;
    if (right < 0 || right >= size()) {
      return -1;
    }
    return right;
  }

  protected int getParent(int i) {
    int parent = (i - 1) / 2;
    if (parent < 0 || parent >= size()) {
      return -1;
    }
    return parent;
  }

  protected E get(int i) {
    return ls.get(i);
  }

  protected void swap(int i, int j) {
    Collections.swap(ls, i, j);
  }
}

class MinHeap<E extends Comparable<E>> extends AbstractHeap<E> {
  public MinHeap(int capacity) {
    super(capacity);
  }

  @Override
  protected void percolate(int i) {
    int parent = getParent(i);
    if (parent < 0 || size() <= 1) return;

    E parentValue = get(parent);
    E currValue = get(i);

    if (parentValue.compareTo(currValue) > 0) {
      swap(parent, i);
    }

    percolate(parent);
  }
}
