package com.huangsu.algorithm.struct.list;

import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/17.
 */
public class ArrayList<T> implements List<T> {

  private T[] items;
  private int size;

  public ArrayList() {
    this(10);
  }

  @SuppressWarnings("unchecked")
  public ArrayList(int initialCapacity) {
    items = (T[]) new Object[initialCapacity];
    size = 0;
  }

  @Override
  public boolean add(T item) {
    items[size++] = item;
    ArrayUtil.resizeArray(items, size, 0);
    return true;
  }

  @Override
  public T remove(int index) {
    if (index <= 0 || index >= size) {
      return null;
    }
    T item = items[index];
    items[index] = null;
    System.arraycopy(items, index + 1, items, index, size - index - 1);
    --size;
    return item;
  }

  @Override
  public boolean remove(T item) {
    return remove(indexOf(item)) != null;
  }

  @Override
  public T get(int index) {
    return items[index];
  }

  @Override
  public int indexOf(T item) {
    for (int i = 0; i < size; i++) {
      if (ArrayUtil.equals(items, i, item)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public List<T> subList(int start, int end) {
    if (end > size || end < 0) {
      end = size;
    }
    List<T> list = new ArrayList<>(end - start);
    for (int i = start; i < end; i++) {
      list.add(items[i]);
    }
    return list;
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
    return new ArrayListIterator();
  }

  private class ArrayListIterator implements Iterator<T> {

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
