package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class BTNodeWithP<Key, Value> extends
    AbstractBTNodeWithP<Key, Value, BTNodeWithP<Key, Value>> {

  BTNodeWithP(BTNodeWithP<Key, Value> parent, Key key, Value value, int nodeCount) {
    super(parent, key, value, nodeCount);
    this.parent = parent;
  }
}
