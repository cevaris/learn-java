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
    Assert.assertEquals(12586269025L, f.fib(49));
    Assert.assertEquals(20365011074L, f.fib(50));
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
    Assert.assertEquals(3, f.fib(3));
    Assert.assertEquals(46368, f.fib(23));
    Assert.assertEquals(9227465, f.fib(34));
  }

}