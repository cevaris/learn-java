package com.cevaris.problems.square_root_int;

import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class SquareRootIntTest {
  @Test
  public void testSqrt() {
    SquareRootInt test = new SquareRootInt();
    List<Integer> ints = TestUtils.until(0, 1000);
    for (Integer i : ints) {
      Assert.assertEquals(String.format("failed on sqrt(%d)", i), (int) Math.floor(Math.sqrt(i)), test.sqrt(i));
    }
  }

  @Test
  public void textLargeNumber() {
    SquareRootInt test = new SquareRootInt();
    int i = Integer.MAX_VALUE;
    Assert.assertEquals(String.format("failed on sqrt(%d)", i), (int) Math.floor(Math.sqrt(i)), test.sqrt(i));
  }
}