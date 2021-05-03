package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/4/7.
 */
class STNode<Key, Value> {

  Key key;
  Value value;

  STNode(Key key, Value value) {
    this.key = key;
    this.value = value;
  }
}
