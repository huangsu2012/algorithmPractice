package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/19.
 */
public class BinarySearchOrderedSet<Key> extends
    AbstractSetCollectionOrderedBinarySearch<Key> implements OrderedSet<Key> {


  public BinarySearchOrderedSet(int initialCapacity) {
    super(initialCapacity);
  }

  public BinarySearchOrderedSet(int initialCapacity,
      Comparator<Key> comparator) {
    super(initialCapacity, comparator);
  }

  @Override
  public boolean add(Key key) {
    int i = rank(key);
    if (keys[i].equals(key)) {
      return false;
    }
    ArrayUtil.insert(keys, key, size++, i, true);
    return true;
  }

  @Override
  public Key deleteMin() {
    Key key = null;
    if (size > 0) {
      key = keys[0];
      ArrayUtil.delete(keys, size, 0);
      --size;
    }
    return key;
  }

  @Override
  public Key deleteMax() {
    Key key = null;
    if (size > 0) {
      key = keys[size - 1];
      ArrayUtil.delete(keys, size, size - 1);
      --size;
    }
    return key;
  }

  @Override
  public boolean delete(Key key) {
    boolean match = false;
    if (size > 0) {
      int i = rank(key);
      match = keys[i].equals(key);
      if (match) {
        ArrayUtil.delete(keys, size, i);
        --size;
      }
    }
    return match;
  }


}
