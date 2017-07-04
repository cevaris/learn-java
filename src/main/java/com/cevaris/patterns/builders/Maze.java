package com.cevaris.patterns.builders;


import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Maze {
  private final Map<Integer, Room> rooms = new HashMap<>();
  private final Map<SimpleEntry<Integer, Integer>, Door> doors = new HashMap<>();

  public void addRoom(Room room) {
    rooms.put(room.getRoomNumber(), room);
  }

  public void addDoor(Room from, Room to, Door door) {
    doors.put(
        new SimpleEntry<>(from.getRoomNumber(), to.getRoomNumber()), door
    );
  }

  public Room getRoom(int roomNumber) {
    return rooms.get(roomNumber);
  }

  public Room[] getRooms() {
    return rooms.values().toArray(new Room[rooms.size()]);
  }

  public Door[] getDoors() {
    return doors.values().toArray(new Door[doors.size()]);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rooms, doors);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Maze other = (Maze) obj;
    return Objects.equals(this.rooms, other.rooms)
        && Objects.equals(this.doors, other.doors);
  }
}
