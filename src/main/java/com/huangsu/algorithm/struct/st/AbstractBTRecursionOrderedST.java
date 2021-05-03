package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 *
 * 二叉查找树递归实现抽象
 */
abstract class AbstractBTRecursionOrderedST<Key, Value, Node extends AbstractBTNode<Key, Value, Node>> implements
    OrderedST<Key, Value> {


  Node tree;
  final Comparator<Key> keyComparator;

  public AbstractBTRecursionOrderedST() {
    this(null);
  }

  public AbstractBTRecursionOrderedST(Comparator<Key> comparator) {
    this.keyComparator = comparator;
  }

  @Override
  public Key min() {
    Node node = min(tree);
    return node == null ? null : node.key;
  }

  Node min(Node node) {
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

  Node max(Node node) {
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
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
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
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
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
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
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

  @Override
  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedQueue<>();
    keys(null, null, tree, new LinkedQueue<>());
    return queue;
  }

  private void keys(Key lo, Key hi, Node node, Queue<Key> queue) {
    if (node == null) {
      return;
    }
    int loCompare =
        lo == null ? 0 : SortUtils.compareTo(lo, node.key, keyComparator);//查找下限为空意味着没有下限
    int highCompare =
        hi == null ? 1 : SortUtils.compareTo(hi, node.key, keyComparator);//查找上限为空意味着没有上限
    if (loCompare < 0) {
      keys(lo, hi, node.left, queue);
    }
    if (loCompare <= 0 && highCompare >= 0) {
      queue.enqueue(node.key);
    }
    if (highCompare > 0) {
      keys(lo, hi, node.right, queue);
    }
  }


  @Override
  public Value get(Key key) {
    return get(key, tree);
  }

  private Value get(Key key, Node node) {
    if (node == null) {
      return null;
    }
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
    if (compareVal == 0) {
      return node.value;
    } else if (compareVal > 0) {
      return get(key, node.left);
    } else {
      return get(key, node.right);
    }
  }

  @Override
  public boolean contains(Key key) {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return size(tree) == 0;
  }

  @Override
  public int size() {
    return size(tree);
  }

  @Override
  public int size(Key lo, Key hi) {
    return size(lo, hi, tree);
  }


  Node put(Node node, Key key, Value value) {
    if (node == null) {
      return newNode(key, value);
    }
    int compareVal = SortUtils.compareTo(node.key, key, keyComparator);
    if (compareVal == 0) {
      node.value = value;
    } else if (compareVal > 0) {
      node.left = put(node.left, key, value);
    } else {
      node.right = put(node.right, key, value);
    }
    node.nodeCount = size(node.left) + size(node.right) + 1;
    return node;
  }

  abstract Node newNode(Key key, Value value);

  private int size(Key lo, Key hi, Node node) {
    if (node == null) {
      return 0;
    }
    int loCompare =
        lo == null ? 0 : SortUtils.compareTo(lo, node.key, keyComparator);//查找下限为空意味着没有下限
    int highCompare =
        hi == null ? 1 : SortUtils.compareTo(hi, node.key, keyComparator);//查找上限为空意味着没有上限
    int s = 0;
    if (loCompare < 0) {
      s += size(lo, hi, node.left);
    }
    if (loCompare <= 0 && highCompare >= 0) {
      s++;
    }
    if (highCompare > 0) {
      s += size(lo, hi, node.right);
    }
    return s;
  }

  int size(Node node) {
    return node == null ? 0 : node.nodeCount;
  }
}
