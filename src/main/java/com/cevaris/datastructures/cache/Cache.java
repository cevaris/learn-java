package com.cevaris.datastructures.cache;

import java.util.Map;
import java.util.Set;

interface Cache<K, V> {
  V get(K k);

  V put(K k, V v);

  V remove(K k);

  Set<Map.Entry<K, V>> entrySet();
}
