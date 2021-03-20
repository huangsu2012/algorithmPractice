package com.huangsu.algorithm.struct.st;


import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/2/23.
 *
 * 基于二分查找的有序符号表实现
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {

  private Key[] keys;
  private Value[] values;
  private int size;

  @SuppressWarnings("unchecked")
  public BinarySearchST(int initialCapacity) {
    keys = (Key[]) new Comparable[initialCapacity];
    values = (Value[]) new Object[initialCapacity];
  }

  @Override
  public Key min() {
    return keys[0];
  }

  @Override
  public Key max() {
    return keys[size - 1];
  }

  @Override
  public Key floor(Key key) {
    return null;
  }

  @Override
  public Key ceiling(Key key) {
    return null;
  }

  @Override
  public int rank(Key key) {
    int low = 0, high = size - 1, mid = low + (high - low) / 2;
    while (high >= low) {
      int compareVal = keys[mid].compareTo(key);
      if (compareVal > 0) {
        high = mid - 1;
      } else if (compareVal < 0) {
        low = mid + 1;
      } else {
        return mid;
      }
      mid = low + (high - low) / 2;
    }
    return low;
  }

  @Override
  public Key select(int k) {
    return k < size && k > -1 ? keys[k] : null;
  }

  @Override
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new LinkedQueue<>();
    for (int i = rank(lo); i < rank(hi); i++) {
      queue.enqueue(keys[i]);
    }
    if (contains(hi)) {
      queue.enqueue(hi);
    }
    return queue;
  }

  @Override
  public void put(Key key, Value value) {
    int i = rank(key);
    if (i < size && keys[i].compareTo(key) == 0) {
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
    if (isEmpty()) {
      return null;
    }
    int i = rank(key);
    if (i < size && keys[i].compareTo(key) == 0) {
      return values[i];
    }
    return null;
  }

  @Override
  public void delete(Key key) {
    int i = rank(key);
    if (i < size && keys[i].compareTo(key) == 0) {
      for (int j = i; j < size - 1; j++) {
        keys[j] = keys[j + 1];
        values[j] = values[j + 1];
      }
      --size;
    }
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }
}
