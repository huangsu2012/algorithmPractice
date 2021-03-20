package com.huangsu.algorithm.struct.queue;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2018/3/18.
 */
public class ArrayQueueMoreEfficient<T> implements Queue<T> {

  private T[] items;
  private int size = 0;
  private int queueStart;

  public ArrayQueueMoreEfficient() {
    this(10);
  }

  @SuppressWarnings("unchecked")
  public ArrayQueueMoreEfficient(int initialCapacity) {
    items = (T[]) new Object[initialCapacity];
  }

  @Override
  public void enqueue(T item) {
    items[queueStart + size] = item;
    ++size;
    resizeArray();
  }

  @Override
  public T dequeue() {
    T item = items[queueStart];
    ++queueStart;
    --size;
    resizeArray();
    return item;
  }

  @Override
  public T peek() {
    return items[queueStart];
  }

  @SuppressWarnings("unchecked")
  private void resizeArray() {
    if (queueStart + size == items.length) {
      T[] temp;
      int copyStart = queueStart;
      if (queueStart > 0) {
        temp = (T[]) new Object[items.length];
        copyStart = queueStart;
      } else {
        temp = (T[]) new Object[items.length * 7 / 4];
      }
      System.out.println(
          "resizeArray,queueStart:" + queueStart + ",size:" + size + ",originArray length:"
              + items.length + ",newArray length:" + temp.length);
      System.arraycopy(items, copyStart, temp, 0, size);
      items = temp;
      queueStart = 0;
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

  @Override
  public Iterator<T> iterator() {
    return new ArrayQueueIterator();
  }

  private class ArrayQueueIterator implements Iterator<T> {

    private int index = queueStart;

    @Override
    public boolean hasNext() {
      return index < queueStart + size;
    }

    @Override
    public T next() {
      return items[index++];
    }
  }


}
