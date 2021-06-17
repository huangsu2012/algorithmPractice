package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.util.ArrayUtil;

/**
 * Created by huangsu2012@gmail.com on 2021/6/17.
 */
public class BinarySearchIntOrderedST<Value> extends
    AbstractIntSetCollectionOrderedBinarySearch implements IntSTOrdered<Value> {

  private Value[] values;

  @SuppressWarnings("unchecked")
  public BinarySearchIntOrderedST(int initialCapacity) {
    super(initialCapacity);
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
  public void put(int key, Value value) {
    int i = rank(key);
    if (keys[i] == key) {
      values[i] = value;
      return;
    }
    ArrayUtil.insert(keys, values, key, value, size, i, true);
    ++size;
  }

  @Override
  public Value get(int key) {
    if (size < 1) {
      return null;
    }
    int i = rank(key);
    if (keys[i] == key) {
      return values[i];
    }
    return null;
  }

  @Override
  public Value delete(int key) {
    int i = rank(key);
    Value value = null;
    if (keys[i] == key) {
      value = ArrayUtil.delete(keys, values, size, i);
      --size;
    }
    return value;
  }
}
