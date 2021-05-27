package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionSequentialSearch.NodeST;

/**
 * Created by huangsu2012@gmail.com on 2021/2/23.
 *
 * 基于链表的符号表实现
 */
public class SequentialSearchST<Key, Value> extends
    AbstractSetCollectionSequentialSearch<Key, Value, NodeST<Key, Value>> implements
    ST<Key, Value> {


  @Override
  public void put(Key key, Value value) {
    insert(key, value);
  }


  @Override
  public Value get(Key key) {
    for (NodeST<Key, Value> node = head; node != null; node = node.next) {
      if (node.key.equals(key)) {
        return node.value;
      }
    }
    return null;
  }


  @Override
  protected NodeST<Key, Value> insertCreateNode(Key key, Value value, NodeST<Key, Value> prev,
      NodeST<Key, Value> next) {
    return new NodeST<>(key, value, prev, next);
  }
}
