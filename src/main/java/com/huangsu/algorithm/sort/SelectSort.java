package com.huangsu.algorithm.sort;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2018/3/29.
 */
public class SelectSort {

  @SuppressWarnings("unchecked")
//  public static <T extends Comparable<T>> T[] sort(T[] items, Class<T> clazz) {
//    T[] sortedItems = (T[]) Array.newInstance(clazz, items.length);
//    int count = items.length;
//    for (int i = 0; i < count; i++) {
//      int minItemIndex = -1;
//      T minItem = null;
//      for (int j = 0; j < items.length; j++) {
//        T item = items[j];
//        if (item == null) {
//          continue;
//        }
//        if (minItem == null || minItem.compareTo(item) > 0) {
//          minItem = item;
//          minItemIndex = j;
//        }
//      }
//      items[minItemIndex] = null;
//      sortedItems[i] = minItem;
//    }
//    return sortedItems;
//  }

  public static <T extends Comparable<T>> void sort(T[] items) {
    for (int i = 0; i < items.length; i++) {
      int minItemIndex = i;
      for (int j = i + 1; j < items.length; j++) {
        if (SortUtils.less(items[j], items[minItemIndex])) {
          minItemIndex = j;
        }
      }
      SortUtils.exec(items, i, minItemIndex);
    }
  }

}
