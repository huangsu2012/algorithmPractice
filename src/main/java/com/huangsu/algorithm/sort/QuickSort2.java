package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/30.
 *
 * 二分快速排序,使用队列模拟递归
 */
public class QuickSort2 {

  public static <T extends Comparable<T>> T[] sort(T[] items) {
    Integer lo = 0, hi = items.length - 1;
    Queue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(lo);
    queue.enqueue(hi);
    while (!queue.isEmpty()) {
      lo = queue.dequeue();
      hi = queue.dequeue();
      int p = QuickSortUtil.partion(items, lo, hi);
      int leftHi = p - 1;
      if (lo < leftHi) {
        queue.enqueue(lo);
        queue.enqueue(leftHi);
      }
      int rightLo = p + 1;
      if (rightLo < hi) {
        queue.enqueue(rightLo);
        queue.enqueue(hi);
      }
    }
    return items;
  }

}
