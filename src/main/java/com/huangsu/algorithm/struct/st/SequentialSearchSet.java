package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionSequentialSearch.NodeSet;

/**
 * Created by huangsu2012@gmail.com on 2021/5/27.
 */
public class SequentialSearchSet<Key> extends
    AbstractSetCollectionSequentialSearch<Key, Void, NodeSet<Key>> implements Set<Key> {

  @Override
  protected NodeSet<Key> insertCreateNode(Key key, Void aVoid, NodeSet<Key> prev,
      NodeSet<Key> next) {
    return new NodeSet<>(key, prev, next);
  }

  @Override
  public boolean add(Key key) {
    return insert(key, null);
  }

  @Override
  public boolean delete(Key key) {
    return deleteNode(key) != null;
  }
}
