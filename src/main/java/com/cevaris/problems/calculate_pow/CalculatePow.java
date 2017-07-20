package com.cevaris.problems.calculate_pow;


import java.math.BigDecimal;
import java.math.RoundingMode;

class CalculatePow {

  static BigDecimal pow(double base, int exp) {
    BigDecimal bdResult = BigDecimal.ONE;
    BigDecimal bdBase = BigDecimal.valueOf(base);
    int times = Math.abs(exp);

    for (int i = 0; i < times; i++) {
      bdResult = bdResult.multiply(bdBase);
    }

    if (exp > 0) {
      return bdResult;
    } else {
      return BigDecimal.ONE.divide(bdResult, 5, RoundingMode.HALF_EVEN);
    }
  }

}
