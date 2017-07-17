package com.cevaris.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface Sorter<E> {
  List<E> sort(List<E> arr);
}

class InsertionSort<E extends Comparable<E>> implements Sorter<E> {
  @Override
  public List<E> sort(List<E> arr) {
    for (int i = 1; i < arr.size(); i++) {
      for (int k = i; k > 0 && arr.get(k).compareTo(arr.get(k - 1)) < 0; k--) {
        Collections.swap(arr, k, k - 1);
      }
    }
    return arr;
  }
}

class MergeSort<E extends Comparable<E>> implements Sorter<E> {
  @Override
  public List<E> sort(List<E> arr) {
    if (arr.size() <= 1) return arr;

    int m = arr.size() / 2;
    List<E> ls = sort(arr.subList(0, m));
    List<E> rs = sort(arr.subList(m, arr.size()));

    return merge(ls, rs);
  }

  private List<E> merge(List<E> ls, List<E> rs) {
    int msLen = ls.size() + rs.size();
    List<E> ms = new ArrayList<>(msLen);

    for (int li = 0, ri = 0, mi = 0; mi < msLen; mi++) {
      if (rs.size() <= ri && ls.size() > li) {
        ms.add(ls.get(li++));
        continue;
      }

      if (ls.size() <= li && rs.size() > ri) {
        ms.add(rs.get(ri++));
        continue;
      }

      if (ls.get(li).compareTo(rs.get(ri)) < 0) {
        ms.add(ls.get(li++));
      } else {
        ms.add(rs.get(ri++));
      }
    }

    return ms;
  }
}
