package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/6/12.
 *
 * key为int的符号表
 */
public interface IntST<Value> extends IntSetCollection {

  /**
   * 将键值对存入表中
   *
   * @param key 键
   * @param value 值
   */
  void put(int key, Value value);

  /**
   * 获取键key对应的值，若键key不存在返回null
   *
   * @param key 键
   * @return 返回对应值或者空
   */
  Value get(int key);

  /**
   * 删除键对应的记录
   *
   * @param key 键
   * @return 被删除键对应的value或者null
   */
  Value delete(int key);


}
