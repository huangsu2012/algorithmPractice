package com.huangsu.algorithm.struct.st;


import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/2/28.
 *
 * 基于二叉查找树的有序符号表递归实现
 */
public class BSTRecursion<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {

  private Node tree;

  @Override
  public Key min() {
    Node node = min(tree);
    return node == null ? null : node.key;
  }

  private Node min(Node node) {
    if (node == null) {
      return null;
    }
    if (node.left == null) {
      return node;
    }
    return min(node.left);
  }

  @Override
  public Key max() {
    Node max = max(tree);
    return max == null ? null : max.key;
  }

  private Node max(Node node) {
    if (node == null) {
      return null;
    }
    if (node.right == null) {
      return node;
    }
    return max(node.right);
  }


  @Override
  public Key floor(Key key) {
    Node node = floor(key, tree);
    if (node == null) {
      return null;
    }
    return node.key;
  }

  private Node floor(Key key, Node node) {
    if (node == null) {
      return null;
    }
    int compareVal = SortUtils.compareTo(node.key, key);
    if (compareVal == 0) {
      return node;
    }
    if (compareVal > 0) {
      return floor(key, node.left);
    }
    Node t = floor(key, node.right);
    if (t != null) {
      return t;
    } else {
      return node;
    }
  }

  @Override
  public Key ceiling(Key key) {
    Node node = ceiling(key, tree);
    return node == null ? null : node.key;
  }

  private Node ceiling(Key key, Node node) {
    if (node == null) {
      return null;
    }
    int compareVal = SortUtils.compareTo(node.key, key);
    if (compareVal == 0) {
      return node;
    }
    if (compareVal < 0) {
      return ceiling(key, node.right);
    }
    Node t = ceiling(key, node.left);
    if (t != null) {
      return t;
    } else {
      return node;
    }
  }


  @Override
  public int rank(Key key) {
    return rank(key, tree);
  }

  private int rank(Key key, Node node) {
    if (node == null) {
      return 0;
    }
    int compareVal = SortUtils.compareTo(node.key, key);
    if (compareVal > 0) {
      return rank(key, node.left);
    } else if (compareVal < 0) {
      return 1 + size(node.left) + rank(key, node.right);
    } else {
      return size(node.left);
    }
  }

  @Override
  public Key select(int k) {
    Node node = select(k, tree);
    return node == null ? null : node.key;
  }

  private Node select(int k, Node node) {
    if (node == null) {
      return null;
    }
    int sizeL = size(node.left);
    if (sizeL == k) {
      return node;
    } else if (sizeL < k) {
      return select(k - sizeL - 1, node.right);
    } else {
      return select(k, node.left);
    }
  }

  @Override
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new LinkedQueue<>();
    keys(lo, hi, tree, queue);
    return queue;
  }

  private void keys(Key lo, Key hi, Node node, Queue<Key> queue) {
    if (node == null) {
      return;
    }
    int loCompare = lo == null ? 0 : SortUtils.compareTo(lo, node.key);//查找下限为空意味着没有下限
    if (loCompare > 0) {
      return;
    }
    keys(lo, hi, node.left, queue);
    queue.enqueue(node.key);
    int highCompare = hi == null ? 1 : SortUtils.compareTo(hi, node.key);//查找上限为空意味着没有上限
    if (highCompare < 0) {
      return;
    }
    keys(lo, hi, node.right, queue);
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
    int compareVal = SortUtils.compareTo(node.key, key);
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

  private Node put(Node node, Key key, Value value) {
    if (node == null) {
      return new Node(key, value, 1);
    }
    int compareVal = SortUtils.compareTo(node.key, key);
    if (compareVal == 0) {
      node.value = value;
    } else if (compareVal > 0) {
      node.left = put(node.left, key, value);
    } else {
      node.right = put(node.right, key, value);
    }
    node.nodeCount = size(node.left) + size(node.right);
    return node;
  }

  @Override
  public Value get(Key key) {
    return get(key, tree);
  }

  private Value get(Key key, Node node) {
    if (node == null) {
      return null;
    }
    int compareVal = SortUtils.compareTo(node.key, key);
    if (compareVal == 0) {
      return node.value;
    } else if (compareVal > 0) {
      return get(key, node.left);
    } else {
      return get(key, node.right);
    }
  }

  @Override
  public boolean isEmpty() {
    return size(tree) == 0;
  }

  @Override
  public int size() {
    return size(tree);
  }

  private int size(Node node) {
    return node == null ? 0 : node.nodeCount;
  }

  private class Node {

    private Key key;
    private Value value;
    /**
     * 以该节点为根的子树中的节点总数
     */
    private int nodeCount;
    private Node left;
    private Node right;

    public Node(Key key, Value value, int nodeCount) {
      this.key = key;
      this.value = value;
      this.nodeCount = nodeCount;
    }
  }
}
