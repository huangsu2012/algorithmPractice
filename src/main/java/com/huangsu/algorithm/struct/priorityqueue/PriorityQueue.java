package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.struct.CollectionIterable;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 */
public interface PriorityQueue<T extends Comparable<T>> extends CollectionIterable<T> {

  /**
   * 向优先队列中插入一个元素
   *
   * @param item 要插入的元素
   */
  void insert(T item);
}
