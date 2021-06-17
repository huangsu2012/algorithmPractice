package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.CollectionIterable;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/5.
 *
 * 键集合查询方法相关抽象
 */
public interface SetCollection<Key> extends CollectionIterable<Key> {

  /**
   * 键key在集合中是否存在
   *
   * @param key 键
   * @return true 如果键存在于集合中
   */
  boolean contains(Key key);

  /**
   * @return 所有键的集合
   */
  Iterable<Key> keys();

  default Iterator<Key> iterator() {
    return keys().iterator();
  }
}
