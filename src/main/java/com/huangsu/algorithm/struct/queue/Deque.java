package com.huangsu.algorithm.struct.queue;

import com.huangsu.algorithm.struct.CollectionIterable;

/**
 * Created by huangsu2012@gmail.com on 2020/11/18.
 *
 * 双向队列
 */
public interface Deque<T> extends CollectionIterable<T> {

  /**
   * 向左端添加一个元素
   * @param t
   */
  void pushLeft(T t);

  /**
   * 向右端添加一个元素
   * @param t
   */
  void pushRight(T t);

  /**
   * 从左端删除一个元素
   * @return
   */
  T popLeft();

  /**
   * 从右端删除一个元素
   * @return
   */
  T popRight();
}
