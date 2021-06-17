package com.huangsu.algorithm.string;

import com.huangsu.algorithm.string.AbstractThreeWayTrieStringSetCollection.ThreeWayTrieNode;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/5/18.
 */
public abstract class AbstractThreeWayTrieStringSetCollection<Value, Node extends ThreeWayTrieNode<Node>> extends
    AbstractTrieStringSetCollection<Value, Node> implements StringSetCollection {

  public AbstractThreeWayTrieStringSetCollection() {
    super(false);
  }

  @SuppressWarnings("Duplicates")
  @Override
  public String longestPrefixOf(String s) {
    Node node = root;
    int i = 0;
    while (node != null && i < s.length()) {
      char c = s.charAt(i);
      if (node.c == c) {
        node = node.eq;
        ++i;
      } else if (node.c < c) {
        node = node.gt;
      } else {
        node = node.lt;
      }
    }
    return i == 0 ? null : s.substring(0, i);
  }

//  /**
//   * @param s 要查找的字符串,如果为空且prefixMatch为true表示查找所有键
//   * @param prefixMatch 是否为前缀匹配
//   * @return 匹配的键列表
//   */
//  @SuppressWarnings("Duplicates")
//  protected Iterable<String> findKeysMatch(String s, boolean prefixMatch) {
//    if (root == null) {
//      return null;
//    }
//    Queue<String> stringQueue = new LinkedQueue<>();
//    Queue<StringBuilder> sbs = new LinkedQueue<>();
//    Queue<Node> nodes = new LinkedQueue<>();
//    sbs.enqueue(new StringBuilder());
//    nodes.enqueue(root);
//    int i = 0;
//    if (s != null && s.length() > 0) {
//      for (; i < s.length() - 1 && !nodes.isEmpty(); i++) {
//        char c = s.charAt(i);
//        findKeysMatch(sbs, nodes, c, false, stringQueue);
//      }
//      findKeysMatch(sbs, nodes, s.charAt(i++), true, stringQueue);
//    }
//    if (s == null || i == s.length()) {
//      findKeysMatch(sbs, nodes, '.', true, stringQueue);
//    }
//    if (prefixMatch) {
//      while (!nodes.isEmpty()) {
//        findKeysMatch(sbs, nodes, '.', true, stringQueue);
//      }
//    }
//    return stringQueue;
//  }

  /**
   * @param sbs 用于匹配到当前层得到的符合条件的字符串
   * @param nodes 当前需要匹配的节点
   * @param c 要匹配的字符
   * @param keyEndCh 是否为键的结尾
   * @param stringQueue 用于存储符合条件的字符串
   */
  protected void findKeysMatch(Queue<StringBuilder> sbs, Queue<Node> nodes,
      char c,
      boolean keyEndCh, Queue<String> stringQueue) {
    int expectFindMatchChCount = nodes.size();
    int findMatchChCount = nodes.size();
    while (!nodes.isEmpty()) {
      Node node = nodes.dequeue();
      if (node == null) {
        break;
      }
      StringBuilder sb = sbs.dequeue();
      boolean findMatchCh = true;
      if (c == '.') {
        if (findMatchChCount > 0) {
          findKeysMatchEnqueue(sbs, nodes, node.lt, new StringBuilder(sb));
          findKeysMatchEnqueue(sbs, nodes, node.gt, new StringBuilder(sb));
        }
        if (findMatchChCount == expectFindMatchChCount) {
          nodes.enqueue(null);//主要是为了隔离不同字符层，所谓字符层可以理解为sbs中各字符串的字符数量是否一致，一致就是同一层
        }
        findKeysMatchEnqueue(sbs, nodes, node.eq, sb.append(node.c));
        --findMatchChCount;
      } else if (node.c == c) {
        if (findMatchChCount == expectFindMatchChCount) {
          nodes.enqueue(null);
        }
        sb.append(node.c);
        findKeysMatchEnqueue(sbs, nodes, node.eq, sb);
        --findMatchChCount;
      } else if (node.c < c) {//对于不匹配的节点继续从大于或小于的子节点中查找，直到找到或者遍历完所有节点
        findMatchCh = false;
        findKeysMatchEnqueue(sbs, nodes, node.gt, sb);
      } else {
        findMatchCh = false;
        findKeysMatchEnqueue(sbs, nodes, node.lt, sb);
      }
      if (keyEndCh && isKeyEndCh(node)
          && findMatchCh) {//在最后一个字符一直不匹配的情况下会出现前面两个条件为真，但此时sb的值并不是与查找字符串匹配的
        stringQueue.enqueue(sb.toString());
      }
    }
  }

  private void findKeysMatchEnqueue(Queue<StringBuilder> sbs,
      Queue<Node> nodes, Node node,
      StringBuilder sb) {
    if (node != null) {
      sbs.enqueue(sb);
      nodes.enqueue(node);
    }
  }


  protected boolean insert(String s, Value value) {
    if (root == null) {
      root = insertCreateNode(null);
      root.c = s.charAt(0);
    }
    Node node = root;
    int i = 0;
    boolean addKey = false;
    while (i < s.length()) {
      char c = s.charAt(i);
      if (c == node.c) {
        addKey = addKey || node.eq == null;
        node.eq = createNodeIfNeed(s, ++i, node.eq);
        node = i == s.length() ? node : node.eq;
      } else if (c < node.c) {
        addKey = addKey || node.lt == null;
        node.lt = createNodeIfNeed(s, i, node.lt);
        node = node.lt;
      } else {
        addKey = addKey || node.gt == null;
        node.gt = createNodeIfNeed(s, i, node.gt);
        node = node.gt;
      }
    }
    insertReplaceNodeValue(node, value);
    if (addKey) {
      ++size;
    }
    return addKey;
  }

  private Node createNodeIfNeed(String s, int i,
      Node origin) {
    if (origin == null && i < s.length()) {
      Node node = insertCreateNode(null);
      node.c = s.charAt(i);
      return node;
    }
    return origin;
  }


  @SuppressWarnings("Duplicates")
  Node getNode(String s) {
    Node node = root;
    int i = 0;
    while (node != null && i < s.length()) {
      char c = s.charAt(i);
      if (node.c == c) {
        node = node.eq;
        ++i;
      } else if (node.c < c) {
        node = node.gt;
      } else {
        node = node.lt;
      }
    }
    if (i == s.length() && isKeyEndCh(node)) {
      return node;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  protected Value deleteNode(String s) {
    if (s == null) {
      return null;
    }
    Node node = root;
    Node toDelNodeParent = null;
    Node toDelNode = node;
    int i = 0;
    Value toDelNodeValue = null;
    while (node != null && i < s.length()) {
      char c = s.charAt(i);
      if (node.c == c) {
        ++i;
        if (node.lt == null && node.gt == null) {
          if (toDelNodeParent == null) {
            toDelNodeParent = node;
          }
        } else {
          toDelNodeParent = null;
        }
        toDelNode = node;
        node = node.eq;
      } else if (node.c < c) {
        node = node.gt;
      } else {
        node = node.lt;
      }
    }
    if (i == s.length()) {
      --size;
      if (toDelNodeParent != null && isKeyEndCh(toDelNode)) {
        if (toDelNode instanceof ThreeWayTrieNodeST) {
          toDelNodeValue = ((ThreeWayTrieNodeST<Value>) toDelNode).value;
        }
        toDelNodeParent.eq = null;
        insertReplaceNodeValue(toDelNode, null);
      }
    }
    return toDelNodeValue;
  }

  @Override
  public boolean contains(String s) {
    Node node = getNode(s);
    return node != null;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void insertReplaceNodeValue(Node node, Value value) {
    if (node instanceof ThreeWayTrieNodeST) {
      ((ThreeWayTrieNodeST<Value>) node).value = value;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  protected boolean isKeyEndCh(Node node) {
    if (node instanceof ThreeWayTrieNodeST) {
      return ((ThreeWayTrieNodeST<Value>) node).value != null;
    } else if (node instanceof ThreeWayTrieNodeSet) {
      return ((ThreeWayTrieNodeSet) node).isEndCh;
    }
    return false;
  }

  protected static class ThreeWayTrieNode<Node extends ThreeWayTrieNode<Node>> implements TrieNode {

    char c;
    Node lt, eq, gt;

    public ThreeWayTrieNode() {
    }

    public ThreeWayTrieNode(char c) {
      this.c = c;
    }
  }

  protected static class ThreeWayTrieNodeST<Value> extends
      ThreeWayTrieNode<ThreeWayTrieNodeST<Value>> {

    protected Value value;

    public ThreeWayTrieNodeST() {
    }
  }

  protected static class ThreeWayTrieNodeSet extends ThreeWayTrieNode<ThreeWayTrieNodeSet> {

    boolean isEndCh;

    public ThreeWayTrieNodeSet() {
    }
  }
}
