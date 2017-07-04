package com.cevaris.concurrency;

import com.cevaris.test.utils.TestPool;
import com.cevaris.test.utils.TestWorker;

import org.junit.Assert;
import org.junit.Test;


public class MemorizerTest {

  @Test
  public void testSingleKeyInsertion() throws Exception {
    long value = System.currentTimeMillis();
    Computable<Long, String> func = (Long v) -> String.valueOf(System.currentTimeMillis());
    Memorizer<Long, String> cache = new Memorizer<>(func);

    String actual = cache.compute(value);

    int N = 100;
    TestPool.executedFixedThreads(new SingleKeyWorker(value, cache), N);

    Assert.assertEquals(1, cache.size());
    Assert.assertEquals((long) N, cache.getCacheHits());
    Assert.assertEquals((long) 1, cache.getMisses());
    Assert.assertEquals(cache.compute(value), actual);
  }

  @Test
  public void testMultiKeyInsertion() throws Exception {
    long value = System.currentTimeMillis();
    Computable<Long, String> func = (Long v) -> String.valueOf(System.currentTimeMillis());
    Memorizer<Long, String> cache = new Memorizer<>(func);

    int N = 100;
    TestPool.executedFixedThreads(new MultiKeyWorker(value, cache), N);
    TestPool.executedFixedThreads(new MultiKeyWorker(value, cache), N);

    Assert.assertEquals(N, cache.size());
    Assert.assertEquals(N, cache.getCacheHits());
    Assert.assertEquals(N, cache.getMisses());
  }

  private class SingleKeyWorker extends TestWorker {

    final private Long key;
    final private Memorizer<Long, String> cache;

    SingleKeyWorker(Long key, Memorizer<Long, String> cache) {
      this.key = key;
      this.cache = cache;
    }

    @Override
    public void run() {
      cache.compute(key);
    }

    @Override
    public TestWorker call() throws Exception {
      return new SingleKeyWorker(key, cache);
    }
  }

  private class MultiKeyWorker extends TestWorker {

    final private Long key;
    final private Memorizer<Long, String> cache;

    MultiKeyWorker(Long key, Memorizer<Long, String> cache) {
      this.key = key;
      this.cache = cache;
    }

    @Override
    public void run() {
      cache.compute(key + getIteration());
    }

    @Override
    public TestWorker call() throws Exception {
      return new MultiKeyWorker(key, cache);
    }
  }
}