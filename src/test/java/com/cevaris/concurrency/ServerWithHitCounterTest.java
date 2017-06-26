package com.cevaris.concurrency;

import java.rmi.RemoteException;
import java.util.concurrent.Callable;

import com.cevaris.RmiTest;
import com.cevaris.test.utils.TestPool;

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

  class Worker implements Runnable, Callable<Runnable> {

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

    @Override
    public Runnable call() throws Exception {
      return new Worker(client);
    }
  }

  @Test
  public void testConcurrentHitCount() throws Exception {
    int expectedCount = 1000;
    TestPool.executedFixedThreads(new Worker(client), expectedCount);
    Assert.assertEquals(Long.valueOf(expectedCount), server.getHitCount());
  }
}