package com.cevaris.datastructures;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UnrolledLinkListTest {

  @Test
  public void testIsEmpty() {
    UnrolledLinkList<String> ls = new UnrolledLinkList<>(3);
    Assert.assertTrue(ls.isEmpty());
    Assert.assertEquals(0, ls.size());
    Assert.assertArrayEquals(new Object[]{}, ls.toArray());
  }

  @Test
  public void testAddOne() {
    UnrolledLinkList<String> ls = new UnrolledLinkList<>(3);
    ls.add("one");

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(1, ls.size());
    Assert.assertArrayEquals(new Object[]{"one"}, ls.toArray());
  }

  @Test
  public void testAddMany() {
    UnrolledLinkList<String> ls = new UnrolledLinkList<>(3);
    List<String> expected = Arrays.asList("one", "two", "three", "four");
    Assert.assertTrue(ls.addAll(expected));

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(expected.size(), ls.size());
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

}