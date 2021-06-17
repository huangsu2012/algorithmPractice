package com.huangsu.algorithm.struct.queue;

import com.huangsu.algorithm.struct.CollectionIterable;

/**
 * Created by huangsu2012@gmail.com on 2018/3/13.
 *
 * 队列抽象，先进先出
 */
public interface Queue<T> extends CollectionIterable<T> {

  void enqueue(T item);

  T dequeue();

  T peek();

}
