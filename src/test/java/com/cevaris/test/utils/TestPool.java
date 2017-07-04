package com.cevaris.test.utils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestPool {

  public static ExecutorService executedFixedThreads(TestWorker newWorker, int threadCount, int N, int waitSeconds) throws Exception {
    ExecutorService pool = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < N; i++) {
      TestWorker worker = newWorker.call();
      worker.setIteration(i);
      pool.execute(worker);
    }

    if (waitSeconds == 0) {
      return pool;
    } else {
      pool.awaitTermination(waitSeconds, TimeUnit.SECONDS);
      return pool;
    }
  }

  public static ExecutorService executedFixedThreads(TestWorker newWorker, int N) throws Exception {
    return executedFixedThreads(newWorker, Runtime.getRuntime().availableProcessors(), N, 1);
  }

  public static ExecutorService executedFixedThreadsBlock(TestWorker newWorker, int N) throws Exception {
    return executedFixedThreads(newWorker, Runtime.getRuntime().availableProcessors(), N, 0);
  }
}
