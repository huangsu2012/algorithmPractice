package com.huangsu.algorithm.struct.st;


import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/23.
 *
 * 基于二分查找的有序符号表实现
 */
public class BinarySearchOrderedST<Key, Value> extends AbstractOrderedST<Key, Value> implements
    OrderedST<Key, Value> {

  private Key[] keys;
  private Value[] values;
  private int size;

  public BinarySearchOrderedST(int initialCapacity) {
    this(initialCapacity, null);
  }

  @SuppressWarnings("unchecked")
  public BinarySearchOrderedST(int initialCapacity, Comparator<Key> comparator) {
    super(comparator);
    keys = (Key[]) new Object[initialCapacity];
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
    int i = rank(key);
    if (keys[i].equals(key)) {
      return key;
    } else if (i > 0) {
      return keys[i - 1];
    }
    return null;
  }

  @Override
  public Key ceiling(Key key) {
    int i = rank(key);
    return keys[i];
  }

  @Override
  public int rank(Key key) {
    int low = 0, high = size - 1, mid = low + (high - low) / 2;
    while (high >= low) {
      int compareVal = SortUtils.compareTo(keys[mid], key, keyComparator);
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
  public int size(Key lo, Key hi) {
    int loIndex = rank(lo);
    int hiIndex = rank(hi);
    int offset = keys[hiIndex].equals(hi) ? 1 : 0;
    return hiIndex - loIndex + offset;
  }

  @Override
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new LinkedQueue<>();
    int hiIndex = rank(hi);
    for (int i = rank(lo); i < hiIndex; i++) {
      queue.enqueue(keys[i]);
    }
    if (keys[hiIndex].equals(hi)) {
      queue.enqueue(hi);
    }
    return queue;
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

  @Override
  public boolean contains(Key key) {
    int i = rank(key);
    return keys[i].equals(key);
  }

  @Override
  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedQueue<>();
    for (int i = 0; i < size; i++) {
      queue.enqueue(keys[i]);
    }
    return queue;
  }
}
