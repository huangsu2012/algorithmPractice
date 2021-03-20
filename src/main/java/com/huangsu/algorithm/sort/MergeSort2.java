package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.ArrayUtil;

/**
 * Created by huangsu2012@gmail.com on 2018/4/27.
 *
 * 归并排序一般实现
 */
public class MergeSort2 {

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void sort(Class<T> tClass, T[] a,
      T[] aux, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    if (aux == null) {
      aux = ArrayUtil.newInstance(tClass, a.length);
    }
    int mid = lo + (hi - lo) / 2;
    sort(tClass, a, aux, lo, mid);
    sort(tClass, a, aux, mid + 1, hi);
    MergeSortUtil.merge(a, aux, lo, mid, hi);
  }
}
