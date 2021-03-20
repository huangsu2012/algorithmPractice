package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.BinaryHeapUtils;
import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/13.
 *
 * 基于二叉堆的排序,使用sink实现建堆操作
 */
public class BinaryHeapSort2 {


  public static <T extends Comparable<T>> void sort(T[] items) {
    int n = items.length;
    for (int pIndex = (n - 1) / 2; pIndex >= 0; pIndex--) {
      BinaryHeapUtils.sinkMax(items, pIndex, n, true);
    }

    while (n > 0) {
      SortUtils.exec(items, 0, --n);
      BinaryHeapUtils.sinkMax(items, 0, n, true);
    }
  }
}
