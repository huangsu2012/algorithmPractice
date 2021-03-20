package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2018/4/27.
 */
@SuppressWarnings("unchecked")
class MergeSortUtil {

  public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
    if (!SortUtils.less(a[mid + 1], a[mid])) {//改进1，如果待合并的两部分已经有序，则无须合并操作
      return;
    }
    int i = lo, j = mid + 1;
    System.arraycopy(a, lo, aux, lo, hi - lo + 1);
    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (SortUtils.less(aux[i], aux[j])) {
        a[k] = aux[i++];
      } else {
        a[k] = aux[j++];
      }
    }
  }

  public static <T extends Comparable<T>> void mergeQuick(T[] a, T[] aux, int lo, int mid, int hi) {
    if (!SortUtils.less(a[mid + 1], a[mid])) {//改进1，如果待合并的两部分已经有序，则无须合并操作
      return;
    }
    int copyIndex = mid + 1;
    //将a的后半部分降序复制到aux中
    for (int i = hi; i > mid; i--) {
      aux[copyIndex++] = a[i];
    }
    System.arraycopy(a, lo, aux, lo, mid - lo + 1);
    int i = lo, j = hi;
    for (int k = lo; k <= hi; k++) {
      if (SortUtils.less(aux[i], aux[j])) {
        a[k] = aux[i++];
      } else {
        a[k] = aux[j--];
      }
    }

  }
}
