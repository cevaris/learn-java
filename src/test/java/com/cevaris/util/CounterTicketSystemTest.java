package com.cevaris.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import com.cevaris.test.utils.TestPool;
import com.cevaris.test.utils.TestUtils;
import com.cevaris.test.utils.TestWorker;

import org.junit.Assert;
import org.junit.Test;

public class CounterTicketSystemTest {

  @Test
  public void testPartialConcurrency() throws Exception {
    TicketSystem actual = new CounterTicketSystem();
    Map<String, Long> observer = new ConcurrentHashMap<>();
    int N = 100;

    TestPool.executedFixedThreads(new TicketCustomerRequester(actual, observer), N);
    TestPool.executedFixedThreads(new TicketCustomerWhoami(actual, observer), N);
    TestPool.executedFixedThreads(new TicketCustomerCaller(actual, observer), N);

    Assert.assertNull(actual.call());
  }

  @Test
  public void testConcurrency() throws Exception {
    TicketSystem actual = new CounterTicketSystem();
    Set<Long> observer = new ConcurrentSkipListSet<>();
    int N = 500;

    TestPool.executedFixedThreads(new TicketCustomer(actual, observer), N);
    Assert.assertNull(actual.call());
    Assert.assertTrue(observer.isEmpty());
  }

  @Test
  public void testStatus() throws Exception {
    TicketSystem actual = new CounterTicketSystem();

    Long ticket0 = actual.request("0");
    Long ticket1 = actual.request("1");
    Long ticket2 = actual.request("2");

    Assert.assertEquals(Long.valueOf(0), actual.status(ticket0));
    Assert.assertEquals(Long.valueOf(1), actual.status(ticket1));
    Assert.assertEquals(Long.valueOf(2), actual.status(ticket2));

    Assert.assertEquals("0", actual.whoami(ticket0));
    Assert.assertEquals("1", actual.whoami(ticket1));
    Assert.assertEquals("2", actual.whoami(ticket2));

    Assert.assertEquals(ticket0, actual.call());
    Assert.assertEquals(ticket1, actual.call());
    Assert.assertEquals(ticket2, actual.call());
    Assert.assertNull(actual.call());
  }

  private class TicketCustomerRequester extends TestWorker {

    private final TicketSystem ts;
    private final Map<String, Long> observer;

    TicketCustomerRequester(TicketSystem ts, Map<String, Long> observer) {
      this.ts = ts;
      this.observer = observer;
    }

    @Override
    public void run() {
      String name = String.valueOf(getIteration());
      Long ticket = ts.request(name);
      observer.put(name, ticket);
    }

    @Override
    public TestWorker call() throws Exception {
      return new TicketCustomerRequester(ts, observer);
    }
  }

  private class TicketCustomerWhoami extends TestWorker {

    private final TicketSystem ts;
    private final Map<String, Long> observer;

    TicketCustomerWhoami(TicketSystem ts, Map<String, Long> observer) {
      this.ts = ts;
      this.observer = observer;
    }

    @Override
    public void run() {
      String name = String.valueOf(getIteration());
      Assert.assertEquals(ts.whoami(observer.get(name)), String.valueOf(getIteration()));

      try {
        ts.request(name);
        Assert.fail("should not allow duplicate names");
      } catch (IllegalArgumentException e) {
        // expected exception
      }
    }

    @Override
    public TestWorker call() throws Exception {
      return new TicketCustomerWhoami(ts, observer);
    }
  }

  private class TicketCustomerCaller extends TestWorker {

    private final TicketSystem ts;
    private final Map<String, Long> observer;

    TicketCustomerCaller(TicketSystem ts, Map<String, Long> observer) {
      this.ts = ts;
      this.observer = observer;
    }

    @Override
    public void run() {
      ts.call();
    }

    @Override
    public TestWorker call() throws Exception {
      return new TicketCustomerCaller(ts, observer);
    }
  }

  private class TicketCustomer extends TestWorker {

    private final TicketSystem ts;
    private final Set<Long> observer;

    TicketCustomer(TicketSystem ts, Set<Long> observer) {
      this.ts = ts;
      this.observer = observer;
    }

    @Override
    public void run() {
      String name = String.valueOf(getIteration());
      Long ticket = ts.request(name);
      observer.add(ticket);
      // potentially another ticket/name

      TestUtils.sleep(100);
      Long someTicket = ts.call();
      Assert.assertTrue(observer.remove(someTicket));

      TestUtils.sleep(100);
      Assert.assertNull(ts.whoami(someTicket));
      Assert.assertNull(ts.status(someTicket));
    }

    @Override
    public TestWorker call() throws Exception {
      return new TicketCustomer(ts, observer);
    }
  }
}