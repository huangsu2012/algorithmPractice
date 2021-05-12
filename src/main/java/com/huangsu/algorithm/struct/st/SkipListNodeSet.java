package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/5.
 */
 class SkipListNodeSet<Key> extends SkipListNode<Key,SkipListNodeSet<Key>> {

  public SkipListNodeSet(Key key, int level) {
    super(key, level);
  }
}
