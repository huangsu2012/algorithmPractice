package com.huangsu.algorithm.struct.st;

import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
abstract class AbstractOrderedST<Key, Value> extends AbstractST<Key, Value> implements
    OrderedST<Key, Value> {

  final Comparator<Key> keyComparator;

  protected AbstractOrderedST() {
    this(null);
  }

  protected AbstractOrderedST(Comparator<Key> keyComparator) {
    this.keyComparator = keyComparator;
  }

}
