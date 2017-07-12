package com.cevaris.datastructures.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * http://igoro.com/archive/skip-lists-are-fascinating/
 */
public class SkipList<E extends Comparable> implements List<E> {

  private LinkedList<Node> ls;
  private final Random rand;

  private int upperBoundLevel = 32;
  private int currMaxLevel = 0;

  SkipList() {
    this(1234567890L);
  }

  SkipList(long seed) {
    rand = new Random(seed);
    ls = new LinkedList<>();
  }

  private class Node {
    private final E value;
    private final List<Node> next;

    Node(E e, int level) {
      value = e;
      next = new ArrayList<>(level);
    }

    Node nextNodeAtLevel(int level) {
      return next.get(level);
    }

    void setNodeAtLevel(int level, Node node) {
      next.set(level, node);
    }

    E getValue() {
      return value;
    }
  }

  @Override
  public int size() {
    return ls.size();
  }

  @Override
  public boolean isEmpty() {
    return ls.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private final Iterator<Node> iter = ls.iterator();

      @Override
      public boolean hasNext() {
        return iter.hasNext();
      }

      @Override
      public E next() {
        return iter.next().getValue();
      }
    };
  }

  @Override
  public Object[] toArray() {
    Object[] obj = new Object[size()];
    Iterator<E> iter = iterator();
    for (int i = 0; iter.hasNext(); i++) {
      obj[i] = iter.next();
    }
    return obj;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(E e) {
    int targetLevel = 0;
    for (int R = rand.nextInt(); (R & 1) == 1; R >>= 1) {
      targetLevel++;
      if (targetLevel == currMaxLevel) {
        currMaxLevel++;
        break;
      }
    }

    // Insert this node into the skip list
    Node newNode = new Node(e, targetLevel + 1);

    Iterator<Node> iter = ls.iterator();

    if (!iter.hasNext()) {
      return ls.add(newNode);
    }

    return false;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public E get(int index) {
    return null;
  }

  @Override
  public E set(int index, E element) {
    return null;
  }

  @Override
  public void add(int index, E element) {

  }

  @Override
  public E remove(int index) {
    return null;
  }

  @Override
  public int indexOf(Object o) {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator<E> listIterator() {
    return null;
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return null;
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return null;
  }
}
