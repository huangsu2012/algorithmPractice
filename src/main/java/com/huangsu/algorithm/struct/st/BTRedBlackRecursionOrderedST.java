package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.BTNodeRedBlackST;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 *
 *
 */
public class BTRedBlackRecursionOrderedST<Key, Value> extends
    AbstractSetCollectionOrderedBTRBRecursion<Key, Value, BTNodeRedBlackST<Key, Value>> implements
    OrderedST<Key, Value> {


  @Override
  public void put(Key key, Value value) {
    tree = insert(tree, key, value);
  }

  @Override
  public Value get(Key key) {
    BTNodeRedBlackST<Key, Value> node = get(key, tree);
    return node == null ? null : node.value;
  }

  @Override
  public Value delete(Key key) {
    return null;
  }

  @Override
  protected BTNodeRedBlackST<Key, Value> insertCreateNode(Key key, Value value) {
    return new BTNodeRedBlackST<>(key, value, 1);
  }

  @Override
  public Value deleteMin() {
    return null;
  }

  @Override
  public Value deleteMax() {
    return null;
  }
}
