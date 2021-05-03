package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/3/20.
 */
public interface ThreadedST<Key extends Comparable<Key>> {

  /**
   * @param key 要查找的键
   * @return key的下一个键，如果key为最大键则返回空
   */
  Key next(Key key);

  /**
   * @param key 要查找的键
   * @return key的上一个键，如果key为最小键则返回空
   */
  Key prev(Key key);

}
