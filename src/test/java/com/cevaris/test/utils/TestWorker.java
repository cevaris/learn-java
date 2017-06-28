package com.cevaris.test.utils;

import java.util.concurrent.Callable;

public abstract class TestWorker implements Runnable, Callable<TestWorker> {

  private int iteration;

  public void setIteration(int i) {
    iteration = i;
  }

  public int getIteration() {
    return iteration;
  }
}