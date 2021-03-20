package com.huangsu.algorithm.struct.priorityqueue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 *
 * 大顶堆
 */
public interface MaxPriorityQueue<T extends Comparable<T>> extends PriorityQueue<T> {


  /**
   *
   * @return 返回最大元素
   */
  T max();

  /**
   *
   * @return 删除并返回最大元素
   */
  T delMax();

}
