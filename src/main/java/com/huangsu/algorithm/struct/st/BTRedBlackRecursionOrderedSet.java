package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.BTNodeRedBlack;

/**
 * Created by huangsu2012@gmail.com on 2021/5/20.
 */
public class BTRedBlackRecursionOrderedSet<Key> extends
    AbstractSetCollectionOrderedBTRBRecursion<Key, Void, BTNodeRedBlack<Key>> implements
    OrderedSet<Key> {

  @Override
  protected BTNodeRedBlack<Key> insertCreateNode(Key key, Void aVoid) {
    return new BTNodeRedBlack<>(key, 1);
  }

  @Override
  public boolean add(Key key) {
    int oldSize = size(tree);
    insert(tree, key, null);
    return oldSize < size(tree);
  }

  @Override
  public boolean delete(Key key) {
    return false;
  }

  @Override
  public Key deleteMin() {
    return null;
  }

  @Override
  public Key deleteMax() {
    return null;
  }
}
