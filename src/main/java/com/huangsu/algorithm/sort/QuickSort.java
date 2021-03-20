package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2018/3/31.
 */
public class QuickSort {

  public static <T extends Comparable<T>> T[] sort(T[] items) {
    sort(items, 0, items.length - 1);
    return items;
  }

  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> void sort(T[] items, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    int p = partion(items, lo, hi);
    sort(items, lo, p - 1);
    sort(items, p + 1, hi);
  }

  private static <T extends Comparable<T>> int partion(T[] items, int lo, int hi) {
    int i = lo, j = hi + 1;
    T val = items[lo];
    while (true) {
      while (SortUtils.less(items[++i], val)) {
        if (i == hi) {
          break;
        }
      }
      while (SortUtils.less(val, items[--j])) {
        ;
      }
      if (i >= j) {
        break;
      }

      SortUtils.exec(items, i, j);
    }
    SortUtils.exec(items, lo, j);
    return j;
  }

}
