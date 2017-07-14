package com.cevaris.datastructures.cache;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class LruCacheTest {

  @Test
  public void testAdd() {
    Cache<Long, Long> actual = new LruCache<>(2);
    actual.put(1L, 1L + 1L); // will be evicted
    actual.put(2L, 2L + 2L);
    actual.put(3L, 3L + 3L);

    Map<Long, Long> expected = new HashMap<>();
    expected.put(2L, 2L + 2L);
    expected.put(3L, 3L + 3L);

    Assert.assertEquals(expected.entrySet(), actual.entrySet());
  }

  @Test
  public void testEvict() {
    Cache<Long, Long> actual = new LruCache<>(2);
    actual.put(1L, 1L + 1L); // will be removed
    actual.put(2L, 2L + 2L);
    actual.evict(1L);


    Map<Long, Long> expected = new HashMap<>();
    expected.put(2L, 2L + 2L);

    Assert.assertEquals(expected.entrySet(), actual.entrySet());
  }

  @Test
  public void testGet() {
    Cache<Long, Long> actual = new LruCache<>(2);
    actual.put(1L, 1L + 1L);
    actual.put(2L, 2L + 2L); // will be evicted

    actual.get(1L); // touches key so we will hold on to
    actual.put(3L, 3L + 3L); // brings to full capacity

    Map<Long, Long> expected = new HashMap<>();
    expected.put(1L, 1L + 1L);
    expected.put(3L, 3L + 3L);

    Assert.assertEquals(expected.entrySet(), actual.entrySet());
  }


}