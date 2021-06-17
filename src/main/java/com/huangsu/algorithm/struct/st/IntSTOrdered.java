package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/6/14.
 */
public interface IntSTOrdered<Value> extends IntST<Value>, IntSetCollectionOrdered {

  /**
   * 删除最小的键
   */
  Value deleteMin();

  /**
   * 删除最大的键
   */
  Value deleteMax();
}
