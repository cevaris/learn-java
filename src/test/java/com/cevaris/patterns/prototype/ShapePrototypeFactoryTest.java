package com.cevaris.patterns.prototype;

import org.junit.Assert;
import org.junit.Test;


public class ShapePrototypeFactoryTest {

  @Test
  public void testSimpleShapePrototypeFactory() {
    ShapePrototypeFactory factory =
        new SimpleShapePrototypeFactory(
            new Ellipse(2, 2),
            new Square(2)
        );

    Assert.assertEquals(new Ellipse(2, 2), factory.createCircle());
    Assert.assertEquals((Quadrilateral) new Square(2), factory.createQuadrilateral());
  }

  @Test
  public void testExoticShapePrototypeFactory() {
    ShapePrototypeFactory factory =
        new SimpleShapePrototypeFactory(
            new Ellipse(2, 2),
            new Trapezoid(1, 3, 2, 2)
        );

    Assert.assertEquals(new Ellipse(2, 2), factory.createCircle());
    Assert.assertEquals(new Trapezoid(1, 3, 2, 2), factory.createQuadrilateral());
  }

  @Test
  public void testMutableShapePrototypeFactory() {
    MutableShapePrototypeFactory factory =
        new SimpleMutableShapePrototypeFactory(
            new Ellipse(2, 2),
            new Square(2)
        );

    Assert.assertEquals(new Ellipse(4, 4), factory.createCircle(4, 4));
    Assert.assertEquals(new Square(4), factory.createQuadrilateral(4, 4, 4, 4));
  }

}