package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.BTNodeWithP;

/**
 * Created by huangsu2012@gmail.com on 2021/5/20.
 */
public class BTOrderedSet<Key> extends
    AbstractSetCollectionOrderedBTNodeImpl<Key, Void, BTNodeWithP<Key>> implements OrderedSet<Key> {

  @Override
  protected BTNodeWithP<Key> insertCreateNode(BTNodeWithP<Key> parent, Key key, Void aVoid) {
    return new BTNodeWithP<>(parent, key, 1);
  }

  @Override
  public boolean add(Key key) {
    int oldSize = size(tree);
    insert(key, null);
    return oldSize < size(tree);
  }

  @Override
  public boolean delete(Key key) {
    BTNodeWithP<Key> node = deleteNode(key);
    return node != null;
  }

  @Override
  public Key deleteMin() {
    BTNodeWithP<Key> node = deleteMinOrMax(true);
    return node == null ? null : node.key;
  }

  @Override
  public Key deleteMax() {
    BTNodeWithP<Key> node = deleteMinOrMax(false);
    return node == null ? null : node.key;
  }
}
