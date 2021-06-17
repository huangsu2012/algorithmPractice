package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.BTNode;

/**
 * Created by huangsu2012@gmail.com on 2021/5/20.
 */
public class BTRecursionOrderedSet<Key> extends
    AbstractSetCollectionOrderedBTNodeRecursion<Key, Void, BTNode<Key>> implements
    OrderedSet<Key> {

  @Override
  protected BTNode<Key> insertCreateNode(Key key, Void aVoid) {
    return new BTNode<>(key, 1);
  }

  @Override
  public boolean add(Key key) {
    int oldSize = size(tree);
    tree = insert(tree, key, null);
    return oldSize < size(tree);
  }

  @Override
  public Key deleteMin() {
    BTNode<Key> node = deleteMinNode();
    return node == null ? null : node.key;
  }

  @Override
  public Key deleteMax() {
    BTNode<Key> node = deleteMaxNode();
    return node == null ? null : node.key;
  }

  @Override
  public boolean delete(Key key) {
    BTNode<Key> node = deleteNode(key);
    return node != null;
  }
}
