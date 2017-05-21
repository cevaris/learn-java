package com.cevaris.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
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
    List<Integer> expected = Arrays.asList(0, 1, 2);
    singleElement.addAll(expected);
    Assert.assertArrayEquals(expected.toArray(), singleElement.toArray());
  }

  @Test
  public void testAddAllWhenEmpty() {
    SingleLinkList<Integer> singleElement = new SingleLinkList<>();
    singleElement.addAll(new ArrayList<>());
    Assert.assertArrayEquals(new Object[0], singleElement.toArray());
  }

}