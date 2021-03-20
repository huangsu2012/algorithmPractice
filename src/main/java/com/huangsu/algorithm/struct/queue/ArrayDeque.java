package com.huangsu.algorithm.struct.queue;

import com.huangsu.algorithm.util.ArrayUtil;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2020/11/18.
 */
public class ArrayDeque<T> implements Deque<T> {

  private T[] items;
  private int size = 0;

  public ArrayDeque() {
    this(10);
  }

  @SuppressWarnings("unchecked")
  public ArrayDeque(int initialCapacity) {
    items = (T[]) new Object[initialCapacity];
  }

  @Override
  public void pushLeft(T t) {
    System.arraycopy(items, 0, items, 1, size);
    items[0] = t;
    ++size;
    ArrayUtil.resizeArray(items, size, 0);
  }

  @Override
  public void pushRight(T t) {
    items[size++] = t;
    ArrayUtil.resizeArray(items, size, 0);
  }

  @Override
  public T popLeft() {
    T val = items[0];
    items[0] = null;
    System.arraycopy(items, 1, items, 0, --size);
    return val;
  }

  @Override
  public T popRight() {
    T val = items[--size];
    items[size] = null;
    return val;
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
    return new ArrayDequeIterator();
  }

  private class ArrayDequeIterator implements Iterator<T> {

    private int index = 0;
    private int initSize = size;

    @Override
    public boolean hasNext() {
      checkModification();
      return index < size;
    }

    private void checkModification() {
      if (size != initSize) {
        throw new ConcurrentModificationException();
      }
    }

    @Override
    public T next() {
      checkModification();
      return items[index++];
    }
  }
}
