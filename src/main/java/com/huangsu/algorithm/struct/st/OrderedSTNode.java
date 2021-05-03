package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class OrderedSTNode<Key, Value> extends STNode<Key, Value> {
  /**
   * 以该节点为根的子树中的节点总数
   */
  int nodeCount;

  OrderedSTNode(Key key, Value value, int nodeCount) {
    super(key, value);
    this.nodeCount = nodeCount;
  }
}
