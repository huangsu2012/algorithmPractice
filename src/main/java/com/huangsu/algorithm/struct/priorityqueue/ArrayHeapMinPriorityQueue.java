package com.huangsu.algorithm.struct.priorityqueue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 *
 * 基于二叉堆实现的优先级队列
 */
public class ArrayHeapMinPriorityQueue<T extends Comparable<T>> extends
    AbsArrayHeapPriorityQueue<T> implements MinPriorityQueue<T> {

  public ArrayHeapMinPriorityQueue() {
    this(1);
  }

  public ArrayHeapMinPriorityQueue(int initialCapacity) {
    super(initialCapacity);
  }

  @Override
  boolean minHeap() {
    return true;
  }

  @Override
  public T min() {
    return size > 0 ? pq[1] : null;
  }

  @Override
  public T delMin() {
    return delFirst();
  }
}
