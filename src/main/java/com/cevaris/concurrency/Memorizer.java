package com.cevaris.concurrency;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicLong;

interface Computable<I, O> {
  O compute(I i);
}

public class Memorizer<I, O> implements Computable<I, O> {

  private final Computable<I, O> func;
  private final ConcurrentMap<I, Future<O>> cache =
      new ConcurrentHashMap<>();

  private final AtomicLong cacheHits = new AtomicLong(0);
  private final AtomicLong misses = new AtomicLong(0);


  public Memorizer(Computable<I, O> func) {
    this.func = func;
  }

  public long size() {
    return cache.size();
  }

  public long getCacheHits() {
    return cacheHits.get();
  }

  public long getMisses() {
    return misses.get();
  }

  @Override
  public O compute(I i) {
    while (true) {
      Future<O> future = cache.get(i);

      if (future == null) {
        FutureTask<O> task = new FutureTask<>(() -> func.compute(i));
        future = cache.putIfAbsent(i, task);

        if (future == null) {
          misses.incrementAndGet();
          future = task;
          task.run();
        }
      } else {
        cacheHits.incrementAndGet();
      }

      try {
        return future.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
  }
}
