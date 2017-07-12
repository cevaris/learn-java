package com.cevaris.datastructures.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cevaris.datastructures.lists.SingleLinkList;

import org.junit.Assert;
import org.junit.Test;

public class SingleLinkListTest {

  @Test
  public void testToArrayWhenEmpty() {
    SingleLinkList<Integer> ls = new SingleLinkList<>();
    Assert.assertTrue(ls.isEmpty());
    Assert.assertArrayEquals(new Object[0], ls.toArray());
  }

  @Test
  public void testAddSingle() {
    SingleLinkList<Integer> ls = new SingleLinkList<>();
    ls.add(1);
    Assert.assertFalse(ls.isEmpty());
    Assert.assertArrayEquals(new Object[]{1}, ls.toArray());
  }

  @Test
  public void testAddSome() {
    SingleLinkList<Integer> ls = new SingleLinkList<>();
    List<Integer> expected = Arrays.asList(0, 1, 2);
    ls.addAll(expected);
    Assert.assertArrayEquals(expected.toArray(), ls.toArray());
  }

  @Test
  public void testAddAllWhenEmpty() {
    SingleLinkList<Integer> ls = new SingleLinkList<>();
    ls.addAll(new ArrayList<>());
    Assert.assertArrayEquals(new Object[0], ls.toArray());
  }

  @Test
  public void testRemoveObjEmptyList() {
    SingleLinkList<Integer> ls = new SingleLinkList<>();
    Assert.assertEquals(false, ls.remove(new Integer(1)));
    Assert.assertArrayEquals(new Object[0], ls.toArray());
  }

  @Test
  public void testRemoveObjSingleItemList() {
    SingleLinkList<String> ls = new SingleLinkList<>();
    ls.add("alpha");
    Assert.assertEquals(false, ls.remove("beta"));
    Assert.assertEquals(true, ls.remove("alpha"));
  }

  @Test
  public void testRemoveObj() {
    SingleLinkList<String> ls = new SingleLinkList<>();
    List<String> expected = Arrays.asList("alpha", "beta", "gamma", "delta");
    ls.addAll(expected);

    // iterate all elemnts with not found
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