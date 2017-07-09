package com.cevaris.concurrency.dining_philosophers;


import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DiningPhilosophers {

  private final ExecutorService dinnerTable;
  private final int numberOfPhilosophers;

  public DiningPhilosophers(int numberOfPhilosophers) {
    dinnerTable = Executors.newFixedThreadPool(numberOfPhilosophers);
    this.numberOfPhilosophers = numberOfPhilosophers;
  }

  void start(long runForMs) throws InterruptedException {
    final Waiter waiter = new BenitoTheWaiter(numberOfPhilosophers);
    final DiningClock clock = new ChefsWatch();
    final Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];

    Arrays.fill(philosophers, new LeftyRightyPhilosopher(waiter, clock));
    for (Philosopher p : philosophers) {
      dinnerTable.submit(p);
    }

    Thread.sleep(runForMs);
    clock.endDinner();
    dinnerTable.shutdown();
    dinnerTable.awaitTermination(1, TimeUnit.DAYS);
  }

}

interface Philosopher extends Runnable {
}

class LeftyRightyPhilosopher implements Philosopher {
  private final Waiter waiter;
  private final DiningClock clock;

  private Fork left;
  private Fork right;

  public LeftyRightyPhilosopher(Waiter waiter, DiningClock clock) {
    this.waiter = waiter;
    this.clock = clock;
  }

  @Override
  public void run() {
    while (isTimeToEat()) {

      if (left == null) {
        printStatus("waiting left");
        left = waiter.askForFork();
        printStatus("got left");
      } else if (right == null) {
        printStatus("waiting right");
        right = waiter.askForFork();
        printStatus("got right");
      } else {
        printStatus("is eating");
        stopEating();
      }
    }

    stopEating();
    printStatus("finished dinner");
  }

  private void printStatus(String msg) {
    System.out.println(Thread.currentThread().getId() + " " + msg);
  }

  private void stopEating() {
    if (left != null) {
      waiter.returnFork(left);
    }
    if (right != null) {
      waiter.returnFork(right);
    }
    right = null;
    left = null;
  }

  private boolean isTimeToEat() {
    return clock.isDinnerTime() && !Thread.interrupted();
  }
}


class Fork {
}

interface DiningClock {
  boolean isDinnerTime();

  void endDinner();
}

class ChefsWatch implements DiningClock {
  private volatile boolean isKitchenOpen = true;

  @Override
  public boolean isDinnerTime() {
    return isKitchenOpen;
  }

  @Override
  public void endDinner() {
    isKitchenOpen = false;
    System.out.println("no more dinner!!!");
  }
}

interface Waiter {
  Fork askForFork();

  void returnFork(Fork fork);
}

class BenitoTheWaiter implements Waiter {

  private final Semaphore semaphore;
  private final Queue<Fork> forks;

  public BenitoTheWaiter(int numberOfForks) {
    semaphore = new Semaphore(numberOfForks - 1);
    forks = new LinkedBlockingQueue<>(numberOfForks);
    for (int i = 0; i < numberOfForks; i++) {
      forks.add(new Fork());
    }
  }

  @Override
  public Fork askForFork() {
    try {
      while (semaphore.tryAcquire(10, TimeUnit.MILLISECONDS)) {
        System.out.println(Thread.currentThread().getId() + " waiting");
      }

      return forks.poll();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    return null;
  }

  @Override
  public void returnFork(Fork fork) {
    forks.add(fork);
    semaphore.release();
  }
}