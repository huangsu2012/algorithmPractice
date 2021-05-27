package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionSequentialSearch.Node;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/27.
 *
 * 基于链表实现抽象
 */
public abstract class AbstractSetCollectionSequentialSearch<Key, Value, N extends Node<Key, N>> extends
    AbstractSetCollection<Key> {

  protected N head;
  protected int size;

  @Override
  public void delete(Key key) {
    for (N node = head; node != null; node = node.next) {
      if (node.key.equals(key)) {
        node.prev.next = node.next;
        --size;
        break;
      }
    }
  }

  @Override
  public boolean contains(Key key) {
    for (N node = head; node != null; node = node.next) {
      if (node.key.equals(key)) {
        return true;
      }
    }
    return false;
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


  @SuppressWarnings("unchecked")
  protected boolean insert(Key key, Value value) {
    for (N node = head; node != null; node = node.next) {
      if (node.key.equals(key)) {
        if (node instanceof NodeST) {
          ((NodeST<Key, Value>) node).value = value;
        }
        return false;
      }
    }
    head = insertCreateNode(key, value, null, head);
    ++size;
    return true;
  }

  protected abstract N insertCreateNode(Key key, Value value, N prev, N next);

  private class SequentialSearchSTKeyIterable implements Iterable<Key> {

    @Override
    public Iterator<Key> iterator() {
      return new SequentialSearchSTKeyIterator();
    }
  }

  private class SequentialSearchSTKeyIterator implements Iterator<Key> {

    private N current = head;

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

  protected static class Node<Key, N extends Node<Key, N>> extends SetNode<Key> {

    protected N prev;
    protected N next;

    protected Node(Key key, N prev, N next) {
      super(key);
      this.prev = prev;
      this.next = next;
    }
  }

  protected static class NodeSet<Key> extends Node<Key, NodeSet<Key>> {

    protected NodeSet(Key key,
        NodeSet<Key> prev,
        NodeSet<Key> next) {
      super(key, prev, next);
    }
  }

  protected static class NodeST<Key, Value> extends Node<Key, NodeST<Key, Value>> {

    protected Value value;

    protected NodeST(Key key, Value value,
        NodeST<Key, Value> prev,
        NodeST<Key, Value> next) {
      super(key, prev, next);
      this.value = value;
    }
  }
}
