package com.cevaris.patterns.bridge;

import com.cevaris.test.utils.TestPool;
import com.cevaris.test.utils.TestWorker;

import org.junit.Assert;
import org.junit.Test;

public class ComputerTest {

  class Worker extends TestWorker {

    final private Computer<Integer, Long> computer;

    Worker(Computer<Integer, Long> computer) {
      this.computer = computer;
    }

    @Override
    public void run() {
      Long result = computer.submit(getIteration());
    }

    @Override
    public TestWorker call() throws Exception {
      return new Worker(computer);
    }
  }

  @Test
  public void testAsyncComputeDouble() throws Exception {
    Computer<Integer, Long> computer = new FutureComputer<>(
        new ComputeDouble()
    );

    int N = 100;
    TestPool.executedFixedThreads(new Worker(computer), N);

    Assert.assertEquals((long) computer.submit(10), 20L);
    Assert.assertEquals((long) computer.submit(20), 40L);
  }

  @Test
  public void testSyncComputeSquared() throws Exception {
    Computer<Integer, Long> computer = new SyncComputer<>(
        new ComputeSquared()
    );

    Assert.assertEquals((long) computer.submit(10), 100L);
    Assert.assertEquals((long) computer.submit(20), 400L);
  }
}