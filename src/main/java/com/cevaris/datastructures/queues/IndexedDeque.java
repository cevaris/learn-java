package com.cevaris.datastructures.queues;

import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.cevaris.datastructures.lists.DoubleLinkList;
import com.cevaris.datastructures.lists.Node;
import com.cevaris.datastructures.lists.OpenLinkList;

public class IndexedDeque<K> implements Deque<K> {

  private final DoubleLinkList<K> ls;
  private final Map<K, Node<K>> map;

  public IndexedDeque(int capacity) {
    ls = new OpenLinkList<K>();
    map = new HashMap<>(capacity);
  }

  @Override
  public void addFirst(K k) {
  }

  @Override
  public void addLast(K k) {
  }

  @Override
  public boolean offerFirst(K k) {
    return false;
  }

  @Override
  public boolean offerLast(K k) {
    return false;
  }

  @Override
  public K removeFirst() {
    return null;
  }

  @Override
  public K removeLast() {
    return null;
  }

  @Override
  public K pollFirst() {
    return null;
  }

  @Override
  public K pollLast() {
    return null;
  }

  @Override
  public K getFirst() {
    return null;
  }

  @Override
  public K getLast() {
    return null;
  }

  @Override
  public K peekFirst() {
    return null;
  }

  @Override
  public K peekLast() {
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
  public boolean add(K k) {
    Node<K> node = ls.addNode(k);
    map.put(k, node);
    return true;
  }

  @Override
  public boolean offer(K k) {
    return false;
  }

  @Override
  public K remove() {
    return null;
  }

  @Override
  public K poll() {
    return null;
  }

  @Override
  public K element() {
    return null;
  }

  @Override
  public K peek() {
    return null;
  }

  @Override
  public void push(K k) {

  }

  @Override
  public K pop() {
    K k = ls.pop();
    map.remove(k);
    return k;
  }

  @Override
  public boolean remove(Object o) {
    Node<K> node = map.get(o);
    return ls.removeNode(node);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends K> c) {
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
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public int size() {
    return ls.size();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Iterator<K> iterator() {
    return ls.iterator();
  }

  @Override
  public Object[] toArray() {
    return ls.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public Iterator<K> descendingIterator() {
    return null;
  }
}
