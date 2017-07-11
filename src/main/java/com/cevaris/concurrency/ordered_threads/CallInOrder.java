package com.cevaris.concurrency.ordered_threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.cevaris.concurrency.T;


abstract class WorkerType implements Runnable {
  protected final Foo foo;

  public WorkerType(Foo foo) {
    this.foo = foo;
  }
}

class WorkerTypeA extends WorkerType {
  public WorkerTypeA(Foo foo) {
    super(foo);
  }

  @Override
  public void run() {
    foo.one();
  }
}

class WorkerTypeB extends WorkerType {
  public WorkerTypeB(Foo foo) {
    super(foo);
  }

  @Override
  public void run() {
    foo.two();
  }
}

class WorkerTypeC extends WorkerType {
  public WorkerTypeC(Foo foo) {
    super(foo);
  }

  @Override
  public void run() {
    foo.three();
  }
}

class WorkerTypePoison extends WorkerType {
  public WorkerTypePoison() {
    super(null);
  }

  @Override
  public void run() {
  }
}

class Foo {
  void one() {
    T.log("one");
  }

  void two() {
    T.log("two");
  }

  void three() {
    T.log("three");
  }
}

class CallInOrder {
  private final ExecutorService service;
  private final BlockingQueue<WorkerType> queue;

  public CallInOrder() {
    this.service = Executors.newWorkStealingPool(3);
    this.queue = new LinkedBlockingQueue<>();
  }

  boolean doWork(int repeat) {
    Foo foo = new Foo();

    WorkerTypeA wa = new WorkerTypeA(foo);
    WorkerTypeB wb = new WorkerTypeB(foo);
    WorkerTypeC wc = new WorkerTypeC(foo);
    service.submit(new CallWorker(queue, wa));
    service.submit(new CallWorker(queue, wb));
    service.submit(new CallWorker(queue, wc));

    for (int i = 0; i < repeat; i++) {
      queue.add(wa);
      queue.add(wb);
      queue.add(wc);
    }

    WorkerType stopWorking = new WorkerTypePoison();
    queue.add(stopWorking);
    queue.add(stopWorking);
    queue.add(stopWorking);

    while (!queue.isEmpty()) {
      T.sleep(100);
    }

    service.shutdown();
    return true;
  }
}

class CallWorker implements Runnable {
  private final BlockingQueue<WorkerType> queue;
  private final WorkerType workerType;

  public CallWorker(BlockingQueue<WorkerType> queue, WorkerType workerType) {
    this.queue = queue;
    this.workerType = workerType;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      WorkerType currWorker = queue.peek();
      if (currWorker.getClass() == WorkerTypePoison.class) {
        T.log("ate poison");
        queue.poll();
        break; // exit queue
      }
      if (currWorker.getClass() == workerType.getClass()) {
        workerType.run(); // do work
        queue.poll(); // ack
      }
    }
    T.log("shutting down");
  }
}
