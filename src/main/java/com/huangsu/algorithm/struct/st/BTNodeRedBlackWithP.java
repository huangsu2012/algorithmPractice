package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class BTNodeRedBlackWithP<Key, Value> extends
    AbstractBTNodeWithP<Key, Value, BTNodeRedBlackWithP<Key, Value>> {

  boolean red;

  BTNodeRedBlackWithP(BTNodeRedBlackWithP<Key, Value> parent, Key key, Value value,
      int nodeCount) {
    super(parent, key, value, nodeCount);
    this.red = true;
  }

  BTNodeRedBlackWithP(BTNodeRedBlackWithP<Key, Value> parent, Key key, Value value,
      int nodeCount, boolean red) {
    super(parent, key, value, nodeCount);
    this.red = red;
  }
}
