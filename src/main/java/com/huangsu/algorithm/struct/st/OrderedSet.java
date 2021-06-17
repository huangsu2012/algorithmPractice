package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 *
 * 有序不可重复集合抽象
 */
public interface OrderedSet<Key> extends SetCollectionOrdered<Key>, Set<Key> {

  /**
   * 删除最小的键
   */
  Key deleteMin();

  /**
   * 删除最大的键
   */
  Key deleteMax();
}
