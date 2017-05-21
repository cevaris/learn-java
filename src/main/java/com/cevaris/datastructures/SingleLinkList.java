package com.cevaris.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SingleLinkList<E> implements List<E> {

  private Node<E> head;

  static private class Node<E> {
    E value;
    Node next;

    public Node(E value, Node next) {
      this.value = value;
      this.next = next;
    }
  }

  @Override
  public int size() {
    int size = 0;
    if (head != null) {
      Node curr = head;
      while (curr != null) {
        size++;
        curr = curr.next;
      }
    }

    return size;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    Object[] collection = new Object[size()];

    if (head != null) {

      int idx = 0;
      Node curr = head;
      while (curr != null) {
        collection[idx] = curr.value;
        idx++;
        curr = curr.next;
      }
    }
    return collection;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(E e) {
    if (head == null) {
      head = new Node<>(e, null);
    } else if (head.next == null) {
      head.next = new Node<>(e, null);
    } else {
      Node curr = head;
      while (curr.next != null) {
        curr = curr.next;
      }
      curr.next = new Node<>(e, null);
    }
    return true;
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
    if (c.isEmpty()) {
      return false;
    } else {
      for (E e : c) {
        add(e);
      }
      return true;
    }
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
