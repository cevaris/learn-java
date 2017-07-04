package com.cevaris.patterns.bridge;


import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

// Implementor
interface Compute<I, O> {
  O compute(I i);
}

// Concrete Implementor (1/2)
class ComputeSquared implements Compute<Integer, Long> {
  @Override
  public Long compute(Integer i) {
    return (long) Math.pow(i, 2);
  }
}

// Concrete Implementor (2/2)
class ComputeDouble implements Compute<Integer, Long> {
  @Override
  public Long compute(Integer i) {
    return (long) (i * 2);
  }
}

// Abstraction
abstract class Computer<I, O> {
  protected final Compute<I, O> func;

  Computer(Compute<I, O> func) {
    this.func = func;
  }

  abstract O submit(I i);
}

// Redefined Abstraction (1/3)
class FutureComputer<I, O> extends Computer<I, O> {
  private final ConcurrentMap<I, Future<O>> futures = new ConcurrentHashMap<>();

  FutureComputer(Compute<I, O> func) {
    super(func);
  }

  O submit(final I i) {
    while (true) {
      Future<O> future = futures.get(i);
      if (future == null) {
        Callable<O> c = new Callable<O>() {
          final Compute<I, O> f = func;

          @Override
          public O call() throws Exception {
            return f.compute(i);
          }
        };
        FutureTask<O> task = new FutureTask<O>(c);
        future = futures.putIfAbsent(i, task);
        if (future == null) {
          future = task;
          task.run();
        }
      }
      try {
        return future.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }
  }
}

// Redefined Abstraction (2/3)
class SyncComputer<I, O> extends Computer<I, O> {
  SyncComputer(Compute<I, O> func) {
    super(func);
  }

  @Override
  O submit(I i) {
    return func.compute(i);
  }
}

// Redefined Abstraction (3/3)
class LatchBatchComputer<I, O> extends Computer<I, O> {

  private final ConcurrentMap<I, O> results = new ConcurrentHashMap<>();

  private final int numOfThreads;
  private final ExecutorService pool;
  private CountDownLatch latch;

  LatchBatchComputer(Compute<I, O> func, int numOfThreads) {
    super(func);
    this.numOfThreads = numOfThreads;
    this.pool = Executors.newFixedThreadPool(numOfThreads);

    newLatch();
  }

  @Override
  O submit(I i) {
    pool.submit(new Worker(i));
    try {
      latch.await();
      newLatch();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    O o = results.get(i);
    results.remove(i);
    return o;
  }

  class Worker implements Runnable {
    private final I i;

    Worker(I i) {
      this.i = i;
    }

    @Override
    public void run() {
      results.putIfAbsent(i, func.compute(i));
      latch.countDown();
    }
  }

  private void newLatch() {
    latch = new CountDownLatch(numOfThreads);
  }
}