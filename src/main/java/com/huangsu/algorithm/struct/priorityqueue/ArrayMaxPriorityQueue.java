package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 *
 * 优先队列无序实现
 */
public class ArrayMaxPriorityQueue<T extends Comparable<T>> extends
    AbsArrayPriorityQueue<T> implements MaxPriorityQueue<T> {

  public ArrayMaxPriorityQueue() {
    this(1);
  }

  public ArrayMaxPriorityQueue(int initialCapacity) {
    super(initialCapacity);
  }

  @Override
  public T max() {
    int index = findMax();
    return index < 0 ? null : pq[index];
  }

  private int findMax() {
    T max = null;
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (max == null) {
        max = pq[i];
        index = i;
      } else {
        if (SortUtils.compareTo(max, pq[i]) > 0) {
          max = pq[i];
          index = i;
        }
      }
    }
    return index;
  }

  @Override
  public T delMax() {
    int index = findMax();
    if (index >= 0) {
      SortUtils.exec(pq, index, --size);
      pq[size] = null;
    }
    return index < 0 ? null : pq[index];
  }


}
