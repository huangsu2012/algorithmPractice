package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 *
 * 有序符号表抽象
 */
public interface OrderedST<Key, Value> extends ST<Key, Value>, SetCollectionOrdered<Key> {

  /**
   * 删除最小的键
   */
  Value deleteMin();

  /**
   * 删除最大的键
   */
  Value deleteMax();
}
