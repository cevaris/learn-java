package com.cevaris.patterns.prototype;

import java.util.Objects;

abstract class Shape {
}

abstract class Quadrilateral extends Shape implements Cloneable {
  protected int top;
  protected int bottom;
  protected int left;
  protected int right;

  public Quadrilateral(int top, int bottom, int left, int right) {
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

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

  void init(int top, int bottom, int left, int right) {
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
  }

}

class Square extends Quadrilateral implements Cloneable {
  Square(int len) {
    super(len, len, len, len);
  }
}

class Trapezoid extends Quadrilateral {
  Trapezoid(int top, int bottom, int left, int right) {
    super(top, bottom, left, right);
  }
}

abstract class Circle extends Shape implements Cloneable {
  int vRadus;
  int hRadius;

  public Circle(int vRadus, int hRadius) {
    this.vRadus = vRadus;
    this.hRadius = hRadius;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public int hashCode() {
    return Objects.hash(vRadus, hRadius);
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
    return Objects.equals(this.vRadus, other.vRadus)
        && Objects.equals(this.hRadius, other.hRadius);
  }

  void init(int vRadus, int hRadius) {
    this.vRadus = vRadus;
    this.hRadius = hRadius;
  }
}

class Ellipse extends Circle {
  Ellipse(int verticalRadius, int horizontalRadius) {
    super(verticalRadius, horizontalRadius);
  }
}

interface ShapePrototypeFactory {
  Circle createCircle();

  Quadrilateral createQuadrilateral();
}

interface MutableShapePrototypeFactory extends ShapePrototypeFactory {
  Circle createCircle(int vRadius, int hRadius);

  Quadrilateral createQuadrilateral(int top, int bottom, int left, int right);
}

class SimpleShapePrototypeFactory implements ShapePrototypeFactory {
  private final Circle circle;
  private final Quadrilateral quad;

  SimpleShapePrototypeFactory(Circle circle, Quadrilateral quad) {
    this.circle = circle;
    this.quad = quad;
  }

  @Override
  public Circle createCircle() {
    try {
      return (Circle) circle.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Quadrilateral createQuadrilateral() {
    try {
      return (Quadrilateral) quad.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }
}

class SimpleMutableShapePrototypeFactory extends SimpleShapePrototypeFactory implements MutableShapePrototypeFactory {

  SimpleMutableShapePrototypeFactory(Circle circle, Quadrilateral quad) {
    super(circle, quad);
  }

  @Override
  public Circle createCircle(int vRadius, int hRadius) {
    Circle x = createCircle();
    x.init(vRadius, hRadius);
    return x;
  }

  @Override
  public Quadrilateral createQuadrilateral(int top, int bottom, int left, int right) {
    Quadrilateral x = createQuadrilateral();
    x.init(top, bottom, left, right);
    return x;
  }
}