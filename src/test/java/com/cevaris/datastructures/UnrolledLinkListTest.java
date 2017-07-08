package com.cevaris.datastructures;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static com.cevaris.test.utils.TestUtils.newList;

public class UnrolledLinkListTest {

  @Test
  public void testIsEmpty() {
    List<String> ls = new UnrolledLinkList<>(9);
    Assert.assertTrue(ls.isEmpty());
    Assert.assertEquals(0, ls.size());
    Assert.assertArrayEquals(new Object[]{}, ls.toArray());
  }

  @Test
  public void testAddOne() {
    List<String> ls = new UnrolledLinkList<>(9);
    ls.add("one");

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(1, ls.size());
    Assert.assertArrayEquals(new Object[]{"one"}, ls.toArray());
  }

  @Test
  public void testAddMany() {
    List<String> ls = new UnrolledLinkList<>(9);
    List<String> expected = newList("one", "two", "three", "four");
    Assert.assertTrue(ls.addAll(expected));

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(expected.size(), ls.size());
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

  @Test
  public void testAddAtHead0Index() {
    List<Integer> ls = new UnrolledLinkList<>(9);
    List<Integer> expected = newList(1, 2, 3, 4, 5, 6, 7, 8);
    Assert.assertTrue(ls.addAll(expected));
    ls.add(0, 0);
    expected.add(0, 0);

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(expected.size(), ls.size());
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

  @Test
  public void testAddAtMid0Index() {
    List<Integer> ls = new UnrolledLinkList<>(9);
    List<Integer> expected = newList(0, 1, 2, 4, 5, 6, 7, 8);
    Assert.assertTrue(ls.addAll(expected));
    ls.add(3, 3);
    expected.add(3, 3);

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(expected.size(), ls.size());
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

  @Test
  public void testAddAtMid1Index() {
    List<Integer> ls = new UnrolledLinkList<>(9);
    List<Integer> expected = newList(0, 1, 2, 3, 5, 6, 7, 8);
    Assert.assertTrue(ls.addAll(expected));
    ls.add(4, 4);
    expected.add(4, 4);

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(expected.size(), ls.size());
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

  @Test
  public void testAddAtMid2Index() {
    List<Integer> ls = new UnrolledLinkList<>(9);
    List<Integer> expected = newList(0, 1, 2, 3, 4, 6, 7, 8);
    Assert.assertTrue(ls.addAll(expected));
    ls.add(5, 5);
    expected.add(5, 5);

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(expected.size(), ls.size());
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

  @Test
  public void testAddATailIndex() {
    List<Integer> ls = new UnrolledLinkList<>(9);
    List<Integer> expected = newList(0, 1, 2, 3, 4, 5, 6, 7);
    Assert.assertTrue(ls.addAll(expected));
    ls.add(8, 8);
    expected.add(8, 8);

    Assert.assertFalse(ls.isEmpty());
    Assert.assertEquals(expected.size(), ls.size());
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

  @Test
  public void testGetIndex() {
    List<Integer> ls = new UnrolledLinkList<>(9);
    List<Integer> expected = newList(0, 1, 2, 3, 4, 5, 6, 7, 8);
    Assert.assertTrue(ls.addAll(expected));

    for (Integer i : expected) {
      Assert.assertEquals(expected.get(i), i);
    }
  }

}