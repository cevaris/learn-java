package com.cevaris.concurrency;

import com.cevaris.test.utils.RmiTest;

import org.junit.Assert;
import org.junit.Test;

public class StatelessServerTest extends RmiTest<StatelessServerIface> {

  private StatelessServer server = new StatelessServer();

  @Override
  protected String name() {
    return "StatelessServer";
  }

  @Override
  protected StatelessServerIface createServer() {
    return server;
  }

  @Test
  public void testFactorSuccessfully() throws Exception {
    Assert.assertEquals(Integer.valueOf(4), client.doubler(2));
  }

}