package com.huangsu.algorithm.string;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/5/8.
 */
public class QuickStringSort {

  public static void sortRecursion(String[] a) {
    sortRecursion(a, 0, 0, a.length - 1);
  }

  @SuppressWarnings("Duplicates")
  public static void sort(String[] a) {
    int lo = 0, hi = a.length - 1;
    Queue<int[]> queue = new LinkedQueue<>();
    queue.enqueue(new int[]{lo, hi, 0});
    int chIndex;
    while (!queue.isEmpty()) {
      int[] item = queue.dequeue();
      lo = item[0];
      hi = item[1];
      chIndex = item[2];
      if (lo >= hi) {
        continue;
      }

      int ch = charAt(a[lo], chIndex);
      //核心为利用三个索引切分数组，使得lo～lt-1之间的元素小于选择的元素，gt+1～hi之间的元素大于选择元素
      int i = lo + 1;//与选择元素进行比较的当前元素索引
      int lt = lo, gt = hi;//lt为当i指向的元素小于选择元素时需要交换的索引,gt为当i指向的元素大于选择元素时需要交换的索引
      while (i <= gt) {
        int temp = charAt(a[i], chIndex);
        if (temp == ch) {
          ++i;
        } else if (temp < ch) {
          SortUtils.exec(a, i, lt);
          ++i;
          ++lt;
        } else {
          SortUtils.exec(a, i, gt);
          --gt;
        }
      }
      item[1] = lt - 1;
      queue.enqueue(item);
      if (ch > 0) {
        queue.enqueue(new int[]{lt, gt, chIndex + 1});
      }
      queue.enqueue(new int[]{gt + 1, hi, chIndex});
    }
  }

  @SuppressWarnings("Duplicates")
  private static void sortRecursion(String[] a, int chIndex, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    int ch = charAt(a[lo], chIndex);
    //核心为利用三个索引切分数组，使得lo～lt-1之间的元素小于选择的元素，gt+1～hi之间的元素大于选择元素
    int i = lo + 1;//与选择元素进行比较的当前元素索引
    int lt = lo, gt = hi;//lt为当i指向的元素小于选择元素时需要交换的索引,gt为当i指向的元素大于选择元素时需要交换的索引
    while (i <= gt) {
      int temp = charAt(a[i], chIndex);
      if (temp == ch) {
        ++i;
      } else if (temp < ch) {
        SortUtils.exec(a, i, lt);
        ++i;
        ++lt;
      } else {
        SortUtils.exec(a, i, gt);
        --gt;
      }
    }
    sortRecursion(a, chIndex, lo, lt - 1);
    if (ch > -1) {
      sortRecursion(a, chIndex + 1, lt, gt);
    }
    sortRecursion(a, chIndex, gt + 1, hi);
  }

  private static int charAt(String s, int i) {
    return i < s.length() ? s.charAt(i) : -1;
  }
}
