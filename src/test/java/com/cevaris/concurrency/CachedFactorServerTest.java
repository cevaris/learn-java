package com.cevaris.concurrency;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cevaris.RmiTest;

import org.junit.Assert;
import org.junit.Test;

public class CachedFactorServerTest extends RmiTest<CachedFactorServerIface> {

  private static Random rand = new Random(System.currentTimeMillis());

  private CachedFactorServer server = new CachedFactorServer();

  @Override
  protected String name() {
    return "CachedFactorServer";
  }

  @Override
  protected CachedFactorServerIface createServer() {
    return server;
  }

  @Test
  public void testConcurrentHitCount() throws Exception {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    Long expectedCount = 1000L;
    for (int i = 0; i < expectedCount; i++) {
      pool.execute(new Worker(client));
    }
    pool.awaitTermination(1, TimeUnit.SECONDS);
    Assert.assertEquals(expectedCount, server.getRequestCount());
  }


  class Worker implements Runnable {

    final private CachedFactorServerIface client;

    Worker(CachedFactorServerIface client) {
      this.client = client;
    }

    @Override
    public void run() {
      try {
        client.calcFactors(BigInteger.valueOf(rand.nextInt(10)));
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }
}