package com.cevaris.recursion;

import java.util.Collections;
import java.util.List;
import java.util.Stack;


class HanoiStack<A> extends Stack<A> {
  private String label;

  public HanoiStack(String label, List<A> ls) {
    this.label = label;
    this.addAll(ls);
  }

  public HanoiStack(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  @Override
  public synchronized String toString() {
    return String.format("%s:%s", label, super.toString());
  }
}