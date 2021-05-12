package com.huangsu.algorithm.struct.st;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 *
 * 符号表抽象
 */
public interface ST<Key, Value> extends SetCollection<Key> {

  /**
   * 将键值对存入表中
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




}
