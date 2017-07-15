package com.cevaris.datastructures.lists;


import java.util.Deque;
import java.util.List;

public interface OpenLinkList<E> extends List<E>, Deque<E> {
  boolean addAfter(Node<E> target, E e);

  boolean addBefore(Node<E> target, E e);

  Node<E> addNode(E e);

  boolean removeNode(Node<E> e);

  Node<E> getNode(int index);

  Node<E> first();

  Node<E> last();
}
