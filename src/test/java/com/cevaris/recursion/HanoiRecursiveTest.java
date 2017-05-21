package com.cevaris.recursion;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HanoiRecursiveTest {

  @Before
  public void beforeEach(){
    System.out.println("");
  }

  @Test
  public void solveWith3Disks() {
    Stack<Integer> source = new Stack<Integer>();
    source.push(3);
    source.push(2);
    source.push(1);

    Object[] expected = {3, 2, 1};
    HanoiStack<Integer> actual = HanoiRecursive.solve(source);
    Assert.assertArrayEquals(expected, actual.toArray());
  }

  @Test
  public void solveWithEvenDisks() {
    Stack<Integer> source = new Stack<Integer>();
    source.push(4);
    source.push(3);
    source.push(2);
    source.push(1);

    Object[] expected = {4, 3, 2, 1};
    HanoiStack<Integer> actual = HanoiRecursive.solve(source);
    Assert.assertArrayEquals(expected, actual.toArray());
  }

}