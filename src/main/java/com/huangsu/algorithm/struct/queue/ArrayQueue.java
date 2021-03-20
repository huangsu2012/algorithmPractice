package com.huangsu.algorithm.struct.queue;


import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2018/3/13.
 */
public class ArrayQueue<T> implements Queue<T> {

  private T[] items;
  private int size = 0;

  public ArrayQueue() {
    this(1);
  }

  @SuppressWarnings("unchecked")
  public ArrayQueue(int initialCapacity) {
    items = (T[]) new Object[initialCapacity];
  }

  @Override
  public void enqueue(T item) {
    items[size++] = item;
    items = ArrayUtil.resizeArray(items, size, 0);
  }

  @Override
  public T dequeue() {
    T item = items[0];
    System.arraycopy(items, 1, items, 0, --size);
    return item;
  }

  @Override
  public T peek() {
    return size == 0 ? null : items[0];
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
    return new ArrayQueueIterator();
  }

  private class ArrayQueueIterator implements Iterator<T> {

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
