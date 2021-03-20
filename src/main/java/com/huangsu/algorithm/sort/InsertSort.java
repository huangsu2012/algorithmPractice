package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2018/4/2.
 */
public class InsertSort {

  public static <T extends Comparable<T>> void sort(T[] items) {
    for (int i = 1; i < items.length; i++) {
      for (int j = i; j > 0 && SortUtils.less(items[j], items[j - 1]);
          j--) {
        SortUtils.exec(items, j - 1, j);
      }
    }
  }
}
