package com.cevaris.datastructures.lists;


class Node<E> {
  private Node<E> prev;
  private Node<E> next;
  private final E value;

  Node(E value) {
    this.value = value;
  }

  public void setPrev(Node<E> prev) {
    this.prev = prev;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }

  public Node<E> getPrev() {
    return prev;
  }

  public Node<E> getNext() {
    return next;
  }

  public E getValue() {
    return value;
  }
}
