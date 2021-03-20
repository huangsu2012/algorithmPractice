package com.huangsu.algorithm.struct.stack;

import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2018/3/15.
 */
public class ArrayStack<T> implements Stack<T> {

  private T[] items;
  private int size = 0;

  public ArrayStack() {
    this(1);
  }

  @SuppressWarnings("unchecked")
  public ArrayStack(int initialCapacity) {
    items = (T[]) new Object[initialCapacity];
  }

  @Override
  public void push(T item) {
    items[size++] = item;
    items = ArrayUtil.resizeArray(items, size, 0);
  }

  @Override
  public T pop() {
    T item = items[--size];
    items[size] = null;
    return item;
  }

  @Override
  public T peek() {
    return items[size - 1];
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
    return new ArrayStackIterator();
  }

  private class ArrayStackIterator implements Iterator<T> {

    private int index = size - 1;

    @Override
    public boolean hasNext() {
      return index >= 0;
    }

    @Override
    public T next() {
      return items[index--];
    }
  }
}
