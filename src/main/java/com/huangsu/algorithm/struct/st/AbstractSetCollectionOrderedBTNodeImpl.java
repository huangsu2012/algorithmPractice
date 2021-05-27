package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.AbstractBTNodeWithP;
import com.huangsu.algorithm.util.SortUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/5/20.
 */
public abstract class AbstractSetCollectionOrderedBTNodeImpl<Key, Value, Node extends AbstractBTNodeWithP<Key, Node>> extends
    AbstractSetCollectionOrderedBTImpl<Key, Node> {


  @SuppressWarnings("unchecked")
  protected void insert(Key key, Value value) {
    if (tree == null) {
      tree = insertCreateNode(null, key, value);
      return;
    }
    Node node = tree;
    int compareVal;
    while (true) {
      compareVal = SortUtils.compareTo(node.key, key, keyComparator);
      if (compareVal == 0) {
        if (node instanceof BTNodeWithPST) {
          ((BTNodeWithPST<Key, Value>) node).value = value;
        }
        return;
      } else if (compareVal > 0) {
        if (node.left == null) {
          node.left = insertCreateNode(node, key, value);
          break;
        }
        node = node.left;
      } else {
        if (node.right == null) {
          node.right = insertCreateNode(node, key, value);
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
    tree = deleteMinOrMax(tree, true);
  }

//  private Node deleteMin(Node tree) {
//    if (tree == null) {
//      return null;
//    }
//    Node node = tree;
//    while (node.left != null) {
//      node = node.left;
//    }
//    if (node.parent != tree.parent) {
//      node.parent.left = node.right;
//    } else {
//      tree = node.right;
//    }
//    while (node != null) {
//      node.nodeCount = size(node.left) + size(node.right) + 1;
//      node = node.parent;
//    }
//    return tree;
//  }

  @Override
  public void deleteMax() {
    tree = deleteMinOrMax(tree, false);
  }

  private Node deleteMinOrMax(Node tree, boolean min) {
    if (tree == null) {
      return null;
    }
    Node node = tree;
    if (min) {
      while (node.left != null) {
        node = node.left;
      }
    } else {
      while (node.right != null) {
        node = node.right;
      }
    }

    if (min) {
      if (node.right != null) {
        node.right.parent = node.parent;
//        node.right.prev = null;
      }
    } else {
      if (node.left != null) {
        node.left.parent = node.parent;
//        node.left.next = null;
      }
    }
    if (node.parent != tree.parent) {
      if (min) {
        node.parent.left = node.right;
      } else {
        node.parent.right = node.left;
      }
    } else {
      tree = min ? node.right : node.left;
    }
    while (node != null) {
      node.nodeCount = size(node.left) + size(node.right) + 1;
      node = node.parent;
    }
    return tree;
  }

//  private Node deleteMax(Node tree) {
//    if (tree == null) {
//      return null;
//    }
//    Node node = tree;
//    while (node.right != null) {
//      node = node.right;
//    }
//    if (node.parent != tree.parent) {
//      node.parent.right = node.left;
//    } else {
//      tree = node.left;
//    }
//    while (node != null) {
//      node.nodeCount = size(node.left) + size(node.right) + 1;
//      node = node.parent;
//    }
//    return tree;
//  }

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
      compareVal = SortUtils.compareTo(node.key, key, keyComparator);
      if (compareVal == 0) {
        break;
      } else if (compareVal > 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
    if (node != null) {
      if (node.left != null && node.right != null) {
        Node t = node;
        node = min(t.right);
        node.left = t.left;
        node.right = deleteMinOrMax(t.right, true);
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
      } else {
        tree = deleteSingleChildNode(tree, node);
      }
    }
    while (node != null) {
      node.nodeCount = size(node.left) + size(node.right) + 1;
      node = node.parent;
    }
    return tree;
  }

  private Node deleteSingleChildNode(Node tree,
      Node toDel) {
    Node toDelChild = toDel.right == null ? toDel.left : toDel.right;
    if (toDel.parent == tree.parent) {
      tree = toDelChild;
    } else {
      if (toDelChild != null) {
        toDelChild.parent = toDel.parent;
      }
      if (toDel.parent.left == toDel) {
        toDel.parent.left = toDelChild;
      } else {
        toDel.parent.right = toDelChild;
      }
    }
    return tree;
  }

  //  @Override
//  public Key next(Key key) {
//    Node node = get(key, tree);
//    return node == null || node.next == null ? null : node.next.key;
//  }
//
//  @Override
//  public Key prev(Key key) {
//    Node node = get(key, tree);
//    return node == null || node.prev == null ? null : node.prev.key;
//  }

  protected abstract Node insertCreateNode(Node parent, Key key, Value value);
}
