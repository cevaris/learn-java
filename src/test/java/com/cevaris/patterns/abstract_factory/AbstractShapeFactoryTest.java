package com.cevaris.patterns.abstract_factory;


import org.junit.Assert;
import org.junit.Test;

public class AbstractShapeFactoryTest {

  @Test
  public void testRectangleShapeFactory() {
    AbstractShapeFactory factory = new RectangleShapeFactory();
    Shape expected = new Rectangle();
    Assert.assertEquals(expected, factory.create());
  }

  @Test
  public void testPentagonShapeFactory() {
    AbstractShapeFactory factory = new PentagonShapeFactory();
    Shape expected = new Pentagon();
    Assert.assertEquals(expected, factory.create());
  }


}