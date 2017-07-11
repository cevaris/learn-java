package com.cevaris.concurrency;

import java.util.concurrent.TimeUnit;

public class T {
  public static void log(String msg) {
    System.out.println(Thread.currentThread().getName() + " " + msg);
    System.out.flush();
  }

  public static void sleep(long ms) {
    sleep(ms, TimeUnit.MILLISECONDS);
  }

  public static void sleep(long time, TimeUnit unit) {
    try {
      Thread.sleep(unit.toMillis(time));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
