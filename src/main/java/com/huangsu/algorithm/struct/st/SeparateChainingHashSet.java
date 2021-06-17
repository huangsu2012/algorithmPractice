package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionSeparateChainingHash.NodeHashSet;

/**
 * Created by huangsu2012@gmail.com on 2021/5/27.
 */
public class SeparateChainingHashSet<Key> extends
    AbstractSetCollectionSeparateChainingHash<Key, Void, NodeHashSet<Key>> implements Set<Key> {

  public SeparateChainingHashSet() {
  }

  public SeparateChainingHashSet(int initCap) {
    super(initCap);
  }

  public SeparateChainingHashSet(int initCap, float loadFactor) {
    super(initCap, loadFactor);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected NodeHashSet<Key>[] createNewTab(int tableLength) {
    return (NodeHashSet<Key>[]) new NodeHashSet[tableLength];
  }

  @Override
  protected NodeHashSet<Key> insertCreateNode(Key key, Void aVoid, int hash) {
    return new NodeHashSet<>(key, hash);
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
