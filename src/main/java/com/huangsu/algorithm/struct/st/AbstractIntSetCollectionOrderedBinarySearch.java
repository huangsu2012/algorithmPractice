package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/6/17.
 */
public abstract class AbstractIntSetCollectionOrderedBinarySearch implements
    IntSetCollectionOrdered {

  protected int[] keys;
  protected int size;

  public AbstractIntSetCollectionOrderedBinarySearch(int initialCapacity) {
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("initialCapacity must gt 0");
    }
    keys = new int[initialCapacity];
  }

  @Override
  public int min() {
    return size > 0 ? keys[0] : MIN_VALUE;
  }

  @Override
  public int max() {
    return size > 0 ? keys[size - 1] : MIN_VALUE;
  }

  @Override
  public int floor(int key) {
    int i = rank(key);
    if (keys[i] == key) {
      return key;
    } else if (i > 0) {
      return keys[i - 1];
    }
    return MIN_VALUE;
  }

  @Override
  public int ceiling(int key) {
    int i = rank(key);
    return size > 0 ? keys[i] : MIN_VALUE;
  }

  @Override
  public int rank(int key) {
    int low = 0, high = size - 1, mid = low + (high - low) / 2;
    while (high >= low) {
      int compareVal = SortUtils.compareTo(keys[mid], key);
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
  public int select(int k) {
    return k < size && k > -1 ? keys[k] : MIN_VALUE;
  }

  @Override
  public int size(int lo, int hi) {
    int loIndex = rank(lo);
    int hiIndex = rank(hi);
    int offset = keys[hiIndex] == hi ? 1 : 0;
    return hiIndex - loIndex + offset;
  }

  @Override
  public int[] keys(int lo, int hi) {
    if (size < 1) {
      return null;
    }
    int hiIndex = rank(hi);
    int lowIndex = rank(lo);
    int[] result;
    if (keys[hiIndex] == hi) {
      result = new int[hiIndex - lowIndex + 1];
    } else {
      result = new int[hiIndex - lowIndex];
    }
    System.arraycopy(keys, lowIndex, result, 0, result.length);
    return result;
  }

  @Override
  public boolean contains(int key) {
    return size > 0 && keys[rank(key)] == key;
  }

  @Override
  public int[] keys() {
    if (size < 1) {
      return null;
    }
    int[] result = new int[size];
    System.arraycopy(keys, 0, result, 0, size);
    return result;
  }

  @Override
  public boolean isEmpty() {
    return size < 1;
  }

  @Override
  public int size() {
    return size;
  }
}
