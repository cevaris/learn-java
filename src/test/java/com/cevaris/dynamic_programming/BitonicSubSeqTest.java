package com.cevaris.dynamic_programming;

import org.junit.Assert;
import org.junit.Test;

public class BitonicSubSeqTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    BitonicSubSeq.calculate(null);
  }

  @Test
  public void testEmptyAndSingle() {
    Assert.assertEquals(0, BitonicSubSeq.calculate(new int[]{}));
    Assert.assertEquals(1, BitonicSubSeq.calculate(new int[]{1}));
  }

  @Test
  public void testMany() {
    Assert.assertEquals(4, BitonicSubSeq.calculate(new int[]{3, 4, 9, 7}));
    Assert.assertEquals(6, BitonicSubSeq.calculate(new int[]{1, 11, 2, 10, 4, 5, 2, 1}));
    Assert.assertEquals(5, BitonicSubSeq.calculate(new int[]{0, 60, 30, 40, 20, 10}));
    Assert.assertEquals(5, BitonicSubSeq.calculate(new int[]{12, 11, 40, 5, 3, 1}));
  }

}