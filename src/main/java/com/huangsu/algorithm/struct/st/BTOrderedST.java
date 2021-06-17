package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.BTNodeWithPST;

/**
 * Created by huangsu2012@gmail.com on 2021/2/25.
 *
 * 基于二叉查找树的有序符号表非递归实现
 */
public class BTOrderedST<Key, Value> extends
    AbstractSetCollectionOrderedBTNodeImpl<Key, Value, BTNodeWithPST<Key, Value>> implements
    OrderedST<Key, Value>
//    ThreadedST<Key>
{


  @Override
  protected BTNodeWithPST<Key, Value> insertCreateNode(BTNodeWithPST<Key, Value> parent, Key key,
      Value value) {
    return new BTNodeWithPST<>(parent, key, value, 1);
  }

  @Override
  public void put(Key key, Value value) {
    insert(key, value);
  }

  @Override
  public Value get(Key key) {
    BTNodeWithPST<Key, Value> node = get(key, tree);
    return node == null ? null : node.value;
  }

  @Override
  public Value delete(Key key) {
    BTNodeWithPST<Key, Value> node = deleteNode(key);
    return node == null ? null : node.value;
  }

  @Override
  public Value deleteMin() {
    BTNodeWithPST<Key, Value> node = deleteMinOrMax(true);
    return node == null ? null : node.value;
  }

  @Override
  public Value deleteMax() {
    BTNodeWithPST<Key, Value> node = deleteMinOrMax(false);
    return node == null ? null : node.value;
  }
}
