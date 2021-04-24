package com.huangsu.algorithm.struct.bag;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/23.
 */
public class LinkedBag<T> implements Bag<T> {

  private class Node {

    private T item;
    private Node next;

    public Node(T item, Node next) {
      this.item = item;
      this.next = next;
    }
  }

  private Node head;
  private int size;

  @Override
  public void add(T item) {
    head = new Node(item, head);
    ++size;
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
    return new LinkedBagIterator();
  }

  private class LinkedBagIterator implements Iterator<T> {

    private Node current = head;

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
