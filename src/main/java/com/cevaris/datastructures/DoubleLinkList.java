package com.cevaris.datastructures;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

interface DoubleLinkList<E> extends List<E>, Deque<E> {
  boolean addAfter(DNode<E> target, E e);

  boolean addBefore(DNode<E> target, E e);
}

class DefaultDoubleLinkList<E> implements DoubleLinkList<E> {

  private DNode<E> head = null;
  private DNode<E> tail = null;
  private int size = 0;

  @Override
  public boolean addAfter(DNode<E> target, E e) {
    DNode<E> node = new DNode<>(e);

    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      node.setPrev(target);
      if (target.getNext() != null) {
        node.setNext(target.getNext());
        target.getNext().setPrev(node);
      }
      target.setNext(node);

      if (target == tail) {
        tail = node;
      }
    }

    size++;
    return true;
  }

  @Override
  public boolean addBefore(DNode<E> target, E e) {
    DNode<E> newNode = new DNode<>(e);

    if (isEmpty()) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.setNext(target);

      if (target.getPrev() != null) {
        target.getPrev().setNext(newNode);
        newNode.setPrev(target.getPrev());
        target.setPrev(newNode);
      }
      target.setPrev(newNode);

      if (target == head) {
        head = newNode;
      }
    }

    size++;
    return true;
  }

  @Override
  public void addFirst(E e) {
    addBefore(head, e);
  }

  @Override
  public void addLast(E e) {
    addAfter(tail, e);
  }

  @Override
  public boolean offerFirst(E e) {
    return false;
  }

  @Override
  public boolean offerLast(E e) {
    return false;
  }

  @Override
  public E removeFirst() {
    return null;
  }

  @Override
  public E removeLast() {
    return null;
  }

  @Override
  public E pollFirst() {
    return null;
  }

  @Override
  public E pollLast() {
    return null;
  }

  @Override
  public E getFirst() {
    if (head == null) {
      throw new NoSuchElementException();
    }
    return head.getValue();
  }

  @Override
  public E getLast() {
    if (tail == null) {
      throw new NoSuchElementException();
    }
    return tail.getValue();
  }

  @Override
  public E peekFirst() {
    return null;
  }

  @Override
  public E peekLast() {
    return null;
  }

  @Override
  public boolean removeFirstOccurrence(Object o) {
    return false;
  }

  @Override
  public boolean removeLastOccurrence(Object o) {
    return false;
  }

  @Override
  public boolean offer(E e) {
    return false;
  }

  @Override
  public E remove() {
    return null;
  }

  @Override
  public E poll() {
    return null;
  }

  @Override
  public E element() {
    return null;
  }

  @Override
  public E peek() {
    return null;
  }

  @Override
  public void push(E e) {

  }

  @Override
  public E pop() {
    return null;
  }

  @Override
  public Iterator<E> descendingIterator() {
    return null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      Iterator<DNode<E>> iterator = nodeIterator();

      @Override
      public boolean hasNext() {
        return iterator.hasNext();
      }

      @Override
      public E next() {
        return iterator.next().getValue();
      }
    };
  }

  @Override
  public Object[] toArray() {
    ArrayList<E> ls = new ArrayList<>();
    Iterator<E> iter = iterator();
    while (iter.hasNext()) {
      ls.add(iter.next());
    }
    return ls.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(E e) {
    DNode<E> currTail = tail;

    DNode<E> node = new DNode<>(e);

    if (isEmpty()) {
      head = node;
      tail = node;
    } else {
      currTail.setNext(node);
      node.setPrev(currTail);
      tail = node;
    }

    size++;
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
    for (E e : c) {
      add(e);
    }
    return true;
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
    size++;

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

  private Iterator<DNode<E>> nodeIterator() {
    return new Iterator<DNode<E>>() {
      DNode<E> curr = head;

      @Override
      public boolean hasNext() {
        return curr != null;
      }

      @Override
      public DNode<E> next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        DNode<E> tmp = curr;
        curr = curr.getNext();
        return tmp;
      }
    };
  }
}