package com.huangsu.algorithm.struct.stack;

import com.huangsu.algorithm.struct.CollectionIterable;

/**
 * Created by huangsu2012@gmail.com on 2018/3/13.
 */
public interface Stack<T> extends CollectionIterable<T> {

  void push(T item);

  T pop();

  T peek();

}
