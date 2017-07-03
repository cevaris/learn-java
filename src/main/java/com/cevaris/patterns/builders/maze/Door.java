package com.cevaris.patterns.builders.maze;

import java.util.Objects;

public class Door implements MapSite {
  private final int fromRoomNumber;
  private final int toRoomNumber;
  private boolean isOpen;

  public Door(int fromRoomNumber, int toRoomNumber) {
    this.fromRoomNumber = fromRoomNumber;
    this.toRoomNumber = toRoomNumber;
  }

  @Override
  public void enter() {
  }

  public boolean isOpen() {
    return isOpen;
  }

  @Override
  public int hashCode() {
    return Objects.hash(fromRoomNumber, toRoomNumber, isOpen);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Door other = (Door) obj;
    return Objects.equals(this.fromRoomNumber, other.fromRoomNumber)
        && Objects.equals(this.toRoomNumber, other.toRoomNumber)
        && Objects.equals(this.isOpen, other.isOpen);
  }
}
