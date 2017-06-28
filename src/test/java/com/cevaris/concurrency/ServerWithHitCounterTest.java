package com.cevaris.concurrency;

import java.rmi.RemoteException;

import com.cevaris.test.utils.RmiTest;
import com.cevaris.test.utils.TestPool;
import com.cevaris.test.utils.TestWorker;

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

  class Worker extends TestWorker {

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
    public TestWorker call() throws Exception {
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