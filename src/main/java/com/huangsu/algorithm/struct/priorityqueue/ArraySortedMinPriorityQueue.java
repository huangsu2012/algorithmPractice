package com.huangsu.algorithm.struct.priorityqueue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 */
public class ArraySortedMinPriorityQueue<T extends Comparable<T>> extends
    AbsArraySortedPriorityQueue<T> implements MinPriorityQueue<T> {

  @Override
  public T min() {
    return size == 0 ? null : pq[size - 1];
  }

  @Override
  public T delMin() {
    return delEnd();
  }


  @Override
  boolean minPriority() {
    return true;
  }
}
