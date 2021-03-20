package com.huangsu.algorithm.sort;

import java.lang.reflect.Array;

/**
 * Created by huangsu2012@gmail.com on 2018/4/5.
 *
 * 归并排序最差实现，每次归并都创建辅助数组
 */
public class MergeSort {

  public static <T extends Comparable<T>> void sort(Class<T> tClass, T[] items, int start,
      int end) {
    if (start < end) {
//      System.out.println("sort(" + start + "," + end + ")");
      int mid = (end + start) / 2;
      sort(tClass, items, start, mid);
      sort(tClass, items, mid + 1, end);
      merge(items, start, mid, end, tClass);
    }
  }

  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> void merge(T[] items, int start, int mid, int end,
      Class<T> tClass) {
//    System.out.println("merge(" + start + "," + mid + "," + end + ")");
    int leftLen = mid - start + 1;
    int rightLen = end - mid;
    T[] leftArray = (T[]) Array.newInstance(tClass, leftLen);
    T[] rightArray = (T[]) Array.newInstance(tClass, rightLen);
    System.arraycopy(items, start, leftArray, 0, leftLen);
    System.arraycopy(items, start + leftLen, rightArray, 0, rightLen);
    int leftIndex = 0;
    int rightIndex = 0;
    for (int i = start; i <= end; i++) {
      boolean leftMin =
          rightIndex >= rightLen || (leftIndex < leftLen
              && leftArray[leftIndex].compareTo(rightArray[rightIndex]) <= 0);
      if (leftMin) {
        items[i] = leftArray[leftIndex++];
      } else {
        items[i] = rightArray[rightIndex++];
      }
    }
  }
}
