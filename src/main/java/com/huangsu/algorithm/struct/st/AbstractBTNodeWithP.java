package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class AbstractBTNodeWithP<Key, Value, Node extends AbstractBTNodeWithP<Key, Value, Node>> extends
    AbstractBTNode<Key, Value, Node> {

  Node parent;

  AbstractBTNodeWithP(Node parent, Key key, Value value, int nodeCount) {
    super(key, value, nodeCount);
    this.parent = parent;
  }
}
