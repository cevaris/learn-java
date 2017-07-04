package com.cevaris.patterns.builders;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

class TwoRoomMazeBuilder extends MazeBuilder {

  private Maze maze;
  private final Random rand = new Random(1000L);

  private final List<Direction[]> validConfigurations = new ArrayList<Direction[]>() {{
    add(new Direction[]{Direction.NORTH, Direction.SOUTH});
    add(new Direction[]{Direction.SOUTH, Direction.NORTH});
    add(new Direction[]{Direction.EAST, Direction.WEST});
    add(new Direction[]{Direction.WEST, Direction.EAST});
  }};

  @Override
  void buildMaze() {
    maze = new Maze();
  }

  @Override
  void buildRoom(int roomNumber) {
    int numOfRooms = maze.getRooms().length;
    if (numOfRooms > 1) {
      throw new IllegalArgumentException("too many rooms: " + numOfRooms);
    }

    if (maze.getRoom(roomNumber) != null) {
      throw new IllegalArgumentException("room already exists in maze: " + numOfRooms);
    }

    Room room = new Room(roomNumber);
    room.setSide(Direction.NORTH, new Wall());
    room.setSide(Direction.EAST, new Wall());
    room.setSide(Direction.SOUTH, new Wall());
    room.setSide(Direction.WEST, new Wall());
    maze.addRoom(room);
  }

  @Override
  void buildDoor(int roomFromNumber, int roomToNumber) {
    Room fromRoom = maze.getRoom(roomFromNumber);
    if (fromRoom == null) {
      throw roomDoesNotExist(roomFromNumber);
    }


    Room toRoom = maze.getRoom(roomToNumber);
    if (toRoom == null) {
      throw roomDoesNotExist(roomToNumber);
    }

    Door door = new Door(roomFromNumber, roomToNumber);
    assignCommonWall(fromRoom, toRoom, door);
    maze.addDoor(fromRoom, toRoom, door);
  }

  @Override
  Maze getMaze() {
    return maze;
  }

  private void assignCommonWall(Room fromRoom, Room toRoom, MapSite mapSite) {
    Set<Direction> fromRoomOpenSides = fromRoom.openSides();
    if (fromRoomOpenSides.isEmpty()) {
      throw roomHasNoOpenWalls(fromRoom.getRoomNumber());
    }

    Set<Direction> toRoomOpenSides = toRoom.openSides();
    if (toRoomOpenSides.isEmpty()) {
      throw roomHasNoOpenWalls(toRoom.getRoomNumber());
    }

    for (Direction[] valid : validConfigurations) {
      if (fromRoomOpenSides.contains(valid[0]) && toRoomOpenSides.contains(valid[1])) {
        fromRoom.setSide(valid[0], mapSite);
        toRoom.setSide(valid[1], mapSite);
        return;
      }
    }

    throw roomsHasNoOpenWallsInCommon(fromRoom.getRoomNumber(), toRoom.getRoomNumber());
  }

  private RuntimeException roomsHasNoOpenWallsInCommon(int fromNumber, int toNumber) {
    return new IllegalArgumentException("room has no open walls in common: " + fromNumber + " " + toNumber);
  }

  private RuntimeException roomHasNoOpenWalls(int roomNumber) {
    return new IllegalArgumentException("room has no open walls: " + roomNumber);
  }

  private RuntimeException roomDoesNotExist(int roomNumber) {
    return new IllegalArgumentException("room does not exist in maze: " + roomNumber);
  }
}
