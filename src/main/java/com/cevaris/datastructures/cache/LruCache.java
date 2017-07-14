package com.cevaris.datastructures.cache;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.cevaris.datastructures.queues.LinkedReorderableDeque;
import com.cevaris.datastructures.queues.ReorderableDeque;

public class LruCache<K, V> implements Cache<K, V> {

  private final int capacity;
  private final Map<K, V> map;
  private final ReorderableDeque<K> insertionList;

  public LruCache(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>(capacity);
    insertionList = new LinkedReorderableDeque<>(capacity);
  }

  @Override
  public V get(K k) {
    insertionList.moveToLast(k);
    return map.get(k);
  }

  @Override
  public V put(K k, V v) {
    if (map.size() >= capacity) {
      K leastUsedKey = insertionList.pop();
      map.remove(leastUsedKey);
    }

    insertionList.add(k);
    return map.put(k, v);
  }

  @Override
  public V evict(K k) {
    insertionList.remove(k);
    return map.remove(k);
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    return map.entrySet();
  }
}