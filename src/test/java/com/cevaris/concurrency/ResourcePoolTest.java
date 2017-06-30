package com.cevaris.concurrency;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

public class ResourcePoolTest {

  @Test
  public void testSuccessfulInitialization() {
    BasicPool pool = new ConnectionPool(5);
    pool.init();
    Assert.assertTrue(pool.isInitialized());
  }

  @Test
  public void testAcquireRelease() throws InterruptedException {
    BasicPool pool = new ConnectionPool(2);
    pool.init();

    Resource r1 = pool.acquire();
    Assert.assertNotNull(r1);
    Resource r2 = pool.acquire();
    Assert.assertNotNull(r2);

    Resource r3 = pool.acquire(10L, TimeUnit.MILLISECONDS);
    Assert.assertNull(r3);

    // add resource back to pool
    pool.release(r1);

    Resource r4 = pool.acquire();
    Assert.assertNotNull(r4);

    Resource r5 = pool.acquire(10L, TimeUnit.MILLISECONDS);
    Assert.assertNull(r5);
  }

}

class ConnectionFactory implements ResourceFactory<Connection> {
  @Override
  public Connection create() {
    return new Connection();
  }
}

class ConnectionPool extends BasicPool<Connection> {
  ConnectionPool(Integer size) {
    super(new ConnectionFactory(), size);
  }
}

class Connection implements Resource {
  @Override
  public void init() {
    try {
      Thread.sleep(100L);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public boolean isHealthy() {
    return true;
  }
}