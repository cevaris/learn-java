package com.cevaris.datastructures.cache;


import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.cevaris.datastructures.lists.DoubleLinkList;
import com.cevaris.datastructures.lists.Node;
import com.cevaris.datastructures.lists.OpenLinkList;

public class LruCache<K, V> implements Cache<K, V> {

  private final int capacity;
  private final Map<K, Node<Map.Entry<K, V>>> map;
  private final OpenLinkList<Map.Entry<K, V>> orderLs;

  public LruCache(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>(capacity);
    orderLs = new DoubleLinkList<>();
  }

  @Override
  public V get(K k) {
    Node<Map.Entry<K, V>> node = map.get(k);
    orderLs.removeNode(node);
    orderLs.add(node.getValue());
    return node.getValue().getValue();
  }

  @Override
  public V put(K k, V v) {
    if (map.size() >= capacity) {
      Map.Entry<K, V> entry = orderLs.pop();
      map.remove(entry.getKey());
    }

    Node<Map.Entry<K, V>> entryNode = orderLs.addNode(new AbstractMap.SimpleEntry<>(k, v));
    map.put(k, entryNode);
    return v;
  }

  @Override
  public V remove(K k) {
    Node<Map.Entry<K, V>> entryNode = map.remove(k);
    orderLs.removeNode(entryNode);
    return entryNode.getValue().getValue();
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> set = new HashSet<>();
    for (Node<Map.Entry<K, V>> node : map.values()) {
      set.add(node.getValue());
    }
    return set;
  }
}