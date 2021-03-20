package com.huangsu.algorithm.struct.st;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/23.
 *
 * 基于链表的符号表实现
 */
public class SequentialSearchST<Key, Value> implements ST<Key, Value> {

  private class Node {

    private Key key;
    private Value value;
    private Node prev;
    private Node next;

    public Node(Key key, Value value, Node prev,
        Node next) {
      this.key = key;
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }

  private Node head;
  private int size;

  @Override
  public void put(Key key, Value value) {
    for (Node node = head; node != null; node = node.next) {
      if (node.key.equals(key)) {
        node.value = value;
        return;
      }
    }
    head = new Node(key, value, null, head);
    ++size;
  }


  @Override
  public Value get(Key key) {
    for (Node node = head; node != null; node = node.next) {
      if (node.key.equals(key)) {
        return node.value;
      }
    }
    return null;
  }

  @Override
  public void delete(Key key) {
    for (Node node = head; node != null; node = node.next) {
      if (node.key.equals(key)) {
        node.prev.next = node.next;
        --size;
        break;
      }
    }
  }

  @Override
  public Iterable<Key> keys() {
    return new SequentialSearchSTKeyIterable();
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  private class SequentialSearchSTKeyIterable implements Iterable<Key> {

    @Override
    public Iterator<Key> iterator() {
      return new SequentialSearchSTKeyIterator();
    }
  }

  private class SequentialSearchSTKeyIterator implements Iterator<Key> {

    private Node current = head;

    @Override
    public boolean hasNext() {
      return current.next != null;
    }

    @Override
    public Key next() {
      Key item = current.key;
      current = current.next;
      return item;
    }
  }
}
