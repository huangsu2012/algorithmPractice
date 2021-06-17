package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/6/14.
 */
public interface IntSetOrdered extends IntSetCollectionOrdered, IntSet {

  /**
   * 删除最小的键
   */
  int deleteMin();

  /**
   * 删除最大的键
   */
  int deleteMax();
}
