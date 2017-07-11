package com.cevaris.concurrency.dining_philosophers;

import org.junit.Ignore;

public class DiningPhilosophersTest {

  @Ignore
  public void testDinnerTime() throws InterruptedException {
    DiningPhilosophers diningPhilosophers = new DiningPhilosophers(5);
    diningPhilosophers.start(5_000);
  }

}