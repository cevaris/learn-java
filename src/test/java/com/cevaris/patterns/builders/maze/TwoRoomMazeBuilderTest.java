package com.cevaris.patterns.builders.maze;

import org.junit.Assert;
import org.junit.Test;

public class TwoRoomMazeBuilderTest {

  @Test
  public void testTwoRoomMazeBuilder() {
    MazeBuilder builder = new TwoRoomMazeBuilder();
    builder.buildMaze();
    builder.buildRoom(1);
    builder.buildRoom(2);
    builder.buildDoor(1, 2);
    Maze actual = builder.getMaze();

    Maze expected = new Maze();
    Room room1 = new Room(1);
    room1.setSide(Direction.NORTH, new Wall());
    room1.setSide(Direction.EAST, new Wall());
    room1.setSide(Direction.SOUTH, new Wall());
    room1.setSide(Direction.WEST, new Wall());
    Room room2 = new Room(2);
    room2.setSide(Direction.NORTH, new Wall());
    room2.setSide(Direction.EAST, new Wall());
    room2.setSide(Direction.SOUTH, new Wall());
    room2.setSide(Direction.WEST, new Wall());
    expected.addRoom(room1);
    expected.addRoom(room2);
    Door door = new Door(room1.getRoomNumber(), room2.getRoomNumber());
    room1.setSide(Direction.NORTH, door);
    room2.setSide(Direction.SOUTH, door);
    expected.addDoor(room1, room2, door);

    Assert.assertArrayEquals(expected.getDoors(), actual.getDoors());
    Assert.assertArrayEquals(expected.getRooms(), actual.getRooms());
    Assert.assertEquals(expected, actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFromRoomDoesNotExist() {
    MazeBuilder builder = new TwoRoomMazeBuilder();
    builder.buildMaze();
    builder.buildRoom(2);
    builder.buildDoor(1, 2);
    builder.getMaze();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testToRoomDoesNotExist() {
    MazeBuilder builder = new TwoRoomMazeBuilder();
    builder.buildMaze();
    builder.buildRoom(1);
    builder.buildDoor(1, 2);
    builder.getMaze();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooManyRooms() {
    MazeBuilder builder = new TwoRoomMazeBuilder();
    builder.buildMaze();
    builder.buildRoom(1);
    builder.buildRoom(2);
    builder.buildRoom(3);
    builder.getMaze();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateRoom() {
    MazeBuilder builder = new TwoRoomMazeBuilder();
    builder.buildMaze();
    builder.buildRoom(1);
    builder.buildRoom(1);
    builder.getMaze();
  }
}