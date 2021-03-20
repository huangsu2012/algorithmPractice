package com.huangsu.algorithm.struct.stack;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2018/3/10.
 */
public class LinkedStack<T> implements Stack<T> {

  private class Node {

    T item;
    Node next;
  }

  private Node first;
  private int size;

  private class StackIterator implements Iterator<T> {

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

  @Override
  public Iterator<T> iterator() {
    return new StackIterator();
  }

  public void push(T item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    ++size;
  }

  public T pop() {
    T item = first.item;
    first = first.next;
    --size;
    return item;
  }

  public T peek() {
    return first.item;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return first == null;
  }

}
