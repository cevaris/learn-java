package com.cevaris.datastructures;

import org.junit.Assert;
import org.junit.Test;


public class CircularLinkListTest {

  @Test
  public void testIsEmpty() {
    CircularLinkList<String> ls = new CircularLinkList<>();
    Assert.assertTrue(ls.isEmpty());
    Assert.assertEquals(0, ls.size());
    Assert.assertArrayEquals(new Object[]{}, ls.toArray());
  }


  @Test
  public void testSingleItem() {
    CircularLinkList<String> ls = new CircularLinkList<>();
    Assert.assertTrue(ls.isEmpty());
    Assert.assertEquals(0, ls.size());

    ls.add("a");

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(1, ls.size());
    Assert.assertArrayEquals(new Object[]{"a"}, ls.toArray());
  }

  @Test
  public void testManyItems() {
    CircularLinkList<String> ls = new CircularLinkList<>();
    Assert.assertTrue(ls.isEmpty());
    Assert.assertEquals(0, ls.size());

    ls.add("a");
    ls.add("b");
    ls.add("c");
    ls.add("d");

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(4, ls.size());
    Assert.assertArrayEquals(new Object[]{"a", "b", "c", "d"}, ls.toArray());
  }

}