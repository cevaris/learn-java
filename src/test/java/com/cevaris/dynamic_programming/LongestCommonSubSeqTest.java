package com.cevaris.dynamic_programming;

import org.junit.Assert;
import org.junit.Test;

public class LongestCommonSubSeqTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullA() {
    LongestCommonSubSeq.calculate(null, "B");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullB() {
    LongestCommonSubSeq.calculate("A", null);
  }

  @Test()
  public void testEmpty() {
    Assert.assertEquals("", LongestCommonSubSeq.calculate("A", ""));
    Assert.assertEquals("", LongestCommonSubSeq.calculate("", "B"));
  }

  @Test()
  public void testCase0() {
    Assert.assertEquals("BDF", LongestCommonSubSeq.calculate("CBDF", "BDFG"));
  }
}