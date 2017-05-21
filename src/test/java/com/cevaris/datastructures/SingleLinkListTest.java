package com.cevaris.datastructures;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SingleLinkListTest {

  @Test
  public void testToArrayWhenEmpty() {
    SingleLinkList<Integer> empty = new SingleLinkList<>();
    Assert.assertArrayEquals(new Object[0], empty.toArray());
  }

  @Test
  public void testAddSingle() {
    SingleLinkList<Integer> singleElement = new SingleLinkList<>();
    singleElement.add(1);
    Assert.assertArrayEquals(new Object[]{1}, singleElement.toArray());
  }

  @Test
  public void testAddSome() {
    SingleLinkList<Integer> singleElement = new SingleLinkList<>();
    singleElement.add(1);
    singleElement.add(2);
    singleElement.add(3);
    Object[] expected = {1, 2, 3};
    Assert.assertArrayEquals(expected, singleElement.toArray());
  }

}