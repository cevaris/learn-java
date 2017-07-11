package com.cevaris.concurrency.dining_philosophers;

import org.junit.Ignore;


public class DiningPhilosophersV1Test {

  @Ignore
  public void testDinnerTime() throws InterruptedException {
    DiningPhilosophers diningPhilosophers = new DiningPhilosophers(5);
    diningPhilosophers.start(2_000);
  }

}