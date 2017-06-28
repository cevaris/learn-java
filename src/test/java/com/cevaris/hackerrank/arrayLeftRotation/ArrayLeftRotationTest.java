package com.cevaris.hackerrank.arrayLeftRotation;

import com.cevaris.test.utils.HackerRankTest;

import org.junit.Test;

public class ArrayLeftRotationTest extends HackerRankTest {

  private String case0 = String.join("\n",
      "5 4",
      "1 2 3 4 5"
      );

  private String case1 = String.join("\n",
      "5 1",
      "1 2 3 4 5"
  );

  private String case2 = String.join("\n",
      "5 3",
      "1 2 3 4 5"
  );

  @Test
  public void testCase0() {
    systemInMock.provideLines(case0);
    ArrayLeftRotation.main(new String[0]);
    assertOutput("5 1 2 3 4");
  }

  @Test
  public void testCase1() {
    systemInMock.provideLines(case1);
    ArrayLeftRotation.main(new String[0]);
    assertOutput("2 3 4 5 1");
  }

  @Test
  public void testCase2() {
    systemInMock.provideLines(case2);
    ArrayLeftRotation.main(new String[0]);
    assertOutput("4 5 1 2 3");
  }
}