package com.huangsu.algorithm.struct;

/**
 * Created by huangsu2012@gmail.com on 2020/11/10.
 */
public interface Collection<T> extends Iterable<T> {

  boolean isEmpty();

  int size();
}
