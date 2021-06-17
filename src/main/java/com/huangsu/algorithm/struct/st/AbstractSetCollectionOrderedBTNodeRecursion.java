package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.AbstractBTNode;
import com.huangsu.algorithm.util.SortUtils;
import com.huangsu.algorithm.util.ValueHolder;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/20.
 *
 * 二叉查找树添加、删除递归实现实现抽象
 */
public abstract class AbstractSetCollectionOrderedBTNodeRecursion<Key, Value, Node extends AbstractBTNode<Key, Node>> extends
    AbstractSetCollectionOrderedBTRecursion<Key, Node> {

  public AbstractSetCollectionOrderedBTNodeRecursion() {
  }

  public AbstractSetCollectionOrderedBTNodeRecursion(Comparator<Key> comparator) {
    super(comparator);
  }

  @SuppressWarnings("unchecked")
  protected Node insert(Node node, Key key, Value value) {
    if (node == null) {
      return insertCreateNode(key, value);
    }
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
    if (compareVal == 0) {
      if (node instanceof AbstractBTNodeST) {
        ((AbstractBTNodeST<Key, Value, Node>) node).value = value;
      }
    } else if (compareVal > 0) {
      node.left = insert(node.left, key, value);
    } else {
      node.right = insert(node.right, key, value);
    }
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }


  protected Node deleteMinNode() {
    ValueHolder<Node> delNode = new ValueHolder<>();
    tree = deleteMinNode(tree, delNode);
    return delNode.value;
  }

  private Node deleteMinNode(Node node, ValueHolder<Node> delNode) {
    if (node == null) {
      return null;
    }
    if (node.left == null) {
      if (delNode != null) {
        delNode.value = node;
      }
      return node.right;
    }
    node.left = deleteMinNode(node.left, delNode);
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  protected Node deleteMaxNode() {
    ValueHolder<Node> delNode = new ValueHolder<>();
    tree = deleteMaxNode(tree, delNode);
    return delNode.value;
  }

  private Node deleteMaxNode(Node node, ValueHolder<Node> delNode) {
    if (node == null) {
      return null;
    }
    if (node.right == null) {
      if (delNode != null) {
        delNode.value = node;
      }
      return node.left;
    }
    node.right = deleteMaxNode(node.right, delNode);
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }


  protected Node deleteNode(Key key) {
    ValueHolder<Node> delNode = new ValueHolder<>();
    tree = deleteNode(key, tree, delNode);
    return delNode.value;
  }

  private Node deleteNode(Key key, Node node, ValueHolder<Node> delNode) {
    if (node == null) {
      return null;
    }
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
    if (compareVal > 0) {
      node.left = deleteNode(key, node.left, delNode);
    } else if (compareVal < 0) {
      node.right = deleteNode(key, node.right, delNode);
    } else {
      delNode.value = node;
      if (node.right == null) {
        return node.left;
      }
      if (node.left == null) {
        return node.right;
      }
      Node t = node;
      node = min(t.right);
      node.left = t.left;
      node.right = deleteMinNode(t.right, null);
    }
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  protected abstract Node insertCreateNode(Key key, Value value);
}
