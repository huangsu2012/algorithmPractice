package com.huangsu.algorithm.struct.st;


import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/23.
 *
 * 基于二分查找的有序符号表实现
 */
public class BinarySearchOrderedST<Key, Value> extends
    AbstractSetCollectionOrderedBinarySearch<Key> implements
    OrderedST<Key, Value> {

  private Value[] values;

  public BinarySearchOrderedST(int initialCapacity) {
    this(initialCapacity, null);
  }

  @SuppressWarnings("unchecked")
  public BinarySearchOrderedST(int initialCapacity, Comparator<Key> comparator) {
    super(initialCapacity, comparator);
    values = (Value[]) new Object[initialCapacity];
  }


  @Override
  public Value deleteMin() {
    Value value = null;
    if (size > 0) {
      value = ArrayUtil.delete(keys, values, size, 0);
      --size;
    }
    return value;
  }

  @Override
  public Value deleteMax() {
    Value value = null;
    if (size > 0) {
      value = ArrayUtil.delete(keys, values, size, size - 1);
      --size;
    }
    return value;
  }


  @Override
  public void put(Key key, Value value) {
    int i = rank(key);
    if (keys[i].equals(key)) {
      values[i] = value;
      return;
    }
    ArrayUtil.insert(keys, values, key, value, size, i, true);
    ++size;
  }

  @Override
  public Value get(Key key) {
    if (size < 1) {
      return null;
    }
    int i = rank(key);
    if (keys[i].equals(key)) {
      return values[i];
    }
    return null;
  }

  @Override
  public Value delete(Key key) {
    int i = rank(key);
    Value value = null;
    if (keys[i].equals(key)) {
      value = ArrayUtil.delete(keys, values, size, i);
      --size;
    }
    return value;
  }

}
