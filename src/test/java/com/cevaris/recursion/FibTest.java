package com.cevaris.recursion;

import org.junit.Assert;
import org.junit.Test;

public class FibTest {

  @Test
  public void testSimpleRecursiveFib() {
    Fibonacci f = new SimpleRecursiveFib();
    assertFib(f);
  }

  @Test
  public void testMemoizedFib() {
    Fibonacci f = new MemoizedFib();
    assertFib(f);
    Assert.assertEquals(7778742049L, f.fib(49));
    Assert.assertEquals(12586269025L, f.fib(50));
    Assert.assertEquals(7540113804746346429L, f.fib(92));
  }

  @Test
  public void testMemoizedRecursiveFib() {
    Fibonacci f = new MemoizedRecursiveFib();
    assertFib(f);
  }

  private void assertFib(Fibonacci f) {
    try {
      Assert.assertEquals(0, f.fib(-1));
      Assert.fail();
    } catch (IllegalArgumentException e) {
    }

    try {
      Assert.assertEquals(0, f.fib(Integer.MIN_VALUE));
      Assert.fail();
    } catch (IllegalArgumentException e) {
    }

    Assert.assertEquals(1, f.fib(1));
    Assert.assertEquals(1, f.fib(2));
    Assert.assertEquals(2, f.fib(3));
    Assert.assertEquals(3, f.fib(4));
    Assert.assertEquals(28657, f.fib(23));
    Assert.assertEquals(5702887, f.fib(34));
  }

}