package com.huangsu.algorithm.struct.bag;

import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2020/11/10.
 */
public class ArrayBag<T> implements Bag<T> {

  private T[] items;
  private int size = 0;

  public ArrayBag() {
    this(10);
  }

  @SuppressWarnings("unchecked")
  public ArrayBag(int initialCapacity) {
    items = (T[]) new Object[initialCapacity];
  }

  @Override
  public void add(T item) {
    items[size++] = item;
    ArrayUtil.resizeArray(items, size, 0);
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<T> iterator() {
    return new ArrayBagIterator();
  }

  private class ArrayBagIterator implements Iterator<T> {

    private int index = 0;

    @Override
    public boolean hasNext() {
      return index < size;
    }

    @Override
    public T next() {
      return items[index++];
    }
  }
}
