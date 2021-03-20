package com.huangsu.algorithm.struct.queue;

import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2020/11/18.
 */
public class LinkedDeque<T> implements Deque<T> {

  private int size = 0;
  private DoubleLinkNode left;
  private DoubleLinkNode right;

  private class DoubleLinkNode {

    private T val;
    private DoubleLinkNode prev;
    private DoubleLinkNode next;

    public DoubleLinkNode(T val) {
      this.val = val;
    }
  }

  public LinkedDeque() {
    left = new DoubleLinkNode(null);
    right = new DoubleLinkNode(null);
    left.next = right;
    right.prev = left;
  }

  @Override
  public void pushLeft(T t) {
    if (left.val == null) {
      left.val = t;
    } else {
      DoubleLinkNode leftNext = left;
      left.prev = new DoubleLinkNode(t);
      left = left.prev;
      left.next = leftNext;
    }
    ++size;
  }

  @Override
  public void pushRight(T t) {
    if (right.val == null) {
      right.val = t;
    } else {
      DoubleLinkNode rightPrev = right;
      right.next = new DoubleLinkNode(t);
      right = right.next;
      right.prev = rightPrev;
    }
  }

  @Override
  public T popLeft() {
    T val = left.val;
    left.val = null;
    left = left.next;
    return val;
  }

  @Override
  public T popRight() {
    T val = right.val;
    right.val = null;
    right = right.prev;
    return val;
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
    return null;
  }
}
