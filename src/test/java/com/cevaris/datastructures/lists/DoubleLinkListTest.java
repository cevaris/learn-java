package com.cevaris.datastructures.lists;

import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class DoubleLinkListTest {

  @Test
  public void testAddMany() {
    OpenLinkList<Integer> actual = new DoubleLinkList<>();
    List<Integer> expected = TestUtils.until(0, 10);
    actual.addAll(expected);

    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    Assert.assertEquals(expected.get(0), actual.getFirst());
    Assert.assertEquals(expected.get(0), actual.getNode(0).getValue());

    Assert.assertEquals(expected.get(expected.size() / 2), actual.getNode(actual.size() / 2).getValue());

    Assert.assertEquals(expected.get(expected.size() - 1), actual.getLast());
    Assert.assertEquals(expected.get(expected.size() - 1), actual.getNode(actual.size() - 1).getValue());
  }

  @Test
  public void testRemove() {
    OpenLinkList<Long> actual = new DoubleLinkList<>();
    Assert.assertFalse(actual.remove(11L)); // remove on empty list

    List<Long> expected = TestUtils.until(0L, 5L);
    actual.addAll(expected);

    // remove middle
    actual.remove(2L);
    expected.remove(2L);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    // remove first
    actual.remove(0L);
    expected.remove(0L);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    // remove last
    actual.remove(4L);
    expected.remove(4L);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    // remove no such object
    Assert.assertFalse(actual.remove(Long.MAX_VALUE));
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  public void testAddBeforeAndAfter() {
    OpenLinkList<Integer> actual = new DoubleLinkList<>();
    List<Integer> expected = TestUtils.until(0, 3);
    actual.addAll(expected);

    ///////////
    // front
    expected.add(0, 10);
    Node<Integer> head = actual.first();
    actual.addBefore(head, 10);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    expected.add(1, 11);
    head = actual.first();
    actual.addAfter(head, 11);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    ///////////
    // middle
    expected.add(3, 30);
    actual.addBefore(actual.getNode(3), 30);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    expected.add(5, 40);
    actual.addAfter(actual.getNode(4), 40);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    ///////////
    // last
    expected.add(expected.size() - 1, 100);
    Node<Integer> last = actual.last();
    actual.addBefore(last, 100);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    expected.add(101);
    last = actual.last();
    actual.addAfter(last, 101);
    Assert.assertArrayEquals(expected.toArray(), actual.toArray());
  }
}