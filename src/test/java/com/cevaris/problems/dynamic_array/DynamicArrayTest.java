package com.cevaris.problems.dynamic_array;

import com.cevaris.test.utils.HackerRankTest;

import org.junit.Test;

public class DynamicArrayTest extends HackerRankTest {

  String case0 = String.join("\n",
      "2 5",
      "1 0 5",
      "1 1 7",
      "1 0 3",
      "2 1 0",
      "2 1 1"
  );

  @Test
  public void testCase0() {
    systemInMock.provideLines(case0);
    DynamicArray.main(new String[0]);
    assertOutput("7\n3");
  }

}