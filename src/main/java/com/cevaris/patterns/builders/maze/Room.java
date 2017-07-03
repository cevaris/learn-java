package com.cevaris.patterns.builders;

public class Room implements MapSite {

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

  public MapSite getSide(Direction direction) {
    return sides[direction.ordinal()];
  }

}
