package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 */
abstract class AbsArrayPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {

  T[] pq;
  int size;

  public AbsArrayPriorityQueue() {
    this(1);
  }

  @SuppressWarnings("unchecked")
  public AbsArrayPriorityQueue(int initialCapacity) {
    pq = (T[]) new Comparable[initialCapacity];
  }

  @Override
  public void insert(T item) {
    pq[size++] = item;
    ArrayUtil.resizeArray(pq, size, 0);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }


  @Override
  public Iterator<T> iterator() {
    return new ArrayPriorityQueueIterator();
  }

  class ArrayPriorityQueueIterator implements Iterator<T> {

    private int index = 0;

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      return pq[index++];
    }
  }
}
