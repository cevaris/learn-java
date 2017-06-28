package com.cevaris.test.utils;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestPool {

  public static void executedFixedThreads(TestWorker newWorker, int threadCount, int N) throws Exception {
    ExecutorService pool = Executors.newFixedThreadPool(threadCount);
    for (int i = 0; i < N; i++) {
      TestWorker worker = newWorker.call();
      worker.setIteration(i);
      pool.execute(worker);
    }
    pool.awaitTermination(1, TimeUnit.SECONDS);
  }

  public static void executedFixedThreads(TestWorker newWorker, int N) throws Exception {
    executedFixedThreads(newWorker, Runtime.getRuntime().availableProcessors(), N);
  }

}
