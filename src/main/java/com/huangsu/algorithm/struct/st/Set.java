package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 *
 * 不可重复集合抽象
 */
public interface Set<Key> extends SetCollection<Key> {

  /**
   * 将键存入集合中
   *
   * @param key 键
   * @return true 如果集合中不存在相同的键
   */
  boolean add(Key key);


}
