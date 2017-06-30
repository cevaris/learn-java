package com.cevaris.concurrency;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

interface ResourcePool<A> {
  /**
   * In parallel, initializes all resources
   */
  void init();

  boolean isInitialized();

  A acquire();

  A acquire(long timeout, TimeUnit unit) throws InterruptedException;

  /**
   * Adds resource back to the pool for use
   *
   * @param a resource to add back to pool
   */
  void release(A a);
}

interface ResourceFactory<A> {
  A create();
}

interface Resource {
  /**
   * @return true if health, false otherwise
   */
  boolean isHealthy();

  /**
   * Initialize resource, ex; create Database/Remote connection
   */
  void init();
}

abstract class BasicPool<A extends Resource> implements ResourcePool<A> {
  private final BlockingQueue<A> pool;
  private final Timer timer = new Timer();
  private final TimerTask pruneUnhealthy = new ResourcePruner();

  private final AtomicBoolean initialized = new AtomicBoolean(false);

  BasicPool(ResourceFactory<A> factory, Integer size) {
    pool = new ArrayBlockingQueue<A>(size);
    for (int i = 0; i < size; i++) {
      pool.add(factory.create());
    }
  }

  @Override
  public boolean isInitialized() {
    return initialized.get();
  }

  @Override
  public void init() {
    final CountDownLatch initLatch = new CountDownLatch((int) (pool.size() * 0.75));

    for (A a : pool) {
      new Thread(() -> {
        a.init();
        if (a.isHealthy()) {
          initLatch.countDown();
        }
      }).start();
    }

    try {
      initLatch.await(1, TimeUnit.SECONDS);
      initialized.set(true);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    timer.schedule(pruneUnhealthy, 5000L);
    pruneUnhealthy.run();
  }

  @Override
  public A acquire(long timeout, TimeUnit unit) throws InterruptedException {
    return pool.poll(timeout, unit);
  }

  @Override
  public A acquire() {
    try {
      return acquire(Long.MAX_VALUE, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null; // should never get here
  }

  @Override
  public void release(A a) {
    pool.add(a);
  }


  private class ResourcePruner extends TimerTask {
    @Override
    public void run() {
      for (A a : pool) {
        if (!a.isHealthy()) {
          synchronized (pool) {
            pool.remove(a);
          }
        }
      }
    }
  }

}