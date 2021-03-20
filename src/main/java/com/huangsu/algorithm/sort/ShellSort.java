package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/7.
 *
 * 希尔排序
 */
public class ShellSort {

  public static <T extends Comparable<T>> void sort(T[] items) {
    int h = 1;
    int n = items.length;
    while (h < n / 3) {
      h = 3 * h + 1;
    }
    while (h > 0) {
      for (int i = h; i < n; i++) {
        for (int j = i; j >= h && SortUtils.less(items[j], items[j - h]);
            j -= h) {
          SortUtils.exec(items, j - h, j);
        }
      }
      h = h / 3;
    }
  }
}
