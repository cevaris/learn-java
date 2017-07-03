package com.cevaris.patterns.abstract_factory;

import java.util.Objects;

abstract class Shape {
  protected final int numOfSides;
  protected final String name;

  protected Shape(int numOfSides, String name) {
    this.numOfSides = numOfSides;
    this.name = name;
  }

  protected final String getName() {
    return name;
  }

  protected final int getNumberOfSides() {
    return numOfSides;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numOfSides, name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Shape other = (Shape) obj;
    return Objects.equals(this.numOfSides, other.numOfSides)
        && Objects.equals(this.name, other.name);
  }
}

class Rectangle extends Shape {
  Rectangle() {
    super(4, "rectangle");
  }
}

class Pentagon extends Shape {
  Pentagon() {
    super(5, "pentagon");
  }
}

abstract class ShapeFactory {
  abstract Shape createShape();
}

class RectangleFactory extends ShapeFactory {
  @Override
  Shape createShape() {
    return new Rectangle();
  }
}

class PentagonFactory extends ShapeFactory {
  @Override
  Shape createShape() {
    return new Pentagon();
  }
}

abstract class AbstractShapeFactory {

  private final ShapeFactory factory;

  AbstractShapeFactory(ShapeFactory factory) {
    this.factory = factory;
  }

  Shape create() {
    return factory.createShape();
  }
}


class RectangleShapeFactory extends AbstractShapeFactory {
  RectangleShapeFactory() {
    super(new RectangleFactory());
  }
}

class PentagonShapeFactory extends AbstractShapeFactory {
  PentagonShapeFactory() {
    super(new PentagonFactory());
  }
}

