package com.huangsu.algorithm.string;

import com.huangsu.algorithm.string.AbstractRWayTrieStringSetCollection.RWayTrieNode;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/5/18.
 *
 * R向单词查找树实现不可重复键集合
 */
public abstract class AbstractRWayTrieStringSetCollection<Value, Node extends RWayTrieNode<Node>> extends
    AbstractTrieStringSetCollection<Value, Node> implements
    StringSetCollection {

  protected final int R;


  public AbstractRWayTrieStringSetCollection(int r) {
    super(true);
    R = r;
    root = insertCreateNode(null);
  }

  @SuppressWarnings("Duplicates")
  @Override
  public String longestPrefixOf(String s) {
    Node node = root;
    int i = 0;
    for (; i < s.length(); i++) {
      char c = s.charAt(i);
      if (node.next[c] != null) {
        node = node.next[c];
      } else {
        break;
      }
    }
    return i == 0 ? null : s.substring(0, i);
  }


  //  /**
//   * 递归实现版本
//   */
//  @Override
//  public Iterable<String> keysThatMatch(String s) {
//    Stack<StringBuilder> sbs = new LinkedStack<>();
//    sbs.push(new StringBuilder());
//    keysThatMatch(root, s, 0, sbs);
//    Queue<String> stringQueue = new LinkedQueue<>();
//    for (StringBuilder sb : sbs) {
//      stringQueue.enqueue(sb.toString());
//    }
//    return stringQueue;
//  }
//
//  private void keysThatMatch(Node node, String s, int i, Stack<StringBuilder> sbs) {
//    if (i == s.length()) {
//      return;
//    }
//    char c = s.charAt(i);
//    StringBuilder sb = sbs.pop();
//    if (c == '.') {
//      for (int j = 0; j < node.next.length; j++) {
//        Node temp = node.next[j];
//        if (temp != null) {
//          sbs.push(new StringBuilder(sb).append((char) j));
//          keysThatMatch(temp, s, i + 1, sbs);
//        }
//      }
//    } else if (node.next[c] != null) {
//      sb.append(c);
//      sbs.push(sb);
//      keysThatMatch(node.next[c], s, i + 1, sbs);
//    }
//  }
  protected boolean insert(String s, Value value) {
    Node node = root;
    int i = 0;
    boolean addKey = false;
    for (; i < s.length(); i++) {
      char c = s.charAt(i);
      if (node.next[c] != null) {
        node = node.next[c];
      } else {
        Node temp = insertCreateNode(null);
        node.next[c] = temp;
        node.nodeCount++;
        node = temp;
        addKey = true;
      }
    }
    insertReplaceNodeValue(node, value);
    if (addKey) {
      ++size;
    }
    return addKey;
  }

  @Override
  public void delete(String s) {
    Node node = root;
    int i = 0;
    Node lastKeyNode = null;//在查找要删除的键的路径中最后遇到的键的节点
    int lastKeyNodeIndex = -1;//在查找要删除的键的路径中最后遇到的键的节点对应的字符串索引
    for (; i < s.length(); i++) {
      char c = s.charAt(i);
      if (node.next[c] != null) {
        if (isKeyEndCh(node)) {
          lastKeyNodeIndex = i;
          lastKeyNode = node;
        }
        node = node.next[c];
      } else {
        break;
      }
    }
    if (i == s.length()) {
      insertReplaceNodeValue(node, null);
      node = lastKeyNode;
      if (lastKeyNodeIndex == -1) {
        lastKeyNodeIndex = 0;
        node = root;
      }
      i = lastKeyNodeIndex;
      for (; i < s.length(); i++) {
        char c = s.charAt(i);
        Node temp = node.next[c];
        if (temp.nodeCount <= 1) {
          node.next[c] = null;
          node.nodeCount--;
        }
        node = temp;
      }
      --size;
    }
  }

  @Override
  public boolean contains(String s) {
    return getNode(s) != null;
  }


  /**
   * @param sbs 用于匹配到当前层得到的符合条件的字符串
   * @param nodes 当前需要匹配的节点
   * @param c 要匹配的字符
   * @param keyEndCh 是否为键的结尾
   * @param stringQueue 用于存储符合条件的字符串
   */
  protected void findKeysMatch(Queue<StringBuilder> sbs, Queue<Node> nodes, char c,
      boolean keyEndCh, Queue<String> stringQueue) {
    nodes.enqueue(null);
    while (!nodes.isEmpty()) {
      Node node = nodes.dequeue();
      if (node == null) {
        break;
      }
      StringBuilder sb = sbs.dequeue();
      if (c == '.') {
        for (int j = 0; j < node.next.length; j++) {
          Node temp = node.next[j];
          if (temp != null) {
            sbs.enqueue(new StringBuilder(sb).append((char) j));
            nodes.enqueue(temp);
          }
        }
      } else if (node.next[c] != null) {
        nodes.enqueue(node.next[c]);
        sbs.enqueue(sb.append(c));
      }
      if (keyEndCh && isKeyEndCh(node)) {
        stringQueue.enqueue(sb.toString());
      }
    }
  }

  @SuppressWarnings("Duplicates")
  protected Node getNode(String s) {
    Node node = root;
    int i = 0;
    for (; i < s.length(); i++) {
      char c = s.charAt(i);
      if (node.next[c] != null) {
        node = node.next[c];
      } else {
        break;
      }
    }
    if (i == s.length() && isKeyEndCh(node)) {
      return node;
    }
    return null;
  }


  protected static class RWayTrieNode<Node extends RWayTrieNode<Node>> implements TrieNode {

    protected Node[] next;
    /**
     * 不为空的节点数
     */
    protected int nodeCount;

    protected RWayTrieNode() {
    }

    protected RWayTrieNode(int R) {

    }

  }

  protected static class RWayTrieNodeSet extends RWayTrieNode<RWayTrieNodeSet> {

    protected boolean endCh;

    protected RWayTrieNodeSet() {
    }

    protected RWayTrieNodeSet(int R, boolean endCh) {
      this.next = new RWayTrieNodeSet[R];
      this.endCh = endCh;
    }
  }

  protected static class RWayTrieNodeST<Value> extends RWayTrieNode<RWayTrieNodeST<Value>> {

    protected Value value;

    protected RWayTrieNodeST() {
    }

    @SuppressWarnings("unchecked")
    protected RWayTrieNodeST(int R, Value value) {
      this.next = new RWayTrieNodeST[R];
      this.value = value;
    }
  }
}
