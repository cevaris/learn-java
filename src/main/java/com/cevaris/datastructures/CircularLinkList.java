package com.cevaris.datastructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CircularLinkList<E> implements List<E> {

  private Node<E> head;

  static private class Node<E> {
    E value;
    Node<E> next;

    public Node(E value, Node next) {
      this.value = value;
      this.next = next;
    }

    public Node(E value) {
      this.value = value;
    }
  }

  @Override
  public int size() {
    if (head == null)
      return 0;

    if (head.next == head)
      return 1;

    int size = 1;
    Node<E> curr = head;
    while (curr.next != head) {
      size++;
      curr = curr.next;
    }
    return size;
  }

  @Override
  public boolean isEmpty() {
    return head == null;
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
    int size = this.size();
    Object[] obj = new Object[size];

    if (head == null) {
      return obj;
    }

    int i = 0;
    obj[i] = head.value;

    Node<E> curr = head.next;
    while(curr != head){
      i++;
      obj[i] = curr.value;
      curr = curr.next;
    }

    return obj;
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(E e) {
    if (head == null) {
      head = new Node<>(e);
      head.next = head;
      return true;
    }

    Node<E> curr = head;
    while (curr.next != head) {
      curr = curr.next;
    }

    curr.next = new Node<E>(e, head);
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
