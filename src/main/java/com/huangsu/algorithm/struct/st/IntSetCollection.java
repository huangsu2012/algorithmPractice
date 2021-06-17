package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.Collection;

/**
 * Created by huangsu2012@gmail.com on 2021/6/12.
 *
 * int键集合查询方法相关抽象
 */
public interface IntSetCollection extends Collection {

  int MIN_VALUE = 0x80000000;

  /**
   * 键key在集合中是否存在
   *
   * @param key 键
   * @return true 如果键存在于表中
   */
  boolean contains(int key);

  /**
   * @return 表中所有键的集合
   */
  int[] keys();
}
