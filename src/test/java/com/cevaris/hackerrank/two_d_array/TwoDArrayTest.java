package com.cevaris.hackerrank.two_d_array;

import com.cevaris.test.utils.HackerRankTest;

import org.junit.Test;


public class TwoDArrayTest extends HackerRankTest {

  String case0 = String.join("\n",
      "1 1 1 0 0 0",
      "0 1 0 0 0 0",
      "1 1 1 0 0 0",
      "0 0 2 4 4 0",
      "0 0 0 2 0 0",
      "0 0 1 2 4 0"
  );

  String case1 = String.join("\n",
      "1 1  1  0  0 0",
      "0 1  0  0  0 0",
      "1 1  1  0  0 0",
      "0 9  2 -4 -4 0",
      "0 0  0 -2  0 0",
      "0 0 -1 -2 -4 0"
  );

  @Test
  public void testCase0() {
    systemInMock.provideLines(case0);
    TwoDArray.main(new String[0]);
    assertOutput("19");
  }

  @Test
  public void testCase1() {
    systemInMock.provideLines(case1);
    TwoDArray.main(new String[0]);
    assertOutput("13");
  }

}