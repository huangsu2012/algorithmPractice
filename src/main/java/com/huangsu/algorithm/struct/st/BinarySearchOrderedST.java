package com.huangsu.algorithm.struct.st;


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
  public void deleteMin() {
    for (int i = 1; i < size; i++) {
      keys[i - 1] = keys[i];
      values[i - 1] = values[i];
    }
    --size;
  }

  @Override
  public void deleteMax() {
    if (size < 1) {
      return;
    }
    keys[size - 1] = null;
    values[size - 1] = null;
    --size;
  }


  @Override
  public void put(Key key, Value value) {
    int i = rank(key);
    if (keys[i].equals(key)) {
      values[i] = value;
      return;
    }
    for (int j = size; j > i; j--) {
      keys[j] = keys[j - 1];
      values[j] = values[j - 1];
    }
    keys[i] = key;
    values[i] = value;
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
  public void delete(Key key) {
    int i = rank(key);
    if (keys[i].equals(key)) {
      for (int j = i; j < size - 1; j++) {
        keys[j] = keys[j + 1];
        values[j] = values[j + 1];
      }
      --size;
    }
  }


}
