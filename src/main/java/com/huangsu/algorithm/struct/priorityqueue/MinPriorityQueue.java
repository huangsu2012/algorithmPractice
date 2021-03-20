package com.huangsu.algorithm.struct.priorityqueue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 *
 * 小顶堆
 */
public interface MinPriorityQueue<T extends Comparable<T>> extends PriorityQueue<T> {


  /**
   * @return 返回最小元素
   */
  T min();

  /**
   * @return 删除并返回最小元素
   */
  T delMin();
}
