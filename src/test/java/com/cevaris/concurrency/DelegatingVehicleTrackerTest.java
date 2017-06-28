package com.cevaris.concurrency;

import java.util.Map;

import com.cevaris.test.utils.TestPool;
import com.cevaris.test.utils.TestWorker;

import org.junit.Test;


public class DelegatingVehicleTrackerTest {

  class Writer extends TestWorker {

    final private VehicleTracker tracker;

    Writer(VehicleTracker tracker) {
      this.tracker = tracker;
    }

    @Override
    public void run() {
      int i = getIteration();
      tracker.setLocation(String.valueOf(i), i * 2, i * 3);
    }

    @Override
    public TestWorker call() throws Exception {
      return new Writer(tracker);
    }
  }

  class Reader extends TestWorker {

    final private VehicleTracker tracker;

    Reader(VehicleTracker tracker) {
      this.tracker = tracker;
    }

    @Override
    public void run() {
      final int i = getIteration();
      final String location = String.valueOf(i);
      final VehicleTracker.Point point = new VehicleTracker.Point(i * 2, i * 3);

      VehicleTracker.Point actual = tracker.getLocation(location);
      if (!point.equals(actual)) {
        throw new AssertionError();
      }

      Map<String, VehicleTracker.Point> locations = tracker.getLocations();
      if (!point.equals(locations.get(location))) {
        throw new AssertionError();
      }
    }

    @Override
    public TestWorker call() throws Exception {
      return new Reader(tracker);
    }
  }

  @Test
  public void testConcurrentReadWrite() throws Exception {
    VehicleTracker tracker = new DelegatingVehicleTracker();
    TestPool.executedFixedThreads(new Writer(tracker), 100, 200);
    TestPool.executedFixedThreads(new Reader(tracker), 100, 200);
  }

}