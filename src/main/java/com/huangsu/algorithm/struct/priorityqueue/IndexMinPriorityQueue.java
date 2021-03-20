package com.huangsu.algorithm.struct.priorityqueue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/13.
 *
 * 索引优先队列
 */
public interface IndexMinPriorityQueue<T extends Comparable<T>> extends IndexPriorityQueue<T> {


  /**
   * @return 返回最小元素
   */
  T min();

  /**
   * @return 最小元素索引
   */
  int minIndex();

  /**
   * 删除最小元素并返回它的索引
   *
   * @return 最小元素索引
   */
  default int delMin() {
    int index = minIndex();
    if (index > -1) {
      delete(index);
    }
    return index;
  }
}
