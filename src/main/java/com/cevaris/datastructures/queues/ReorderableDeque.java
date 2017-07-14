package com.cevaris.datastructures.queues;

import java.util.Deque;


public interface ReorderableDeque<K> extends Deque<K> {

  void moveToLast(K k);

}