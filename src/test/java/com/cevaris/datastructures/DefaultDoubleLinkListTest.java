package com.cevaris.datastructures;

import java.util.List;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class DefaultDoubleLinkListTest {

  @Test
  public void testAddMany() {
    DoubleLinkList<Integer> actual = new DefaultDoubleLinkList<>();
    List<Integer> expected = TestUtils.until(0, 10);
    actual.addAll(expected);

    Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    Assert.assertEquals(expected.get(0), actual.getFirst());
    Assert.assertEquals(expected.get(expected.size() - 1), actual.getLast());
  }


  @Test
  public void testInsertAfterBefore() {
    DoubleLinkList<Integer> actual = new DefaultDoubleLinkList<>();
    List<Integer> expected = TestUtils.until(0, 5);
    actual.addAll(expected);
  }

}