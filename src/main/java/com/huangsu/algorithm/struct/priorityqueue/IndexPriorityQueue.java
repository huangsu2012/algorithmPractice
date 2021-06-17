package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.struct.CollectionIterable;

/**
 * Created by huangsu2012@gmail.com on 2021/3/13.
 *
 * 索引优先队列
 */
public interface IndexPriorityQueue<T extends Comparable<T>> extends CollectionIterable<T> {

  /**
   * 向优先队列中插入一个元素并和索引k关联
   *
   * @param index 索引
   * @param item 要插入的元素
   */
  void insert(int index, T item);

  /**
   * 将索引为k的元素设为指定元素
   *
   * @param index 索引
   * @param item 要修改的元素
   */
  void change(int index, T item);

  /**
   * 是否存在索引为index的元素
   *
   * @param index 要查询的索引
   */
  boolean contains(int index);


  /**
   * 删去索引k及其相关联的元素
   *
   * @param index 要删除的元素索引
   */
  void delete(int index);

}
