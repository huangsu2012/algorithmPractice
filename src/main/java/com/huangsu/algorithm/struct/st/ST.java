package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 *
 * 符号表抽象
 */
public interface ST<Key, Value> {

  /**
   * 将键值对存入表中，若值为空则将键删除
   *
   * @param key 键
   * @param value 值
   */
  void put(Key key, Value value);

  /**
   * 获取键key对应的值，若键key不存在返回null
   *
   * @param key 键
   * @return 返回对应值或者空
   */
  Value get(Key key);

  default void delete(Key key) {
    put(key, null);
  }

  /**
   * 键key在表中是否存在
   *
   * @param key 键
   * @return true如果键存在于表中
   */
  default boolean contains(Key key) {
    return get(key) != null;
  }

  /**
   * @return 表中所有键的集合
   */
  Iterable<Key> keys();

  boolean isEmpty();

  int size();
}
