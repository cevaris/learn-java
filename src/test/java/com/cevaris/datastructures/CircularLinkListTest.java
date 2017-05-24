package com.cevaris.datastructures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
  public void tesAddManyItems() {
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

  @Test
  public void testIterator() {
    CircularLinkList<String> ls = new CircularLinkList<>();
    List<String> expected = Arrays.asList("alpha", "beta", "gamma", "delta");
    ls.addAll(expected);
    Iterator<String> iter = ls.iterator();
    for (String s : expected) {
      Assert.assertEquals(s, iter.next());
    }
  }

  @Test
  public void testRemoveObj() {
    CircularLinkList<String> ls = new CircularLinkList<>();
    List<String> expected = Arrays.asList("alpha", "beta", "gamma", "delta");
    ls.addAll(expected);

    // iterate all elements with not found
    Assert.assertEquals(false, ls.remove("charlie"));

    //delete head
    Assert.assertEquals(true, ls.remove("alpha"));
    Assert.assertArrayEquals(new Object[]{"beta", "gamma", "delta"}, ls.toArray());

    //delete mid
    Assert.assertEquals(true, ls.remove("gamma"));
    Assert.assertArrayEquals(new Object[]{"beta", "delta"}, ls.toArray());

    //delete last
    Assert.assertEquals(true, ls.remove("delta"));
    Assert.assertArrayEquals(new Object[]{"beta"}, ls.toArray());
  }
}