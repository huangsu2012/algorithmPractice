package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/30.
 *
 * 三项切分的快速排序，主要对重复元素进行优化
 */
public class QuickSort3 {

  private final static int INSERT_SORT_SIZE = 10;

  public static <T extends Comparable<T>> T[] sort(T[] items) {
    Integer lo = 0, hi = items.length - 1;
    Queue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(lo);
    queue.enqueue(hi);
    while (!queue.isEmpty()) {
      lo = queue.dequeue();
      hi = queue.dequeue();
      if (hi - lo <= INSERT_SORT_SIZE) {//当元素比较少时使用插入排序
        InsertSort2.sort(items, lo, hi);
        continue;
      }
      //核心为利用三个索引切分数组，使得lo～lt-1之间的元素小于选择的元素，gt+1～hi之间的元素大于选择元素
      int i = lo + 1;//与选择元素进行比较的当前元素索引
      int lt = lo, gt = hi;//lt为当i指向的元素小于选择元素时需要交换的索引,gt为当i指向的元素大于选择元素时需要交换的索引
      T val = items[lo];
      while (i <= gt) {
        int cmp = SortUtils.compareTo(items[i], val);
        if (cmp == 0) {
          ++i;
        } else if (cmp > 0) {
          SortUtils.exec(items, i, gt);
          --gt;
        } else {//此处的交换其实是相当于把与选择元素相等的元素往右移动了
          SortUtils.exec(items, i, lt);
          ++i;
          ++lt;
        }
      }

      int leftHi = lt - 1;
      if (lo < leftHi) {
        queue.enqueue(lo);
        queue.enqueue(leftHi);
      }
      int rightLo = gt + 1;
      if (rightLo < hi) {
        queue.enqueue(rightLo);
        queue.enqueue(hi);
      }
    }
    return items;
  }
}
