package com.huangsu.algorithm.struct.list;

import com.huangsu.algorithm.struct.Collection;

/**
 * Created by huangsu2012@gmail.com on 2021/2/17.
 */
public interface List<T> extends Collection<T> {

  /**
   * 添加元素到列表
   * @param item 待添加的元素
   * @return true 表示添加成功
   */
  boolean add(T item);

  /**
   * 删除列表中的第index+1的元素
   * @param index 需要删除的元素索引，从0开始
   * @return 删除的元素
   */
  T remove(int index);

  /**
   * 删除第一个与指定元素相同的元素
   * @param item 待删除元素
   * @return 删除成功返回true
   */
  boolean remove(T item);

  /**
   * 获取第index+1的元素
   * @param index 元素索引，从0开始
   * @return 第index+1的元素或者null
   */
  T get(int index);

  /**
   * 返回指定元素的第一个索引
   * @param item 待查找元素
   * @return 指定元素索引，从0开始
   */
  int indexOf(T item);

  /**
   * 返回指定索引范围的元素列表
   * @param start 开始索引，包含
   * @param end 结束索引，不包含
   * @return 指定索引范围的元素列表
   */
  List<T> subList(int start, int end);
}
