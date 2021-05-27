package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.AbstractBTNode;
import com.huangsu.algorithm.util.SortUtils;
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


  @Override
  public void deleteMin() {
    tree = deleteMin(tree);
  }

  private Node deleteMin(Node node) {
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

  private Node deleteMax(Node node) {
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

  private Node delete(Key key, Node node) {
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
      Node t = node;
      node = min(t.right);
      node.left = t.left;
      node.right = deleteMin(t.right);
    }
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  protected abstract Node insertCreateNode(Key key, Value value);
}
