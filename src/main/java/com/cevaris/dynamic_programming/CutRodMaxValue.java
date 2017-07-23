package com.cevaris.dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class CutRodMaxValue {
  static int calculate(Map<Integer, Integer> lenPrice, int L) {
    if (lenPrice == null)
      throw new IllegalArgumentException("lenPrice cannot be null");
    if (lenPrice.size() < 2)
      throw new IllegalArgumentException("lenPrice contain at least 2 prices");
    if(lenPrice.size() != L)
      throw new IllegalArgumentException("lenPrice must be of size L");

    List<Integer> m = new ArrayList<>(L + 1);
    m.add(1);
    m.add(1);

    for (int i = 2; i <= L; i++)
      for (int j = i - 1; j > 0; j--)
        m.add(Math.max(lenPrice.get(i), lenPrice.get(i - j) + lenPrice.get(j)));

    return Collections.max(m);
  }

}
