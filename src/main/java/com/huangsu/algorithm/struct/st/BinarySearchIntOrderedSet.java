package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.util.ArrayUtil;

/**
 * Created by huangsu2012@gmail.com on 2021/6/17.
 */
public class BinarySearchIntOrderedSet extends
    AbstractIntSetCollectionOrderedBinarySearch implements IntSetOrdered {

  public BinarySearchIntOrderedSet(int initialCapacity) {
    super(initialCapacity);
  }

  @Override
  public int deleteMin() {
    int key = MIN_VALUE;
    if (size > 0) {
      key = keys[0];
      ArrayUtil.delete(keys, size, 0);
      --size;
    }
    return key;
  }

  @Override
  public int deleteMax() {
    int key = MIN_VALUE;
    if (size > 0) {
      key = keys[size - 1];
      ArrayUtil.delete(keys, size, size - 1);
      --size;
    }
    return key;
  }

  @Override
  public boolean add(int key) {
    int i = rank(key);
    if (keys[i] == key) {
      return false;
    }
    ArrayUtil.insert(keys, key, size++, i, true);
    return true;
  }

  @Override
  public boolean delete(int key) {
    boolean match = false;
    if (size > 0) {
      int i = rank(key);
      match = keys[i] == key;
      if (match) {
        ArrayUtil.delete(keys, size, i);
        --size;
      }
    }
    return match;
  }
}
