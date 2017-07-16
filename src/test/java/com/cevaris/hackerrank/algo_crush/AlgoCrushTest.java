package com.cevaris.hackerrank.algo_crush;

import com.cevaris.test.utils.HackerRankTest;

import org.junit.Test;


public class AlgoCrushTest extends HackerRankTest {

  private String case0 = String.join("\n",
      "5 3",
      "1 2 100",
      "2 5 100",
      "3 4 100"
  );

  @Test
  public void testCase0() {
    systemInMock.provideLines(case0);
    AlgoCrush.main(new String[0]);
    assertOutput("200");
  }

}