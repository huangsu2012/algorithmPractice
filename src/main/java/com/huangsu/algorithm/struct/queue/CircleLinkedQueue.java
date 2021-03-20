package com.huangsu.algorithm.struct.queue;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2018/3/15.
 */
public class CircleLinkedQueue<T> implements Queue<T> {

  private class Node {

    T item;
    Node next;
  }

  private Node last;
  private int size;

  @Override
  public void enqueue(T item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    if (isEmpty()) {
      last.next = last;
    } else {
      Node first = oldLast.next;
      oldLast.next = last;
      last.next = first;
    }
    ++size;
  }

  @Override
  public T dequeue() {
    T item = last.next.item;
    if (last.next == last) {
      last = null;
    } else {
      last.next = last.next.next;
    }
    --size;
    return item;
  }

  @Override
  public T peek() {
    return last == null ? null : last.item;
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
    return new CircleLinkedQueueIterator();
  }

  private class CircleLinkedQueueIterator implements Iterator<T> {

    private Node current = last.next;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      T item = current.item;
      current = current.next;
      if (current == last.next) {
        current = null;
      }
      return item;
    }
  }
}
