package com.cevaris.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface Sorts<E> {
  List<E> sort(List<E> ls);
}

class InsertionSort<E extends Comparable<E>> implements Sorts<E> {
  @Override
  public List<E> sort(List<E> ls) {
    for (int i = 1; i < ls.size(); i++) {
      for (int k = i; k > 0 && ls.get(k).compareTo(ls.get(k - 1)) < 0; k--) {
        Collections.swap(ls, k, k - 1);
      }
    }
    return ls;
  }
}

class MergeSort<E extends Comparable<E>> implements Sorts<E> {
  @Override
  public List<E> sort(List<E> ls) {
    if (ls.size() <= 1) return ls;

    int m = ls.size() / 2;
    List<E> left = sort(ls.subList(0, m));
    List<E> right = sort(ls.subList(m, ls.size()));

    return merge(left, right);
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

class QuickSort<E extends Comparable<E>> implements Sorts<E> {
  @Override
  public List<E> sort(List<E> ls) {
    quickSort(ls, 0, ls.size() - 1);
    return ls;
  }

  private void quickSort(List<E> ls, int low, int high) {
    if (ls == null) return;
    if (ls.isEmpty()) return;
    if (low == high) return;

    int i = low;
    int j = high;
    E pivot = ls.get((low + high) / 2);

    while (i <= j) {
      while (ls.get(i).compareTo(pivot) < 0) {
        i++;
      }
      while (ls.get(j).compareTo(pivot) > 0) {
        j--;
      }

      if (i <= j) {
        Collections.swap(ls, i, j);
        i++;
        j--;
      }
    }

    if (low < j) {
      quickSort(ls, low, j);
    }
    if (high > i) {
      quickSort(ls, i, high);
    }
  }
}

class QuickSort3<E extends Comparable<E>> implements Sorts<E> {
  @Override
  public List<E> sort(List<E> ls) {
    return ls;
  }
}