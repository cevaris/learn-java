package com.cevaris.patterns.builders.maze;


abstract class MazeBuilder {
  abstract void buildMaze();

  abstract void buildRoom(int roomNumber);

  abstract void buildDoor(int roomFromNumber, int roomToNumber);

  abstract Maze getMaze();
}
