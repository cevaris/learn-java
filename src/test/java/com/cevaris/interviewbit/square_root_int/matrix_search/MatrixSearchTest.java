package com.cevaris.interviewbit.square_root_int.matrix_search;

import java.util.ArrayList;

import com.cevaris.test.utils.TestUtils;

import org.junit.Assert;
import org.junit.Test;

public class MatrixSearchTest {

  private final int R = 2;
  private final int C = 20;
  private final ArrayList<ArrayList<Integer>> test = TestUtils.buildMatrix(C, R);
  private final MatrixSearch actual = new MatrixSearch();

  @Test
  public void testMatrixSearchFound() {
    for (int i = 0; i < R * C; i++) {
      Assert.assertEquals(String.format("failed on searchMatrix(%d)", i), 1, actual.searchMatrix(test, i));
    }
  }

  @Test
  public void testSingleElement() {
    ArrayList<ArrayList<Integer>> single = new ArrayList<>();
    single.add(new ArrayList<Integer>() {{
      add(1);
    }});

    Assert.assertEquals(0, actual.searchMatrix(single, -1));
    Assert.assertEquals(1, actual.searchMatrix(single, 1));
  }

  @Test
  public void testEmpty() {
    ArrayList<ArrayList<Integer>> test = new ArrayList<>();
    Assert.assertEquals(0, actual.searchMatrix(test, -1));
  }

  @Test
  public void testMatrixSearchNotFound() {
    Assert.assertEquals(0, actual.searchMatrix(test, -1));
    Assert.assertEquals(0, actual.searchMatrix(test, R * C));
    Assert.assertEquals(0, actual.searchMatrix(test, Integer.MAX_VALUE));
    Assert.assertEquals(0, actual.searchMatrix(test, Integer.MIN_VALUE));
    Assert.assertEquals(0, actual.searchMatrix(null, 0));
  }
}