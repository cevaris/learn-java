package com.cevaris.problems.calculate_pow;

import java.math.BigDecimal;
import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class CalculatePowTest {

  @Test
  public void testCalculateZero() {
    Assert.assertEquals(bigDecimal("0.00"), CalculatePow.pow(0, 2));
    Assert.assertEquals(bigDecimal("1.00000"), CalculatePow.pow(100, 0));
  }

  @Test
  public void testCalculateNegativeNumbers() {
    Assert.assertEquals(
        bigDecimal("0.01000"),
        CalculatePow.pow(10, -2)
    );

    Assert.assertEquals(
        bigDecimal("0.00826"),
        CalculatePow.pow(11, -2)
    );
  }

  @Test
  public void testCalculateAll() {
    List<Integer> baseInts = TestUtils.until(-50, 50);
    List<Integer> expInts = TestUtils.until(1, 10);
    for (Integer a : baseInts) {
      for (Integer b : expInts) {
        Assert.assertEquals(String.format("%d^%d", a, b), Math.pow(a, b), CalculatePow.pow(a, b).doubleValue(), Double.MIN_VALUE);
      }
    }
  }

  @Test
  public void testCalculateLargeNumbers() {
    Assert.assertEquals(bigDecimal("4.00"), CalculatePow.pow(2, 2));
    Assert.assertTrue(
        bigDecimal("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
            .compareTo(CalculatePow.pow(10, 100)) == 0);

    Assert.assertTrue(
        bigDecimal("10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
            .compareTo(CalculatePow.pow(10, 1000)) == 0);
  }

  private BigDecimal bigDecimal(String value) {
    return new BigDecimal(value);
  }

  private BigDecimal bigDecimal(Double value) {
    return BigDecimal.valueOf(value);
  }
}