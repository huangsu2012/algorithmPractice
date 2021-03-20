package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 */
public class ArrayMinPriorityQueue<T extends Comparable<T>> extends
    AbsArrayPriorityQueue<T> implements MinPriorityQueue<T> {

  public ArrayMinPriorityQueue() {
    this(1);
  }

  public ArrayMinPriorityQueue(int initialCapacity) {
    super(initialCapacity);
  }

  @Override
  public T min() {
    int index = findMin();
    return index < 0 ? null : pq[index];
  }

  private int findMin() {
    T min = null;
    int index = -1;
    for (int i = 0; i < size; i++) {
      if (min == null) {
        min = pq[i];
        index = i;
      } else {
        if (SortUtils.compareTo(min, pq[i]) < 0) {
          min = pq[i];
          index = i;
        }
      }
    }
    return index;
  }

  @Override
  public T delMin() {
    int index = findMin();
    if (index >= 0) {
      SortUtils.exec(pq, index, --size);
      pq[size] = null;
    }
    return index < 0 ? null : pq[index];
  }

}
