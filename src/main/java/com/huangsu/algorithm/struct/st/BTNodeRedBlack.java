package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class BTNodeRedBlack<Key, Value> extends
    AbstractBTNode<Key, Value, BTNodeRedBlack<Key, Value>> {

  boolean red;

  BTNodeRedBlack(Key key, Value value, int nodeCount) {
    super(key, value, nodeCount);
    this.red = true;
  }

  BTNodeRedBlack(Key key, Value value, int nodeCount, boolean red) {
    super(key, value, nodeCount);
    this.red = red;
  }
}
