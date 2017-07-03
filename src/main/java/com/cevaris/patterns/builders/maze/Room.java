package com.cevaris.patterns.builders.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Room implements MapSite {

  private final MapSite[] sides = new MapSite[4];

  private final int roomNumber;

  public Room(int roomNumber) {
    this.roomNumber = roomNumber;
  }

  @Override
  public void enter() {
  }

  public int getRoomNumber() {
    return roomNumber;
  }

  public void setSide(Direction direction, MapSite site) {
    sides[direction.ordinal()] = site;
  }

  public Set<Direction> openSides() {
    List<Direction> ls = new ArrayList<>(4);
    for (Direction direction : Direction.values()) {
      MapSite x = sides[direction.ordinal()];
      if (x instanceof Wall) {
        ls.add(direction);
      }
    }
    return new HashSet<Direction>(ls);
  }

  public MapSite getSide(Direction direction) {
    return sides[direction.ordinal()];
  }

  @Override
  public int hashCode() {
    return Objects.hash(sides, roomNumber);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Room other = (Room) obj;
    return Objects.deepEquals(this.sides, other.sides)
        && Objects.equals(this.roomNumber, other.roomNumber);
  }
}
