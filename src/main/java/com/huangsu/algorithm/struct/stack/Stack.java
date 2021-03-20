package com.huangsu.algorithm.struct.stack;

import com.huangsu.algorithm.struct.Collection;

/**
 * Created by huangsu2012@gmail.com on 2018/3/13.
 */
public interface Stack<T> extends Collection<T> {

  void push(T item);

  T pop();

  T peek();

}
