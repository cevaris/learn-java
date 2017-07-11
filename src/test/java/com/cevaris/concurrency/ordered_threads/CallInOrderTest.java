package com.cevaris.concurrency.ordered_threads;

import org.junit.Test;

public class CallInOrderTest {

  @Test
  public void testCallInOrder() throws InterruptedException {
    CallInOrder test = new CallInOrder();
    test.doWork(5);
  }

}