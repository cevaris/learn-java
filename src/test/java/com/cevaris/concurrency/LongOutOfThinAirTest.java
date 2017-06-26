package com.cevaris.concurrency;

import java.util.concurrent.Callable;

import com.cevaris.test.utils.TestPool;

import org.junit.Assert;
import org.junit.Test;

public class LongOutOfThinAirTest {

  class UnsafeLong {
    private long number64 = 0L;

    public long get() {
      return number64;
    }

    public long getAndIncrement() {
      return number64++;
    }
  }

  class Worker implements Runnable, Callable<Runnable> {

    final private UnsafeLong unsafeLong;

    Worker(UnsafeLong unsafeLong) {
      this.unsafeLong = unsafeLong;
    }

    @Override
    public void run() {
      long currValue = unsafeLong.getAndIncrement();
    }

    @Override
    public Runnable call() throws Exception {
      return new Worker(unsafeLong);
    }
  }

  @Test
  public void testRandomLong() throws Exception {
    UnsafeLong unsafeLong = new UnsafeLong();

    int N = 10000;
    TestPool.executedFixedThreads(new Worker(unsafeLong), 100, N);

    // this should aways pass on 64-bit machines that guarantee atomic 64-bit operations
    Assert.assertTrue(unsafeLong.get() > 0 && unsafeLong.get() <= N);
  }

}