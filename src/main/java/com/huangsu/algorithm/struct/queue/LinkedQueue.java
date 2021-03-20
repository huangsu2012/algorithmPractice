package com.huangsu.algorithm.struct.queue;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2018/3/10.
 */
public class LinkedQueue<T> implements Queue<T> {

  @Override
  public Iterator<T> iterator() {
    return new LinkedQueueIterator();
  }

  private class Node {

    T item;
    Node next;
  }

  private Node first;
  private Node last;
  private int size;

  public void enqueue(T item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
    ++size;
  }

  public T dequeue() {
    T item = first.item;
    first = first.next;
    --size;
    if (isEmpty()) {
      last = null;
    }
    return item;
  }

  @Override
  public T peek() {
    return first == null ? null : first.item;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private class LinkedQueueIterator implements Iterator<T> {

    private Node current = first;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      T item = current.item;
      current = current.next;
      return item;
    }
  }

}
