package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.util.ArrayUtil;
import com.huangsu.algorithm.util.BinaryHeapUtils;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 */
abstract class AbsArrayHeapPriorityQueue<T extends Comparable<T>> extends
    AbsArrayPriorityQueue<T> implements PriorityQueue<T> {

  public AbsArrayHeapPriorityQueue() {
    this(1);
  }

  public AbsArrayHeapPriorityQueue(int initialCapacity) {
    super(initialCapacity + 1);
  }

  @Override
  public void insert(T item) {
    pq[++size] = item;
    swim(size);
    ArrayUtil.resizeArray(pq, size, 1);
  }

  T delFirst() {
    T item = pq[1];
    SortUtils.exec(pq, 1, size);
    pq[size] = null;
    --size;
    sink(1);
    return item;
  }

  void swim(int index) {
    if (minHeap()) {
      BinaryHeapUtils.swimMinImproved(pq, index);
    } else {
      BinaryHeapUtils.swimMaxImproved(pq, index);
    }
  }

  void sink(int index) {
    if (minHeap()) {
      BinaryHeapUtils.sinkMinImproved(pq, index, size);
    } else {
      BinaryHeapUtils.sinkMaxImproved(pq, index, size);
    }
  }

  abstract boolean minHeap();

  @Override
  public Iterator<T> iterator() {
    return new ArrayHeapPriorityQueueIterator();
  }

  class ArrayHeapPriorityQueueIterator implements Iterator<T> {

    private int index = 1;

    @Override
    public boolean hasNext() {
      return index <= size;
    }

    @Override
    public T next() {
      return pq[index++];
    }
  }
}
