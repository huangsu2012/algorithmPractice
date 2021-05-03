package com.huangsu.algorithm.struct.st;


import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/2/28.
 *
 * 基于二叉查找树的有序符号表递归实现
 */
public class BTRecursionOrderedST<Key, Value> extends
    AbstractBTRecursionOrderedST<Key, Value, BTNode<Key, Value>> implements
    OrderedST<Key, Value> {

  public BTRecursionOrderedST() {
  }

  public BTRecursionOrderedST(Comparator<Key> comparator) {
    super(comparator);
  }

  @Override
  public void deleteMin() {
    tree = deleteMin(tree);
  }

  private BTNode<Key, Value> deleteMin(BTNode<Key, Value> node) {
    if (node == null) {
      return null;
    }
    if (node.left == null) {
      return node.right;
    }
    node.left = deleteMin(node.left);
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  @Override
  public void deleteMax() {
    tree = deleteMax(tree);
  }

  private BTNode<Key, Value> deleteMax(BTNode<Key, Value> node) {
    if (node == null) {
      return null;
    }
    if (node.right == null) {
      return node.left;
    }
    node.right = deleteMin(node.right);
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  @Override
  public void delete(Key key) {
    tree = delete(key, tree);
  }

  private BTNode<Key, Value> delete(Key key, BTNode<Key, Value> node) {
    if (node == null) {
      return null;
    }
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
    if (compareVal > 0) {
      node.left = delete(key, node.left);
    } else if (compareVal < 0) {
      node.right = delete(key, node.right);
    } else {
      if (node.right == null) {
        return node.left;
      }
      if (node.left == null) {
        return node.right;
      }
      BTNode<Key, Value> t = node;
      node = min(t.right);
      node.left = t.left;
      node.right = deleteMin(t.right);
    }
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  /**
   * 递归实现
   *
   * @param key 键
   * @param value 值
   */
  @Override
  public void put(Key key, Value value) {
    tree = put(tree, key, value);
  }


  @Override
  BTNode<Key, Value> newNode(Key key, Value value) {
    return new BTNode<>(key, value, 1);
  }
}
