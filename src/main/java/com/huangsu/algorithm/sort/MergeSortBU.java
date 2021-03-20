package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.ArrayUtil;

/**
 * Created by huangsu2012@gmail.com on 2021/3/7.
 *
 * 归并排序自底向上
 */
public class MergeSortBU {

  public static <T extends Comparable<T>> void sort(Class<T> tClass, T[] a, T[] aux) {
    if (aux == null) {
      aux = ArrayUtil.newInstance(tClass, a.length);
    }
    int sz = 1;
    int n = a.length;
    for (; sz < n; sz = sz + sz) {
      for (int lo = 0; lo < n; lo += sz + sz) {
        MergeSortUtil.mergeQuick(a, aux, lo, lo + sz - 1, Math.min(n - 1, lo + sz + sz - 1));
      }
    }
  }
}
