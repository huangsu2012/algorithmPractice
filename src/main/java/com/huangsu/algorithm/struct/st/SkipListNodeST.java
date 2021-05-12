package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/5.
 */
class SkipListNodeST<Key, Value> extends SkipListNode<Key, SkipListNodeST<Key, Value>> {

  Value value;

  public SkipListNodeST(Key key, Value value, int level) {
    super(key, level);
    this.value = value;
  }
}
