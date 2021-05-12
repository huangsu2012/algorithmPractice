package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.Collection;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/5.
 *
 * 不可重复键集合抽象
 */
public interface SetCollection<Key> extends Collection<Key> {

  void delete(Key key);

  /**
   * 键key在表中是否存在
   *
   * @param key 键
   * @return true如果键存在于表中
   */
  boolean contains(Key key);

  /**
   * @return 表中所有键的集合
   */
  Iterable<Key> keys();

  default Iterator<Key> iterator() {
    return keys().iterator();
  }
}
