package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.AbstractBTNode;
import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 *
 * 二叉查找树键查询相关方法实现抽象
 */
abstract class AbstractSetCollectionOrderedBTImpl<Key, Node extends AbstractBTNode<Key, Node>> extends
    AbstractSetCollectionOrderedBT<Key, Node> {

  public AbstractSetCollectionOrderedBTImpl() {
  }

  public AbstractSetCollectionOrderedBTImpl(Comparator<Key> comparator) {
    super(comparator);
  }

  @Override
  public Key min() {
    Node node = min(tree);
    return node != null ? node.key : null;
  }

  Node min(Node tree) {
    Node node = tree;
    while (node != null && node.left != null) {
      node = node.left;
    }
    return node;
  }

  @Override
  public Key max() {
    Node node = tree;
    while (node != null && node.right != null) {
      node = node.right;
    }
    return node != null ? node.key : null;
  }

  @Override
  public Key floor(Key key) {
    Node node = tree;
    int compareVal;
    while (node != null) {
      compareVal = SortUtils.compareTo(node.key, key, keyComparator);
      if (compareVal == 0) {
        return node.key;
      } else if (compareVal > 0) {
        node = node.left;
      } else {
        if (node.right != null) {
          compareVal = SortUtils.compareTo(node.right.key, key, keyComparator);
          if (compareVal <= 0) {
            node = node.right;
            continue;
          }
        }
        return node.key;
      }
    }
    return null;
  }

  @Override
  public Key ceiling(Key key) {
    Node node = tree;
    int compareVal;
    while (node != null) {
      compareVal = SortUtils.compareTo(node.key, key, keyComparator);
      if (compareVal == 0) {
        return node.key;
      } else if (compareVal < 0) {
        node = node.right;
      } else {
        if (node.left != null) {
          compareVal = SortUtils.compareTo(node.left.key, key, keyComparator);
          if (compareVal >= 0) {
            node = node.left;
            continue;
          }
        }
        return node.key;
      }
    }
    return null;
  }

  @Override
  public int rank(Key key) {
    Node node = tree;
    int rank = 0;
    int compareVal;
    while (node != null) {
      compareVal = SortUtils.compareTo(node.key, key, keyComparator);
      if (compareVal == 0) {
        rank += size(node.left);
        break;
      } else if (compareVal < 0) {
        rank += size(node.left) + 1;
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return rank;
  }


  @Override
  public Key select(int k) {
    Node node = tree;
    int kSel = k;
    int sizeL;
    while (node != null) {
      sizeL = size(node.left);
      if (sizeL == kSel) {
        break;
      } else if (sizeL < kSel) {
        kSel = kSel - sizeL - 1;
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return node == null ? null : node.key;
  }


  @Override
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new LinkedQueue<>();
    if (tree != null) {
      Node node = tree;
      Stack<Node> stack = new LinkedStack<>();
      while (node != null) {
        boolean traverseRightNode = shouldTraverseRightNode(lo, hi, node, stack);
        if (traverseRightNode) {
          node = stack.pop();
          queue.enqueue(node.key);
          node = node.right;
        } else {
          node = node.left;
        }
      }
    }
    return queue;
  }

  @Override
  public Iterable<Key> keys() {
    return keys(null, null);
  }


  protected Node get(Key key, Node tree) {
    Node node = tree;
    int compareVal;
    while (node != null) {
      compareVal = SortUtils.compareTo(node.key, key, keyComparator);
      if (compareVal == 0) {
        return node;
      } else if (compareVal > 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
    return null;
  }

  @Override
  public boolean contains(Key key) {
    Node node = tree;
    int compareVal;
    while (node != null) {
      compareVal = SortUtils.compareTo(node.key, key, keyComparator);
      if (compareVal == 0) {
        return true;
      } else if (compareVal > 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
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

  int size(Node node) {
    return node == null ? 0 : node.nodeCount;
  }

  @Override
  public int size(Key lo, Key hi) {
    int s = 0;
    if (tree != null) {
      Node node = tree;
      Stack<Node> stack = new LinkedStack<>();
      while (node != null) {
        boolean traverseRightNode = shouldTraverseRightNode(lo, hi, node, stack);
        if (traverseRightNode) {
          node = stack.pop();
          node = node.right;
          ++s;
        } else {
          node = node.left;
        }
      }
    }
    return s;
  }

  /**
   * 在中序遍历某个范围的键时用于决定是否需要从堆栈中弹出节点并遍历其右子节点
   *
   * @param lo 键范围的下限，包含
   * @param hi 键范围的上限，包含
   * @param node 当前访问的节点
   * @param stack 保存需要遍历的节点的堆栈
   * @return 如果需要访问节点的右子节点，返回true
   */
  boolean shouldTraverseRightNode(Key lo, Key hi, Node node,
      Stack<Node> stack) {
    if (node == null) {
      return true;
    }
    int loCompare =
        lo == null ? 0 : SortUtils.compareTo(lo, node.key, keyComparator);//查找下限为空意味着没有下限
    int highCompare =
        hi == null ? 1 : SortUtils.compareTo(hi, node.key, keyComparator);//查找上限为空意味着没有上限
    boolean traverseRight = false;
    if (loCompare <= 0 && highCompare >= 0) {
      stack.push(node);
    } else {
      traverseRight = true;
    }
    node = node.left;
    traverseRight = (traverseRight || (node == null)) && !stack.isEmpty();
    return traverseRight;
  }
}
