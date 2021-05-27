package com.huangsu.algorithm.struct.st;

import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/19.
 */
public class BinarySearchOrderedSet<Key> extends AbstractSetCollectionOrderedBinarySearch<Key> {


  public BinarySearchOrderedSet(int initialCapacity) {
    super(initialCapacity);
  }

  public BinarySearchOrderedSet(int initialCapacity,
      Comparator<Key> comparator) {
    super(initialCapacity, comparator);
  }
}
