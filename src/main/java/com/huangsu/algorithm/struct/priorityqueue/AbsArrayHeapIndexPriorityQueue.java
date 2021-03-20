package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.util.SortUtils;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/3/13.
 *
 * 索引优先队列抽象实现
 */
abstract class AbsArrayHeapIndexPriorityQueue<T extends Comparable<T>> implements
    IndexPriorityQueue<T> {

  /**
   * 元素的值为元素下标
   */
  int[] pq;
  /**
   * 元素的值为pq的下标，qp[pq[i]]=pq[qp[i]]=i,这里的等式不意味着i表达的是同一个东西，qp[pq[i]]中的i是pq的索引，pq[qp[i]]中的i是元素索引
   */
  int[] qp;
  /**
   * 元素数组
   */
  T[] items;
  int size;

  public AbsArrayHeapIndexPriorityQueue() {
    this(1);
  }

  @SuppressWarnings("unchecked")
  public AbsArrayHeapIndexPriorityQueue(int initialCapacity) {
    items = (T[]) new Comparable[initialCapacity];
    pq = new int[initialCapacity + 1];
    qp = new int[initialCapacity];
    for (int i = 0; i < initialCapacity; i++) {
      pq[i] = -1;
      qp[i] = -1;
    }
  }

  @Override
  public void insert(int index, T item) {
    ++size;
    qp[index] = size;
    pq[size] = index;
    items[index] = item;
    swim(size);
  }

  /**
   * 注意此处逻辑和delete方法的不同之处，此处的逻辑是对同一个元素进行上浮，下沉操作
   *
   * @param index 索引
   * @param item 要修改的元素
   */
  @Override
  public void change(int index, T item) {
    items[index] = item;
    swim(qp[index]);
    sink(qp[index]);
  }

  @Override
  public boolean contains(int index) {
    return qp[index] > -1;
  }

  /**
   * 注意此处逻辑和change方法的不同之处，此处的逻辑是先对pq中的最后一个元素进行上浮，然后再对被上浮操作交换过来的元素进行下沉操作，两个元素有可能是不同的
   *
   * @param index 要删除的元素索引
   */
  @Override
  public void delete(int index) {
    int pqIndex = qp[index];
    if (pqIndex > -1) {
      exec(pqIndex, size--);
      swim(pqIndex);
      sink(pqIndex);
      items[index] = null;
      qp[index] = -1;
    }
  }


  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  T first() {
    return items[pq[1]];
  }

  int firstIndex() {
    return pq[1];
  }

  int delFirst() {
    int itemIndex = pq[1];
    exec(1, size--);
    sink(1);
    items[itemIndex] = null;
    qp[itemIndex] = -1;
    return itemIndex;
  }


  void swim(int index) {
//    if (minHeap()) {
//      swimMin(index);
//    } else {
//      swimMax(index);
//    }
//    int compareToMulti = minHeap() ? 1 : -1;
    int minPIndex = 1;
    int pIndex = index / 2;
    while (index > minPIndex && compareTo(index, pIndex) < 0) {
      exec(index, pIndex);
      index = pIndex;
      pIndex = pIndex / 2;
    }
  }

//  private void swimMax(int index) {
//    int minPIndex = 1;
//    int pIndex = index / 2;
//    while (index > minPIndex && compareTo(index, pIndex) > 0) {
//      exec(index, pIndex);
//      index = pIndex;
//      pIndex = pIndex / 2;
//    }
//  }
//
//  private void swimMin(int index) {
//    int minPIndex = 1;
//    int pIndex = index / 2;
//    while (index > minPIndex && compareTo(index, pIndex) < 0) {
//      exec(index, pIndex);
//      index = pIndex;
//      pIndex = pIndex / 2;
//    }
//  }

  void sink(int index) {
//    if (minHeap()) {
//      sinkMin(index);
//    } else {
//      sinkMax(index);
//    }
    int cIndex = index * 2;
    while (cIndex <= size) {
      if (cIndex < size && compareTo(cIndex, cIndex + 1) > 0) {
        ++cIndex;
      }
      if (compareTo(index, cIndex) < 0) {
        break;
      }
      exec(index, cIndex);
      index = cIndex;
      cIndex = cIndex * 2;
    }
  }

//  private void sinkMax(int pIndex) {
//    int cIndex = pIndex * 2;
//    while (cIndex <= size) {
//      if (cIndex < size && compareTo(cIndex, cIndex + 1) < 0) {
//        ++cIndex;
//      }
//      if (compareTo(pIndex, cIndex) > 0) {
//        break;
//      }
//      exec(pIndex, cIndex);
//      pIndex = cIndex;
//      cIndex = cIndex * 2;
//    }
//  }
//
//  private void sinkMin(int pIndex) {
//    int cIndex = pIndex * 2;
//    while (cIndex <= size) {
//      if (cIndex < size && compareTo(cIndex, cIndex + 1) > 0) {
//        ++cIndex;
//      }
//      if (compareTo(pIndex, cIndex) < 0) {
//        break;
//      }
//      exec(pIndex, cIndex);
//      pIndex = cIndex;
//      cIndex = cIndex * 2;
//    }
//  }

  private void exec(int i, int j) {
    qp[pq[j]] = i;
    qp[pq[i]] = j;
    int tmp = pq[i];
    pq[i] = pq[j];
    pq[j] = tmp;
  }

  //  private int compareTo(int i, int j) {
//    return SortUtils.compareTo(items[pq[i]], items[pq[j]]);
//  }

  /**
   * 小顶堆的时候会正常比较下标i，j对应的元素，大顶堆两者顺序进行调转，原因是上浮与下沉实现都基于小顶堆
   */
  private int compareTo(int i, int j) {
    return minHeap() ? SortUtils.compareTo(items[pq[i]], items[pq[j]])
        : SortUtils.compareTo(items[pq[j]], items[pq[i]]);
  }


  abstract boolean minHeap();


  @Override
  public Iterator<T> iterator() {
    return new ArrayHeapIndexPriorityQueueIterator();
  }

  class ArrayHeapIndexPriorityQueueIterator implements Iterator<T> {

    private int index = 1;

    @Override
    public boolean hasNext() {
      return index <= size;
    }

    @Override
    public T next() {
      return items[index++];
    }
  }
}
