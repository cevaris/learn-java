package com.cevaris.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class UnrolledLinkList<E> implements List<E> {

  private Block head;
  private final int bucketSize;

  private class Block {
    List<E> ls;
    Block next;

    Block(List<E> ls, Block next) {
      this.ls = ls;
      this.next = next;
    }

    public int size() {
      return ls.size();
    }
  }


  public UnrolledLinkList(int bucketSize) {
    this.bucketSize = bucketSize;
  }

  @Override
  public int size() {
    int size = 0;
    Iterator<E> iter = iterator();
    for (; iter.hasNext(); iter.next()) {
      size++;
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
    return new Iterator<E>() {
      Block currBlock = head;
      Iterator<E> currBlockIter = assignIter();

      @Override
      public boolean hasNext() {
        if (currBlock == null) {
          return false;
        }

        return currBlockIter.hasNext() || currBlock.next != null;
      }

      @Override
      public E next() {
        if (currBlock == null) {
          throw new NoSuchElementException();
        }
        if (currBlockIter == null) {
          currBlockIter = currBlock.ls.iterator();
        }
        if (currBlockIter.hasNext()) {
          return currBlockIter.next();
        } else {
          currBlockIter = null;
          currBlock = currBlock.next;
          return next();
        }
      }

      private Iterator<E> assignIter() {
        if (currBlock != null) {
          return currBlock.ls.iterator();
        } else {
          return null;
        }
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
    if (head == null) {
      head = new Block(new ArrayList<>(bucketSize), null);
      head.ls.add(e);
      return true;
    }

    Block currBlock = head;
    while (currBlock.next != null) {
      currBlock = currBlock.next;
    }

    if (currBlock.size() == bucketSize) {
      currBlock.next = new Block(new ArrayList<>(bucketSize), null);
      currBlock.next.ls.add(e);
    } else {
      currBlock.ls.add(e);
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
    if (c.isEmpty())
      return false;

    for (E s : c) {
      add(s);
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
