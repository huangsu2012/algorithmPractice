package com.huangsu.algorithm.struct.st;


/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class AbstractBTNode<Key, Value, Node extends AbstractBTNode<Key, Value, Node>> extends
    OrderedSTNode<Key, Value> {

  Node left;
  Node right;

  AbstractBTNode(Key key, Value value, int nodeCount) {
    super(key, value, nodeCount);

  }
}
