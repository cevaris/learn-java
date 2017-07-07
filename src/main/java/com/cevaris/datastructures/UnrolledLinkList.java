package com.cevaris.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class UnrolledLinkList<E> implements List<E> {

  private final int bucketSize;
  private final LinkedList<Block> blocks;

  private int size;

  private class Block {
    private final List<E> arr;

    Block(List<E> arr) {
      this.arr = arr;
    }

    public int size() {
      return arr.size();
    }

    public Iterator<E> iterator() {
      return arr.iterator();
    }

    public boolean add(E e) {
      size++;
      return arr.add(e);
    }

    public void add(int index, E e) {
      arr.add(index, e);
    }

    public E get(int index) {
      return arr.get(index);
    }

    public E popLast() {
      if (arr.isEmpty()) {
        throw new NoSuchElementException("no elements to pop");
      }
      size--;
      return arr.remove(size() - 1);
    }
  }

  public UnrolledLinkList(int initialCapacity) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("initialCapacity must be > 2");
    }

    this.bucketSize = (int) Math.sqrt(initialCapacity);
    this.blocks = new LinkedList<>();
  }

  private Block newBlock() {
    return new Block(new ArrayList<>(bucketSize));
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
    return size() == 0;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {

      private Iterator<Block> blockIter = blocks.iterator();
      private Iterator<E> innerIter;

      @Override
      public boolean hasNext() {
        return blockIter.hasNext() || (innerIter != null && innerIter.hasNext());
      }

      @Override
      public E next() {
        if (innerIter == null || !innerIter.hasNext()) {
          innerIter = blockIter.next().iterator();
        }
        return innerIter.next();
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
    if (blocks.isEmpty()) {
      blocks.add(newBlock());
    }

    Block last = blocks.getLast();
    if (last.size() >= bucketSize) {
      last = newBlock();
      blocks.add(last);
    }

    return last.add(e);
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
    if (index >= size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    int currIndex = index;
    Iterator<Block> blocksIter = blocks.iterator();
    Block currBlock;
    while (blocksIter.hasNext()) {
      currBlock = blocksIter.next();

      int targetIndex = currIndex - currBlock.size();
      if (targetIndex < 0) {
        return currBlock.get(currIndex);
      }

      currIndex -= currBlock.size();
    }

    return null; // should never get here...
  }

  @Override
  public E set(int index, E element) {
    return null;
  }

  @Override
  public void add(int index, E element) {
    if (index > size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    int currIndex = index;
    Iterator<Block> blocksIter = blocks.iterator();
    Block currBlock;
    while (blocksIter.hasNext()) {
      currBlock = blocksIter.next();

      // adding to end of list
      if ((currIndex - currBlock.size()) == 0 && !blocksIter.hasNext()) {
        Block last = newBlock();
        blocks.add(last);

        last.add(element);
        break;
      }

      // adding to existing block
      if ((currIndex - currBlock.size()) <= 0) {
        currBlock.add(currIndex, element);

        if (currBlock.size() > bucketSize) {
          E elementToShift = currBlock.popLast();
          prependAndShift(blocksIter, elementToShift);
        }
        break;
      }

      currIndex -= currBlock.size();
    }
  }

  private void prependAndShift(Iterator<Block> blocksIter, E element) {
    if (!blocksIter.hasNext()) {
      Block last = newBlock();
      last.add(element);
      blocks.add(last);
      return;
    }

    Block currBlock = blocksIter.next();
    currBlock.add(0, element);

    if (currBlock.size() > bucketSize) {
      // no space here, lets
      E elementToShift = currBlock.popLast();
      prependAndShift(blocksIter, elementToShift);
    }
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
