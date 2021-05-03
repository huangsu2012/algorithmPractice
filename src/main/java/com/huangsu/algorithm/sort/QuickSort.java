package com.huangsu.algorithm.sort;

/**
 * Created by huangsu2012@gmail.com on 2018/3/31.
 *
 * 基于递归的二分快速排序
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
    int p = QuickSortUtil.partion(items, lo, hi);
    sort(items, lo, p - 1);
    sort(items, p + 1, hi);
  }

}
