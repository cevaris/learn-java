package com.cevaris.datastructures;


class DNode<E> {
  private DNode<E> prev;
  private DNode<E> next;
  private final E value;

  DNode(E value) {
    this.value = value;
  }

  public void setPrev(DNode<E> prev) {
    this.prev = prev;
  }

  public void setNext(DNode<E> next) {
    this.next = next;
  }

  public DNode<E> getPrev() {
    return prev;
  }

  public DNode<E> getNext() {
    return next;
  }

  public E getValue() {
    return value;
  }
}
