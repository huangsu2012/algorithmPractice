package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/19.
 */
public abstract class AbstractSetCollectionOrderedBinarySearch<Key> extends
    AbstractSetCollectionOrdered<Key> {

  protected Key[] keys;
  protected int size;

  public AbstractSetCollectionOrderedBinarySearch(int initialCapacity) {
    this(initialCapacity, null);
  }

  @SuppressWarnings("unchecked")
  public AbstractSetCollectionOrderedBinarySearch(int initialCapacity, Comparator<Key> comparator) {
    super(comparator);
    if (initialCapacity < 1) {
      throw new IllegalArgumentException("initialCapacity must gt 0");
    }
    keys = (Key[]) new Object[initialCapacity];
  }


  @Override
  public Key min() {
    return size > 0 ? keys[0] : null;
  }

  @Override
  public Key max() {
    return size > 0 ? keys[size - 1] : null;
  }

  @Override
  public Key floor(Key key) {
    if (size > 0) {
      int i = rank(key);
      if (keys[i].equals(key)) {
        return key;
      } else if (i > 0) {
        return keys[i - 1];
      }
    }
    return null;
  }

  @Override
  public Key ceiling(Key key) {
    return size > 0 ? keys[rank(key)] : null;
  }

  @Override
  public int rank(Key key) {
    int low = 0, high = size - 1, mid = low + (high - low) / 2;
    if (key == null || size < 1) {
      return low;
    }
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
  public int size(Key lo, Key hi) {
    if (size < 1) {
      return 0;
    }
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
  public boolean contains(Key key) {
    return size > 0 && keys[rank(key)].equals(key);
  }

  @Override
  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedQueue<>();
    for (int i = 0; i < size; i++) {
      queue.enqueue(keys[i]);
    }
    return queue;
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
