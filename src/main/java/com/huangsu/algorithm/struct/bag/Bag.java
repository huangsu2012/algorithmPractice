package com.huangsu.algorithm.struct.bag;

import com.huangsu.algorithm.struct.CollectionIterable;

/**
 * Created by huangsu2012@gmail.com on 2020/11/10.
 *
 * 背包抽象，只进不出，而且顺序不重要
 */
public interface Bag<T> extends CollectionIterable<T> {

  void add(T item);

}
