package com.cevaris.test.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public abstract class HackerRankTest {

  private ByteArrayOutputStream baos;
  private PrintStream oldOut;

  @Before
  public void beforeEach() {
    // preserve old System.out
    oldOut = System.out;

    // init capture of System.out
    baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    System.setOut(ps);
  }

  @After
  public void afterEach() {
    // reset old System.out
    System.out.flush();
    System.setOut(oldOut);
  }

  @Rule
  public final TextFromStandardInputStream systemInMock =
      TextFromStandardInputStream.emptyStandardInputStream();

  protected void assertOutput(String expected) {
    Assert.assertEquals(expected, baos.toString().trim());
  }

}
