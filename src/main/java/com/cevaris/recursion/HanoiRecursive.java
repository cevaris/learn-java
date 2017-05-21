package com.cevaris.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HanoiRecursive {

  static HanoiStack<Integer> solve(List<Integer> ls) {
    HanoiStack<Integer> dest = new HanoiStack<Integer>("to");
    HanoiStack<Integer> aux = new HanoiStack<Integer>("mid");
    HanoiStack<Integer> source = new HanoiStack<Integer>("from", ls);

    return go(source.size(), source, dest, aux);
  }

  private static HanoiStack<Integer> go(int n, HanoiStack<Integer> source, HanoiStack<Integer> dest, HanoiStack<Integer> aux) {
    if (n <= 1) {
      move(source, dest, aux);
      return dest;
    }

    go(n - 1, source, aux, dest);

    move(source, dest, aux);

    return go(n - 1, aux, dest, source);
  }

  private static void move(final HanoiStack<Integer> from, final HanoiStack<Integer> to, final HanoiStack<Integer> aux) {
    if ((!from.isEmpty() && !to.isEmpty()) && to.peek() < from.peek()) {
      throw new IllegalArgumentException(String.format("invalid move %s %s", from, to));
    } else {
      to.push(from.pop());
      printHanoiStacks(from, to, aux);
    }
  }

  private static void printHanoiStacks(HanoiStack<Integer> from, HanoiStack<Integer> to, HanoiStack<Integer> aux) {
    List<String> output = new ArrayList<String>() {{
      add(from.toString());
      add(aux.toString());
      add(to.toString());
    }};
    Collections.sort(output);
    System.out.println(String.join(",", output));
  }
}
