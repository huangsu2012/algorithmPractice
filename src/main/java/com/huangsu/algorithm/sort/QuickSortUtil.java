package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/30.
 */
abstract class QuickSortUtil {

  static <T extends Comparable<T>> int partion(T[] items, int lo, int hi) {
    int i = lo, j = hi + 1;
    T val = items[lo];
    while (true) {
      while (SortUtils.less(items[++i], val)) {
        if (i == hi) {
          break;
        }
      }
      while (SortUtils.less(val, items[--j])) {
        ;
      }
      if (i >= j) {
        break;
      }

      SortUtils.exec(items, i, j);
    }
    SortUtils.exec(items, lo, j);
    return j;
  }

  static <T extends Comparable<T>> int[] partionThree(T[] items, int lo, int hi) {
    //核心为利用三个索引切分数组，使得lo～lt-1之间的元素小于选择的元素，gt+1～hi之间的元素大于选择元素
    int i = lo + 1;//与选择元素进行比较的当前元素索引
    int lt = lo, gt = hi;//lt为当i指向的元素小于选择元素时需要交换的索引,gt为当i指向的元素大于选择元素时需要交换的索引
    T val = items[lo];
    while (i <= gt) {
      int cmp = SortUtils.compareTo(items[i], val);
      if (cmp == 0) {
        ++i;
      } else if (cmp > 0) {
        SortUtils.exec(items, i, gt--);
      } else {//此处的交换其实是相当于把与选择元素相等的元素往右移动了
        SortUtils.exec(items, i++, lt++);
      }
    }
    return new int[]{lt - 1, gt + 1};
  }

//  static <T extends Comparable<T>> int[] partionThreeQuick(T[] items, int lo, int hi) {
//    //核心为利用四个索引切分数组，使得lo～p-1,q+1~hi之间的元素等于选择的元素，p~i之间的元素小于选择元素，j~q之间的元素大于选择元素
//    int i = lo + 1;//与选择元素进行比较的当前元素索引
//    int j = hi;
//    int p = lo + 1, q = hi;
//    T val = items[lo];
//    while (true) {
//      int cmpLow = SortUtils.compareTo(items[i], val);
//      if (cmpLow == 0) {
//        SortUtils.exec(items, i++, p++);
//      } else if (cmpLow < 0) {
//        ++i;
//      }
//      int cmpHi = SortUtils.compareTo(items[j], val);
//      if (cmpHi == 0) {
//        SortUtils.exec(items, j--, q--);
//      } else if (cmpHi > 0) {
//        ++j;
//      }
//      if (cmpLow > 0 && cmpHi < 0) {
//        SortUtils.exec(items, i, j);
//      }
//
//    }
//    return new int[]{j, i};
//  }
}
