package com.cevaris.bits;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class SumBitDifferencesTest {

  SumBitDifferences test = new SumBitDifferences();

  @Test
  public void testCase0() {
    ArrayList<Integer> data = new ArrayList<>(
        Arrays.asList(1, 3, 5)
    );
    Assert.assertEquals(8, test.cntBits(data));
  }

}