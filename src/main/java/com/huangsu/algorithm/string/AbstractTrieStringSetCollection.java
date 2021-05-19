package com.huangsu.algorithm.string;

import com.huangsu.algorithm.string.AbstractTrieStringSetCollection.TrieNode;
import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/5/18.
 */
public abstract class AbstractTrieStringSetCollection<Value, Node extends TrieNode> implements
    StringSetCollection {

  protected Node root;
  protected int size;
  private final boolean rootEmptyCh;

  public AbstractTrieStringSetCollection(boolean rootEmptyCh) {
    this.rootEmptyCh = rootEmptyCh;
  }

  @Override
  public Iterable<String> keysWithPrefix(String s) {
    return findKeysMatch(s, true);
  }

  @Override
  public Iterable<String> keysThatMatch(String s) {
    return findKeysMatch(s, false);
  }


  @Override
  public Iterable<String> keys() {
    return findKeysMatch(null, true);
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }


  /**
   * @param s 要查找的字符串,如果为空且prefixMatch为true表示查找所有键
   * @param prefixMatch 是否为前缀匹配
   * @return 匹配的键列表
   */
  @SuppressWarnings("Duplicates")
  protected Iterable<String> findKeysMatch(String s, boolean prefixMatch) {
    if (root == null) {
      return null;
    }
    Queue<String> stringQueue = new LinkedQueue<>();
    Queue<StringBuilder> sbs = new LinkedQueue<>();
    Queue<Node> nodes = new LinkedQueue<>();
    sbs.enqueue(new StringBuilder());
    nodes.enqueue(root);
    int i = 0;
    if (s != null && s.length() > 0) {
      int loopLen = rootEmptyCh ? s.length() : s.length() - 1;
      for (; i < loopLen && !nodes.isEmpty(); i++) {
        char c = s.charAt(i);
        findKeysMatch(sbs, nodes, c, false, stringQueue);
      }
      if (!rootEmptyCh) {
        findKeysMatch(sbs, nodes, s.charAt(i++), true, stringQueue);
      }
    }
    if (s == null || i == s.length()) {
      findKeysMatch(sbs, nodes, '.', true, stringQueue);
    }
    if (prefixMatch) {
      while (!nodes.isEmpty()) {
        findKeysMatch(sbs, nodes, '.', true, stringQueue);
      }
    }
    return stringQueue;
  }

  /**
   * @param sbs 用于匹配到当前层得到的符合条件的字符串
   * @param nodes 当前需要匹配的节点
   * @param c 要匹配的字符
   * @param keyEndCh 是否为键的结尾
   * @param stringQueue 用于存储符合条件的字符串
   */
  protected abstract void findKeysMatch(Queue<StringBuilder> sbs, Queue<Node> nodes, char c,
      boolean keyEndCh, Queue<String> stringQueue);

  protected abstract Node insertCreateNode(Value value);

  protected abstract void insertReplaceNodeValue(Node node, Value value);

  protected abstract boolean isKeyEndCh(Node node);

  protected interface TrieNode {

  }
}
