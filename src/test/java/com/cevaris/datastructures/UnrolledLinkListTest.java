package com.cevaris.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class UnrolledLinkListTest {

  @Test
  public void testIsEmpty() {
    UnrolledLinkList<String> ls = new UnrolledLinkList<>();
    Assert.assertTrue(ls.isEmpty());
//    Assert.assertEquals(0, ls.size());
//    Assert.assertArrayEquals(new Object[]{}, ls.toArray());
  }

}