package com.cevaris.sorting;

import java.util.Collections;
import java.util.List;

interface Sorter<E> {
  List<E> sort(List<E> arr);
}

public class Sorts<E extends Comparable<E>> {

  private final InsertionSort<E> insertionSort = new InsertionSort<E>();

  public List<E> insertionSort(List<E> arr) {
    return insertionSort.sort(arr);
  }

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
