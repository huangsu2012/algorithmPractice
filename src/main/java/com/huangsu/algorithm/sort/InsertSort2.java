package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/13.
 *
 * 通过右移数组元素改进算法
 */
public class InsertSort2 {

  public static <T extends Comparable<T>> void sort(T[] items) {
    for (int i = 1; i < items.length; i++) {
      T item = items[i];
      int j = i;
      for (; j > 0 && SortUtils.less(item, items[j - 1]);
          j--) {
        items[j] = items[j - 1];
      }
      items[j] = item;
    }
  }
}
