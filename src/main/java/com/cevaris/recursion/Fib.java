package com.cevaris.recursion;

import java.math.BigInteger;

interface Fibonacci {
  long fib(int n);

  BigInteger fibl(int n);
}

class SimpleRecursiveFib implements Fibonacci {
  @Override
  public long fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0) return 0;
    if (n == 1 || n == 2) return 1;
    return fib(n - 1) + fib(n - 2);
  }

  @Override
  public BigInteger fibl(int n) {
    return BigInteger.valueOf(fib(n));
  }
}

class MemoizedFib implements Fibonacci {
  @Override
  public long fib(int n) {
    return fibl(n).longValue();
  }

  @Override
  public BigInteger fibl(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0 || n == 1) return BigInteger.ONE;

    BigInteger[] m = new BigInteger[n + 1];
    m[0] = BigInteger.ONE;
    m[1] = BigInteger.ONE;

    for (int i = 2; i <= n; i++) {
      m[i] = m[i - 1].add(m[i - 2]);
    }

    return m[n - 1];
  }
}

class MemoizedRecursiveFib implements Fibonacci {
  @Override
  public long fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    return fib(n, new long[n + 1]);
  }

  private long fib(int n, long[] m) {
    if (n == 0) return 0;
    if (n <= 2) return 1;

    if (m[n] == 0) {
      m[n] = fib(n - 1) + fib(n - 2);
    }

    return m[n];
  }

  @Override
  public BigInteger fibl(int n) {
    return BigInteger.valueOf(fib(n));
  }
}
