package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class BTNode<Key, Value> extends
    AbstractBTNode<Key, Value, BTNode<Key, Value>> {

  BTNode(Key key, Value value, int nodeCount) {
    super(key, value, nodeCount);
  }
}
