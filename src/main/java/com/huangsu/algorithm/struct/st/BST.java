package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/2/25.
 *
 * 基于二叉查找树的有序符号表非递归实现
 */
public class BST<Key extends Comparable<Key>, Value> implements OrderedST<Key, Value> {


  private Node tree;

  @Override
  public Key min() {
    Node node = min(tree);
    return node != null ? node.key : null;
  }

  private Node min(Node tree) {
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
      compareVal = SortUtils.compareTo(node.key, key);
      if (compareVal == 0) {
        return node.key;
      } else if (compareVal > 0) {
        node = node.left;
      } else {
        if (node.right != null) {
          compareVal = SortUtils.compareTo(node.right.key, key);
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
      compareVal = SortUtils.compareTo(node.key, key);
      if (compareVal == 0) {
        return node.key;
      } else if (compareVal < 0) {
        node = node.right;
      } else {
        if (node.left != null) {
          compareVal = SortUtils.compareTo(node.left.key, key);
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
      compareVal = SortUtils.compareTo(node.key, key);
      if (compareVal == 0) {
        rank += size(node.left) + 1;
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
      Node loNode = tree;
      Node hiNode = tree;
      while (true) {
        int loCompare = lo == null ? 0 : SortUtils.compareTo(lo, loNode.key);//查找下限为空意味着没有下限
        if (loCompare > 0 || loNode.left == null) {
          break;
        }
        loNode = loNode.left;
      }
      while (true) {
        int highCompare = hi == null ? 1 : SortUtils.compareTo(hi, hiNode.key);//查找上限为空意味着没有上限
        if (highCompare < 0 || hiNode.right == null) {
          break;
        }
        hiNode = hiNode.right;
      }
      while (loNode != null) {
        if (loNode.left != null) {
          queue.enqueue(loNode.left.key);
        }
        if (loNode.right != null) {
          queue.enqueue(loNode.right.key);
        }
        if (loNode.parent == null) {
          queue.enqueue(loNode.key);
        }
        loNode = loNode.parent;
      }

    }
    return queue;
  }


  @Override
  public void put(Key key, Value value) {
    if (tree == null) {
      tree = new Node(key, value, null, 1);
      return;
    }
    Node node = tree;
    int compareVal;
    while (true) {
      compareVal = SortUtils.compareTo(node.key, key);
      if (compareVal == 0) {
        node.value = value;
        return;
      } else if (compareVal > 0) {
        if (node.left == null) {
          node.left = new Node(key, value, node, 1);
          break;
        }
        node = node.left;
      } else {
        if (node.right == null) {
          node.right = new Node(key, value, node, 1);
          break;
        }
        node = node.right;
      }
    }
    while (node != null) {
      node.nodeCount = size(node.left) + size(node.right) + 1;
      node = node.parent;
    }
  }

  @Override
  public void deleteMin() {
    tree = deleteMin(tree);
  }

  private Node deleteMin(Node tree) {
    if (tree == null) {
      return null;
    }
    Node node = tree;
    while (node.left != null) {
      node = node.left;
    }
    if (node.parent != null) {
      node.parent.left = node.right;
    } else {
      tree = node.right;
    }
    while (node != null) {
      node.nodeCount = size(node.left) + size(node.right) + 1;
      node = node.parent;
    }
    return tree;
  }

  @Override
  public void deleteMax() {
    tree = deleteMax(tree);
  }

  private Node deleteMax(Node tree) {
    if (tree == null) {
      return null;
    }
    Node node = tree;
    while (node.right != null) {
      node = node.right;
    }
    if (node.parent != null) {
      node.parent.right = node.left;
    } else {
      tree = node.left;
    }
    while (node != null) {
      node.nodeCount = size(node.left) + size(node.right) + 1;
      node = node.parent;
    }
    return tree;
  }

  @Override
  public void delete(Key key) {
    tree = delete(key, tree);
  }

  private Node delete(Key key, Node tree) {
    if (tree == null) {
      return null;
    }
    Node node = tree;
    int compareVal;
    while (node != null) {
      compareVal = SortUtils.compareTo(node.key, key);
      if (compareVal == 0) {
        if (node.right == null) {
          if (node.parent == tree.parent) {
            tree = node.left;
          } else {
            if (node.left != null) {
              node.left.parent = node.parent;
            }
            if (node.parent.left == node) {
              node.parent.left = node.left;
            } else {
              node.parent.right = node.left;
            }
          }
          break;
        }
        if (node.left == null) {
          if (node.parent == tree.parent) {
            tree = node.right;
          } else {
            node.right.parent = node.parent;
            if (node.parent.left == node) {
              node.parent.left = node.right;
            } else {
              node.parent.right = node.right;
            }
          }
          break;
        }
        Node t = node;
        node = min(t.right);
        node.left = t.left;
        node.right = deleteMin(t.right);
        node.parent = t.parent;
        node.left.parent = node;
        if (node.right != null) {
          node.right.parent = node;
        }
        if (node.parent == tree.parent) {
          tree = node;
        } else {
          if (node.parent.left == t) {
            node.parent.left = node;
          } else {
            node.parent.right = node;
          }
        }
        break;
      } else if (compareVal > 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
    while (node != null) {
      node.nodeCount = size(node.left) + size(node.right) + 1;
      node = node.parent;
    }
    return tree;
  }

  @Override
  public Value get(Key key) {
    Node node = tree;
    int compareVal;
    while (node != null) {
      compareVal = SortUtils.compareTo(node.key, key);
      if (compareVal == 0) {
        return node.value;
      } else if (compareVal > 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
    return null;
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
    /**
     * 用于插入删除时更新nodeCount
     */
    private Node parent;
    private Node left;
    private Node right;

    public Node(Key key, Value value, Node parent, int nodeCount) {
      this.key = key;
      this.value = value;
      this.parent = parent;
      this.nodeCount = nodeCount;
    }
  }
}
