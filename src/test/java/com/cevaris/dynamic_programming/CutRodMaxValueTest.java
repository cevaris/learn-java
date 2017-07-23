package com.cevaris.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class CutRodMaxValueTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNull() {
    CutRodMaxValue.calculate(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmpty() {
    CutRodMaxValue.calculate(new HashMap<>(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSingleArg() {
    HashMap<Integer, Integer> lenPrice = new HashMap<>();
    lenPrice.put(1, 1);
    CutRodMaxValue.calculate(lenPrice, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnbalancedLenPriceAndL() {
    HashMap<Integer, Integer> lenPrice = new HashMap<>();
    lenPrice.put(1, 1);
    lenPrice.put(2, 1);
    CutRodMaxValue.calculate(lenPrice, 5);
  }

  @Test
  public void testCase0() {
    Map<Integer, Integer> lenPrices = new HashMap<>();
    lenPrices.put(1, 2);
    lenPrices.put(2, 3);
    lenPrices.put(3, 3);
    lenPrices.put(4, 5);

    CutRodMaxValue test = new CutRodMaxValue();
    Assert.assertEquals(6, CutRodMaxValue.calculate(lenPrices, 4));
  }

  @Test
  public void testCase1() {
    Map<Integer, Integer> lenPrices = new HashMap<>();
    lenPrices.put(1, 1);
    lenPrices.put(2, 5);
    lenPrices.put(3, 8);
    lenPrices.put(4, 9);
    lenPrices.put(5, 10);
    lenPrices.put(6, 17);
    lenPrices.put(7, 17);
    lenPrices.put(8, 20);

    CutRodMaxValue test = new CutRodMaxValue();
    Assert.assertEquals(22, CutRodMaxValue.calculate(lenPrices, 8));
  }

}