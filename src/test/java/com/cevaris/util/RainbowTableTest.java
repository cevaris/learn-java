package com.cevaris.util;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class RainbowTableTest {

  class StrongReferenceClass {
    String value = "value";
  }

  @Test
  public void testWeakReference() throws ExecutionException, InterruptedException, TimeoutException {
    final RainbowTable table = new WeakRainbowTable();
    int N = 10_000;
    List<Integer> ints = TestUtils.newList(0, N);
    for (Integer integer : ints) {
      table.add(integer.toString());
    }
    StrongReferenceClass strong = new StrongReferenceClass();
    table.add(strong.value);

    int expectedSizeBeforeGC = N + 1;
    int expectedSizeAfterGC = 1;
    Assert.assertEquals(expectedSizeBeforeGC, table.size());

    FutureTask<Integer> f = new FutureTask<>(() -> {
      while (table.size() == expectedSizeBeforeGC) {
        System.gc(); // requests gc and all weak references get evicted
        TestUtils.sleep(100);
      }
      return table.size();
    });

    f.run();
    int actual = f.get(5, TimeUnit.SECONDS);

    Assert.assertNotEquals(N, actual);
    // should be only have reference to above inserted strong value
    Assert.assertTrue(table.size() == expectedSizeAfterGC);
  }

}