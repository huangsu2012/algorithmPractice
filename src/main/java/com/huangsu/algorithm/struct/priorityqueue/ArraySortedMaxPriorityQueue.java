package com.huangsu.algorithm.struct.priorityqueue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 *
 * 基于有序数组实现的优先队列
 */
public class ArraySortedMaxPriorityQueue<T extends Comparable<T>> extends
    AbsArraySortedPriorityQueue<T> implements MaxPriorityQueue<T> {

  public ArraySortedMaxPriorityQueue() {
    this(1);
  }

  public ArraySortedMaxPriorityQueue(int initialCapacity) {
    super(initialCapacity);
  }

  @Override
  boolean minPriority() {
    return false;
  }

  @Override
  public T max() {
    return size == 0 ? null : pq[size - 1];
  }

  @Override
  public T delMax() {
    return delEnd();
  }
}
