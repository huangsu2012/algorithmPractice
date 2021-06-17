package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.AbstractBTNodeRedBlack;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/20.
 *
 * 红黑树有序符号表抽象类，主要提供添加以及删除相关方法递归实现
 */
public abstract class AbstractSetCollectionOrderedBTRBRecursion<Key, Value, Node extends AbstractBTNodeRedBlack<Key, Node>> extends
    AbstractSetCollectionOrderedBTRecursion<Key, Node> {

  public AbstractSetCollectionOrderedBTRBRecursion() {
  }

  public AbstractSetCollectionOrderedBTRBRecursion(Comparator<Key> comparator) {
    super(comparator);
  }


  @SuppressWarnings({"Duplicates", "unchecked"})
  Node insert(Node node, Key key, Value value) {
    if (node == null) {
      return insertCreateNode(key, value);
    }
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
    if (compareVal == 0) {
      if (node instanceof BTNodeRedBlackST) {
        ((BTNodeRedBlackST<Key, Value>) node).value = value;
      }
    } else if (compareVal > 0) {
      node.left = insert(node.left, key, value);
    } else {
      node.right = insert(node.right, key, value);
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


  private Node deleteMin(Node node) {
    if (node == null || node.left == null) {
      return null;
    }
    if (isRedNode(node.left.left)) {
      node = node.left;
    } else {
      Node brotherNode = node.right;
      if (isRedNode(brotherNode)) {
        brotherNode = brotherNode.left;
      }
    }
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }


  protected boolean isRedNode(Node node) {
    return node != null && node.red;
  }

  /**
   * 左旋操作，只有当节点h的右子节点为红色链接时才需要左旋,左旋相当于将较大的节点提升为子树根结点
   *
   * @param tree 需要左旋的节点
   * @return 左旋完成后的根结点
   */
  protected Node rotateLeft(Node tree) {
//    if (tree.right == null || !tree.right.red) {
//      return tree;
//    }
    Node newTree = tree.right;//
    tree.right = newTree.left;
    newTree.left = tree;

    newTree.red = tree.red;
    tree.red = true;
    newTree.nodeCount = tree.nodeCount;//newTree只是取代了tree节点的位置，所以节点数和原来的tree节点一样
    tree.nodeCount = size(tree.left) + size(tree.right) + 1;
    return newTree;
  }

  /**
   * 右旋操作，只有h节点是红色链接且其左子节点也是红色链接时才需要右旋
   *
   * @param tree 需要右旋的节点
   * @return 右旋完成后的根结点
   */
  protected Node rotateRight(Node tree) {
//    if (tree.left == null || !tree.left.red) {
//      return tree;
//    }
    Node newTree = tree.left;
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
  protected void flipColors(Node node) {
//    if (node.left == null || !node.left.red || node.right == null || !node.right.red) {
//      return;
//    }
    node.red = true;
    node.left.red = false;
    node.right.red = false;
  }


  protected abstract Node insertCreateNode(Key key, Value value);

}
