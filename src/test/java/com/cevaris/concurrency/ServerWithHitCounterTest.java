package com.cevaris.concurrency;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cevaris.RmiTest;

import org.junit.Assert;
import org.junit.Test;

public class ServerWithHitCounterTest extends RmiTest<ServerWithHitCounterIface> {

  private ServerWithHitCounter server = new ServerWithHitCounter();

  @Override
  protected String name() {
    return "ServerWithHitCounter";
  }

  @Override
  protected ServerWithHitCounterIface createServer() {
    return server;
  }

  class Worker implements Runnable {

    final private ServerWithHitCounterIface client;

    Worker(ServerWithHitCounterIface client) {
      this.client = client;
    }

    @Override
    public void run() {
      try {
        client.func();
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void testConcurrentHitCount() throws Exception {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    Long expectedCount = 100L;
    for (int i = 0; i < expectedCount; i++) {
      pool.execute(new Worker(client));
    }
    pool.awaitTermination(1, TimeUnit.SECONDS);
    Assert.assertEquals(expectedCount, server.getHitCount());
  }
}