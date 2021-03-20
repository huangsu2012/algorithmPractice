package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/2/15.
 *
 * 冒泡排序
 */
public class BubbleSort {

  public static <T extends Comparable<T>> void sort(T[] items) {
    for (int i = 0; i < items.length; i++) {
      boolean sorted = true;
      for (int j = i + 1; j < items.length; j++) {
        if (SortUtils.less(items[j], items[i])) {
          SortUtils.exec(items, i, j);
          sorted = false;
        }
      }
      if (sorted) {
        break;
      }
    }
  }
}
