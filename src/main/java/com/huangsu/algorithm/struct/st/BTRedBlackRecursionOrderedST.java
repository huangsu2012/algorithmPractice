package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 *
 * 红黑树有序符号表抽象类，主要提供节点抽象以及通用操作及实现
 */
public class BTRedBlackRecursionOrderedST<Key, Value> extends
    AbstractBTRecursionOrderedST<Key, Value, BTNodeRedBlack<Key, Value>> implements
    OrderedST<Key, Value> {


  @Override
  public void put(Key key, Value value) {
    tree = put(tree, key, value);
  }

  @SuppressWarnings("Duplicates")
  BTNodeRedBlack<Key, Value> put(BTNodeRedBlack<Key, Value> node, Key key, Value value) {
    if (node == null) {
      return new BTNodeRedBlack<>(key, value, 1, true);
    }
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
    if (compareVal == 0) {
      node.value = value;
    } else if (compareVal > 0) {
      node.left = put(node.left, key, value);
    } else {
      node.right = put(node.right, key, value);
    }
    if (!isRedNode(node.left) && isRedNode(node.right)) {
      node = rotateLeft(node);
    }
    if (isRedNode(node.left) && isRedNode(node.left.left)) {
      node = rotateRight(node);
    }
    if (isRedNode(node.left) && isRedNode(node.right)) {
      flipColors(node);
    }
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  boolean isRedNode(BTNodeRedBlack<Key, Value> node) {
    return node != null && node.red;
  }

  @Override
  BTNodeRedBlack<Key, Value> newNode(Key key, Value value) {
    return new BTNodeRedBlack<>(key, value, 1, true);
  }

  @Override
  public void delete(Key key) {

  }


  @Override
  public void deleteMin() {

  }

  @Override
  public void deleteMax() {

  }


  /**
   * 左旋操作，只有当节点h的右子节点为红色链接时才需要左旋,左旋相当于将较大的节点提升为子树根结点
   *
   * @param tree 需要左旋的节点
   * @return 左旋完成后的根结点
   */
  BTNodeRedBlack<Key, Value> rotateLeft(BTNodeRedBlack<Key, Value> tree) {
//    if (tree.right == null || !tree.right.red) {
//      return tree;
//    }
    BTNodeRedBlack<Key, Value> newTree = tree.right;//
    tree.right = newTree.left;
    newTree.left = tree;

    newTree.red = tree.red;
    tree.red = true;
    newTree.nodeCount = tree.nodeCount;//x只是取代了h节点的位置，所以节点数和原来的h节点一样
    tree.nodeCount = size(tree.left) + size(tree.right) + 1;
    return newTree;
  }

  /**
   * 右旋操作，只有h节点是红色链接且其左子节点也是红色链接时才需要右旋
   *
   * @param tree 需要右旋的节点
   * @return 右旋完成后的根结点
   */
  BTNodeRedBlack<Key, Value> rotateRight(BTNodeRedBlack<Key, Value> tree) {
//    if (tree.left == null || !tree.left.red) {
//      return tree;
//    }
    BTNodeRedBlack<Key, Value> newTree = tree.left;
    tree.left = newTree.right;
    newTree.right = tree;

    newTree.red = tree.red;
    tree.red = true;
    newTree.nodeCount = tree.nodeCount;
    tree.nodeCount = size(tree.left) + size(tree.right) + 1;
    return newTree;
  }

  /**
   * 颜色转换操作，只有node节点的左右子节点均为红色链接才需要进行此操作，这其实相当于将一个4-节点变为2个2-节点同时将中间节点移到父节点中
   *
   * @param node 需要进行颜色转换操作的节点
   */
  void flipColors(BTNodeRedBlack<Key, Value> node) {
//    if (node.left == null || !node.left.red || node.right == null || !node.right.red) {
//      return;
//    }
    node.red = true;
    node.left.red = false;
    node.right.red = false;
  }
}
