package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/6/14.
 *
 * 有序int集合相关查询方法相关抽象
 */
public interface IntSetCollectionOrdered extends IntSetCollection {
  /**
   * @return 最小的键,如果集合为空，返回{@link #MIN_VALUE}
   */
  int min();

  /**
   * @return 最大的键,如果集合为空，返回{@link #MIN_VALUE}
   */
  int max();

  /**
   * @param key 要查找的键
   * @return 小于等于key的最大键,如果集合为空或者找不到小于等于key的最大键，返回{@link #MIN_VALUE}
   */
  int floor(int key);

  /**
   * @param key 要查找的键
   * @return 大于等于key的最小键，如果集合为空或者找不到大于等于key的最小键，返回{@link #MIN_VALUE}
   */
  int ceiling(int key);

  /**
   * @param key 要查找的键
   * @return 小于key的键的数量
   */
  int rank(int key);

  /**
   * @param k 排名
   * @return 排名为k的键，如果集合为空，返回{@link #MIN_VALUE}
   */
  int select(int k);



  /**
   * @param lo 键区间下限；包含
   * @param hi 键区间上限；包含
   * @return [lo..hi] 之间的键的数量
   */
  int size(int lo, int hi);

  /**
   * @param lo 键区间下限；包含
   * @param hi 键区间上限；包含
   * @return [lo..hi] 之间的所有键，已排序
   */
  int[] keys(int lo, int hi);
}
