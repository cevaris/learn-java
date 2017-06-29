package com.cevaris.hackerrank.sparseArrays;

import com.cevaris.test.utils.HackerRankTest;

import org.junit.Test;


public class SparseArraysTest extends HackerRankTest {

  private String case0 = String.join("\n",
      "4",
      "aba",
      "baba",
      "aba",
      "xzxb",
      "3",
      "aba",
      "xzxb",
      "ab"
  );

  @Test
  public void testCase0() {
    systemInMock.provideLines(case0);
    SparseArrays.main(new String[0]);
    assertOutput("2\n1\n0");
  }

}