package com.cevaris.problems.min_xor;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class MinXorTest {

  MinXor test = new MinXor();

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    test.findMinXor(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmpty() {
    test.findMinXor(new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSingle() {
    ArrayList<Integer> A = new ArrayList<>();
    A.add(1);
    test.findMinXor(A);
  }

  @Test
  public void test0() {
    ArrayList<Integer> A = new ArrayList<>();
    A.add(0);
    A.add(2);
    A.add(5);
    A.add(7);
    Assert.assertEquals(2, test.findMinXor(A));
  }

  @Test
  public void test1() {
    ArrayList<Integer> A = new ArrayList<>();
    A.add(0);
    A.add(4);
    A.add(7);
    A.add(9);
    Assert.assertEquals(3, test.findMinXor(A));
  }

  @Test
  public void test2() {
    ArrayList<Integer> A = new ArrayList<>(
        Arrays.asList(3, 2, 13, 1, 5, 13, 0, 13, 13)
    );
    Assert.assertEquals(0, test.findMinXor(A));
  }

}