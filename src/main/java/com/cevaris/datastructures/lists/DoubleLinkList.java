package com.cevaris.datastructures.lists;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkList<E> implements OpenLinkList<E> {

  private Node<E> head = null;
  private Node<E> last = null;
  private int size = 0;

  @Override
  public boolean removeNode(Node<E> curr) {
    if (curr == null) {
      return false; // did not find target
    }

    if (curr == head) {
      head = curr.getNext();
    } else if (curr == last) {
      last = curr.getPrev();
      curr.getPrev().setNext(null);
    } else {
      curr.getNext().setPrev(curr.getPrev());
      curr.getPrev().setNext(curr.getNext());
    }

    size--;
    return true;
  }

  @Override
  public Node<E> addNode(E e) {
    add(e);
    return last();
  }

  @Override
  public Node<E> first() {
    return head;
  }

  @Override
  public Node<E> last() {
    return last;
  }

  @Override
  public Node<E> getNode(int index) {
    Iterator<Node<E>> iter = nodeIterator();
    Node<E> curr;
    int i = -1;
    do {
      i++;
      curr = iter.next();
    }
    while (i < index && iter.hasNext());
    return curr;
  }

  @Override
  public boolean addAfter(Node<E> target, E e) {
    Node<E> newNode = new Node<>(e);

    if (isEmpty()) {
      head = newNode;
      last = newNode;
    } else {
      newNode.setPrev(target);
      if (target.getNext() != null) {
        newNode.setNext(target.getNext());
        target.getNext().setPrev(newNode);
      }
      target.setNext(newNode);

      if (target == last) {
        last = newNode;
      }
    }

    size++;
    return true;
  }

  @Override
  public boolean addBefore(Node<E> target, E e) {
    Node<E> newNode = new Node<>(e);

    if (isEmpty()) {
      head = newNode;
      last = newNode;
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
    addAfter(last, e);
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
    Node<E> curr = head;
    if (remove(curr.getValue())) {
      return curr.getValue();
    } else {
      throw new NoSuchElementException();
    }
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
    if (last == null) {
      throw new NoSuchElementException();
    }
    return last.getValue();
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
    return removeFirst();
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
      Iterator<Node<E>> iterator = nodeIterator();

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
    return addAfter(last, e);
  }

  @Override
  public boolean remove(Object o) {
    Node<E> curr = head;
    while (curr != null && !curr.getValue().equals(o)) {
      curr = curr.getNext();
    }

    return removeNode(curr);
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

  private Iterator<Node<E>> nodeIterator() {
    return new Iterator<Node<E>>() {
      Node<E> curr = head;

      @Override
      public boolean hasNext() {
        return curr != null;
      }

      @Override
      public Node<E> next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        Node<E> tmp = curr;
        curr = curr.getNext();
        return tmp;
      }
    };
  }
}