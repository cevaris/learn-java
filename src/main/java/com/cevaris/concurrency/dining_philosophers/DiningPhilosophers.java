package com.cevaris.concurrency.dining_philosophers;


import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    for (int i = 0; i < numberOfPhilosophers; i++) {
      dinnerTable.submit(new LeftyRightyPhilosopher(waiter, clock));
    }

    Thread.sleep(runForMs * 10000);
//    clock.endDinner();
//    Thread.sleep(runForMs / 2);
//    dinnerTable.shutdown();
//    dinnerTable.awaitTermination(1, TimeUnit.DAYS);
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
    while (true) {

      boolean isDinnerTime = clock.isDinnerTime();
      boolean interrupted = Thread.currentThread().isInterrupted();

      if (!isDinnerTime || interrupted) {
        stopEating();
        printStatus("finished dinner");
        break;
      }

      if (left == null) {
        printStatus("waiting left");
        left = waiter.askForLeft();
        printStatus("got left");
        continue;
      }

      if (right == null) {
        printStatus("waiting right");
        right = waiter.askForRight();
        printStatus("got right");
        continue;
      }

      printStatus("is eating");
      stopEating();
    }
  }

  private void printStatus(String msg) {
    System.out.println(Thread.currentThread().getName() + " " + msg + "[ " + left + "," + right + "]");
    System.out.flush();
  }

  private void stopEating() {
    if (left != null) {
      waiter.returnLeft(left);
      left = null;
      printStatus("released left");
    }
    if (right != null) {
      waiter.returnRight(right);
      right = null;
      printStatus("released right");
    }
  }

  private boolean isTimeToEat() {
    return clock.isDinnerTime() && !Thread.currentThread().isInterrupted();
  }
}


class Fork {
  @Override
  public String toString() {
    return "fork";
  }
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
  Fork askForLeft();

  Fork askForRight();

  void returnLeft(Fork fork);

  void returnRight(Fork fork);
}

class BenitoTheWaiter implements Waiter {

  private final Semaphore semaphoreRight;
  private final Semaphore semaphoreLeft;
  private final Deque<Fork> forks;

  public BenitoTheWaiter(int numberOfForks) {
    semaphoreLeft = new Semaphore(numberOfForks - (numberOfForks / 2));
    semaphoreRight = new Semaphore(numberOfForks / 2);

    forks = new LinkedList<>();
    for (int i = 0; i < numberOfForks; i++) {
      forks.add(new Fork());
    }
  }

  @Override
  public Fork askForRight() {
    return ask(semaphoreRight);
  }

  @Override
  public Fork askForLeft() {
    return ask(semaphoreLeft);
  }

  private Fork ask(Semaphore s) {
    try {
      s.tryAcquire(10, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return forks.pop();
  }

  @Override
  public void returnLeft(Fork fork) {
    returnFork(semaphoreLeft, fork);
  }

  @Override
  public void returnRight(Fork fork) {
    returnFork(semaphoreRight, fork);
  }

  private void returnFork(Semaphore s, Fork fork) {
    forks.addLast(fork);
    s.release();
  }
}