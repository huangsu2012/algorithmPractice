package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 *
 * 有序符号表抽象
 */
public interface OrderedST<Key extends Comparable<Key>, Value> extends ST<Key, Value> {

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
  default void deleteMin() {
    delete(min());
  }

  /**
   * 删除最大的键
   */
  default void deleteMax() {
    delete(max());
  }

  /**
   * @param lo 键区间下限；包含
   * @param hi 键区间上限；包含
   * @return [lo..hi] 之间的键的数量
   */
  default int size(Key lo, Key hi) {
    if (hi.compareTo(lo) < 0) {
      return 0;
    } else if (contains(hi)) {
      return rank(hi) - rank(lo) + 1;
    } else {
      return rank(hi) - rank(lo);
    }
  }

  /**
   * @param lo 键区间下限；包含
   * @param hi 键区间上限；包含
   * @return [lo..hi] 之间的所有键，已排序
   */
  Iterable<Key> keys(Key lo, Key hi);

  @Override
  default Iterable<Key> keys() {
    return keys(min(), max());
  }
}
