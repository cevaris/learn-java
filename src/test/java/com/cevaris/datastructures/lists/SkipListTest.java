package com.cevaris.datastructures.lists;

import java.util.List;

import com.cevaris.datastructures.lists.SkipList;
import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Ignore;

public class SkipListTest {

  @Ignore
  public void testAddMany() {
    SkipList<Integer> actual = new SkipList<>();
    List<Integer> expected = TestUtils.newList(1, 2, 3);

    Assert.assertTrue(actual.isEmpty());

    for (int i = 0; i < expected.size(); i++) {
      actual.add(expected.get(i));
      Assert.assertArrayEquals(expected.subList(0, i + 1).toArray(), actual.toArray());
    }
  }

}