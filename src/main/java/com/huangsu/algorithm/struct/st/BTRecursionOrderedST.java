package com.huangsu.algorithm.struct.st;


import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.BTNodeST;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/28.
 *
 * 基于二叉查找树的有序符号表递归实现
 */
public class BTRecursionOrderedST<Key, Value> extends
    AbstractSetCollectionOrderedBTNodeRecursion<Key, Value, BTNodeST<Key, Value>> implements
    OrderedST<Key, Value> {

  public BTRecursionOrderedST() {
  }

  public BTRecursionOrderedST(Comparator<Key> comparator) {
    super(comparator);
  }

  @Override
  protected BTNodeST<Key, Value> insertCreateNode(Key key, Value value) {
    return new BTNodeST<>(key, value, 1);
  }


  /**
   * 递归实现
   *
   * @param key 键
   * @param value 值
   */
  @Override
  public void put(Key key, Value value) {
    tree = insert(tree, key, value);
  }

  @Override
  public Value get(Key key) {
    BTNodeST<Key, Value> node = get(key, tree);
    return node == null ? null : node.value;
  }

}
