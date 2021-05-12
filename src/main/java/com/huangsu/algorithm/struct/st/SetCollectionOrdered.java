package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/5.
 *
 * 有序key集合相关抽象
 */
public interface SetCollectionOrdered<Key> extends SetCollection<Key> {

  /**
   * @return 最小的键
   */
  Key min();

  /**
   * @return 最大的键
   */
  Key max();

  /**
   * @param key 要查找的键
   * @return 小于等于key的最大键
   */
  Key floor(Key key);

  /**
   * @param key 要查找的键
   * @return 大于等于key的最小键
   */
  Key ceiling(Key key);

  /**
   * @param key 要查找的键
   * @return 小于key的键的数量
   */
  int rank(Key key);

  /**
   * @param k 排名
   * @return 排名为k的键
   */
  Key select(int k);

  /**
   * 删除最小的键
   */
  void deleteMin();

  /**
   * 删除最大的键
   */
  void deleteMax();

  /**
   * @param lo 键区间下限；包含
   * @param hi 键区间上限；包含
   * @return [lo..hi] 之间的键的数量
   */
  int size(Key lo, Key hi);

  /**
   * @param lo 键区间下限；包含
   * @param hi 键区间上限；包含
   * @return [lo..hi] 之间的所有键，已排序
   */
  Iterable<Key> keys(Key lo, Key hi);
}
