package com.huangsu.algorithm.struct.list;

import com.huangsu.algorithm.struct.Collection;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 *
 * 跳表
 */
public interface SkipList<T extends Comparable<T>> extends Collection<T> {

  /**
   * 添加元素到列表
   *
   * @param item 待添加的元素
   * @return true 表示添加成功
   */
  boolean add(T item);

  /**
   * @param item 待查找元素
   * @return 元素在列表中的索引, 如果元素不在索引中返回-1
   */
  int rank(T item);


}
