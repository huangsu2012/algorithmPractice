package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.util.ArrayUtil;
import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 */
abstract class AbsArraySortedPriorityQueue<T extends Comparable<T>> extends
    AbsArrayPriorityQueue<T> implements PriorityQueue<T> {

  public AbsArraySortedPriorityQueue() {
    this(1);
  }

  public AbsArraySortedPriorityQueue(int initialCapacity) {
    super(initialCapacity);
  }

  T delEnd() {
    T item = null;
    if (size > 0) {
      item = pq[--size];
      pq[size] = null;
    }
    return item;
  }


  public void insert(T item) {
    int insertIndex = size;
    if (minPriority()) {
      for (int i = size - 1; i >= 0; i--) {
        if (SortUtils.compareTo(item, pq[i]) <= 0) {
          insertIndex = i;
          break;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (SortUtils.compareTo(item, pq[i]) <= 0) {
          insertIndex = i;
          break;
        }
      }
    }
    if (insertIndex != size) {
      System.arraycopy(pq, insertIndex, pq, insertIndex, size - insertIndex);
    }
    pq[insertIndex] = item;
    ++size;
    ArrayUtil.resizeArray(pq, size, 0);
  }

  abstract boolean minPriority();
}
