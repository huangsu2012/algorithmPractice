package com.huangsu.algorithm.struct.list;

import com.huangsu.algorithm.util.ArrayUtil;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 *
 * 跳表
 */
public class LinkedSkipList<T extends Comparable<T>> implements SkipList<T> {

  private class Node {

    private T item;
    private Node[] forwards;
  }


  private Node head;
  private int size;


  @Override
  public boolean add(T item) {
    Node node = new Node();
    node.item = item;
    if (head == null) {
      head = node;
      head.forwards = ArrayUtil.newInstance(Node.class, 1);
      head.forwards[0] = head;
    } else {
      if (item.compareTo(head.forwards[head.forwards.length - 1].item) < 0) {
        node.forwards = ArrayUtil.newInstance(Node.class, head.forwards.length);


      } else {
        for (int i = head.forwards.length - 1; i > -1; i--) {
          Node forward = head.forwards[i];
        }
      }
    }
    ++size;
    return true;
  }

  @Override
  public int rank(T item) {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public Iterator<T> iterator() {
    return null;
  }
}
