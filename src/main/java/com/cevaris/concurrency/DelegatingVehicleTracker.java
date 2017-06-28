package com.cevaris.concurrency;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

abstract class VehicleTracker {

  abstract public Map<String, Point> getLocations();

  abstract public Point getLocation(String id);

  abstract public void setLocation(String id, int x, int y);

  static class Point {
    final int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }
      final Point other = (Point) obj;
      return Objects.equals(this.x, other.x)
          && Objects.equals(this.y, other.y);
    }
  }
}

class DelegatingVehicleTracker extends VehicleTracker {

  private final Map<String, Point> locations =
      new ConcurrentHashMap<>();

  @Override
  public Point getLocation(String id) {
    return locations.get(id);
  }

  @Override
  public void setLocation(String id, int x, int y) {
    locations.put(id, new Point(x, y));
  }

  @Override
  public Map<String, Point> getLocations() {
    return Collections.unmodifiableMap(new HashMap<>(locations));
  }
}
