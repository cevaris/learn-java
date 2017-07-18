package com.cevaris.recursion;

interface Fibonacci {
  long fib(int n);
}

class SimpleRecursiveFib implements Fibonacci {
  @Override
  public long fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0 || n == 1) return 1;
    return fib(n - 1) + fib(n - 2);
  }
}

class MemoizedFib implements Fibonacci {
  @Override
  public long fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    if (n == 0 || n == 1) return 1;

    long[] m = new long[n + 1];
    m[0] = 1;
    m[1] = 1;

    for (int i = 2; i <= n; i++) {
      if (m[i] == 0) {
        m[i] = m[i - 1] + m[i - 2];
      }
      if (i == n) break;
    }

    return m[n];
  }
}

class MemoizedRecursiveFib implements Fibonacci {
  @Override
  public long fib(int n) {
    if (n < 0) throw new IllegalArgumentException();
    return fib(n, new long[n + 1]);
  }

  private long fib(int n, long[] m) {
    if (n <= 1) return 1;

    if (m[n] == 0) {
      m[n] = fib(n - 1) + fib(n - 2);
    }

    return m[n];
  }
}
