package com.cevaris.patterns.prototype;

import java.util.Objects;

abstract class Shape {
}

class Quadrilateral extends Shape {
  int top;
  int bottom;
  int left;
  int right;

  @Override
  public int hashCode() {
    return Objects.hash(top, bottom, left, right);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Quadrilateral other = (Quadrilateral) obj;
    return Objects.equals(this.top, other.top)
        && Objects.equals(this.bottom, other.bottom)
        && Objects.equals(this.left, other.left)
        && Objects.equals(this.right, other.right);
  }
}

class Square extends Quadrilateral {
  Square(int len) {
    top = len;
    bottom = len;
    left = len;
    right = len;
  }

  static Square copy(Square x) {
    return new Square(x.top);
  }

  void initialize(int len) {
    top = len;
    bottom = len;
    left = len;
    right = len;
  }

}

class Trapezoid extends Quadrilateral {
  Trapezoid(int top, int bottom, int left, int right) {
    super.top = top;
    super.bottom = bottom;
    super.left = left;
    super.right = right;
  }

  static Trapezoid copy(Trapezoid x) {
    return new Trapezoid(x.top, x.bottom, x.left, x.right);
  }
}

class Circle extends Shape {
  private int radius;

  Circle() {
  }

  Circle(int radius) {
    this.radius = radius;
  }

  static Circle copy(Circle x) {
    return new Circle(x.radius);
  }

  void initialize(int radius) {
    this.radius = radius;
  }

  @Override
  public int hashCode() {
    return Objects.hash(radius);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final Circle other = (Circle) obj;
    return Objects.equals(this.radius, other.radius);
  }
}

class Ellipse extends Circle {
  private int height;
  private int width;

  Ellipse(int height, int width) {
    this.height = height;
    this.width = width;
  }

  static Ellipse copy(Ellipse x) {
    return new Ellipse(x.height, x.width);
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + Objects.hash(height, width);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    if (!super.equals(obj)) {
      return false;
    }
    final Ellipse other = (Ellipse) obj;
    return Objects.equals(this.height, other.height)
        && Objects.equals(this.width, other.width);
  }
}

abstract class ShapePrototypeFactory {
  abstract Circle createCircle();

  abstract Quadrilateral createQuadrilateral();
}

abstract class MutableShapePrototypeFactory extends ShapePrototypeFactory {
  abstract Circle createCircle(int radius);

  abstract Quadrilateral createQuadrilateral(int len);
}

class SimpleShapePrototypeFactory extends ShapePrototypeFactory {
  private final Circle circle;
  private final Square square;

  SimpleShapePrototypeFactory(Circle circle, Square square) {
    this.circle = circle;
    this.square = square;
  }

  @Override
  Circle createCircle() {
    return Circle.copy(circle);
  }

  @Override
  Quadrilateral createQuadrilateral() {
    return Square.copy(square);
  }
}


class ExoticShapePrototypeFactory extends ShapePrototypeFactory {
  private final Ellipse ellipse;
  private final Trapezoid trapezoid;

  ExoticShapePrototypeFactory(Ellipse ellipse, Trapezoid trapezoid) {
    this.ellipse = ellipse;
    this.trapezoid = trapezoid;
  }

  @Override
  Circle createCircle() {
    return Ellipse.copy(ellipse);
  }

  @Override
  Quadrilateral createQuadrilateral() {
    return Trapezoid.copy(trapezoid);
  }
}

class SimpleMutableShapePrototypeFactory extends MutableShapePrototypeFactory {
  private final Circle circle;
  private final Square square;

  SimpleMutableShapePrototypeFactory(Circle circle, Square square) {
    this.circle = circle;
    this.square = square;
  }

  @Override
  Circle createCircle() {
    return Circle.copy(circle);
  }

  @Override
  Quadrilateral createQuadrilateral() {
    return Square.copy(square);
  }

  @Override
  Circle createCircle(int radius) {
    Circle x = createCircle();
    x.initialize(radius);
    return x;
  }

  @Override
  Quadrilateral createQuadrilateral(int len) {
    Square x = (Square) createQuadrilateral();
    x.initialize(len);
    return x;

  }
}