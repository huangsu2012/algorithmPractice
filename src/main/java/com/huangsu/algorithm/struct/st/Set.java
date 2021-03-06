package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 *
 * 范型键集合抽象
 */
public interface Set<Key> extends SetCollection<Key> {

  /**
   * 将键存入集合中
   *
   * @param key 键
   * @return true 如果集合中不存在相同的键
   */
  boolean add(Key key);

  /**
   * 将键从集合删除
   *
   * @param key 键
   * @return true 如果集合中存在要删除的键
   */
  boolean delete(Key key);
}
