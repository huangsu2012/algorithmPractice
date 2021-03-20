package com.huangsu.algorithm.struct.priorityqueue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 *
 * 基于二叉堆实现的优先级队列
 */
public class ArrayHeapMaxPriorityQueue<T extends Comparable<T>> extends
    AbsArrayHeapPriorityQueue<T> implements MaxPriorityQueue<T> {

  public ArrayHeapMaxPriorityQueue() {
    this(1);
  }

  public ArrayHeapMaxPriorityQueue(int initialCapacity) {
    super(initialCapacity);
  }

  @Override
  boolean minHeap() {
    return false;
  }

  @Override
  public T max() {
    return size > 0 ? pq[1] : null;
  }

  @Override
  public T delMax() {
    return delFirst();
  }
}
