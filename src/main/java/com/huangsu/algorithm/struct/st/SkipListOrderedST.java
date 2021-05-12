package com.huangsu.algorithm.struct.st;

import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/3/21.
 *
 * 基于跳表实现的有序符号表
 */
public class SkipListOrderedST<Key, Value> extends
    AbstractSkipListOrdered<Key, Value, SkipListNodeST<Key, Value>> implements
    OrderedST<Key, Value> {


  public SkipListOrderedST() {
    this(DEFAULT_MAX_LEVEL, DEFAULT_P, null);
  }

  public SkipListOrderedST(Comparator<Key> keyComparator) {
    this(DEFAULT_MAX_LEVEL, DEFAULT_P, keyComparator);
  }

  public SkipListOrderedST(int maxLevel, double p, Comparator<Key> keyComparator) {
    super(maxLevel, p, keyComparator);
  }

  @Override
  protected SkipListNodeST<Key, Value> insertCreateNode(Key key, Value value,
      int toInsertLevel) {
    return new SkipListNodeST<>(key, value, toInsertLevel);
  }

  @Override
  protected void insertReplaceNodeValue(SkipListNodeST<Key, Value> node, Value value) {
    node.value = value;
  }


  @Override
  public void put(Key key, Value value) {
    insert(key, value);
  }

  @Override
  public Value get(Key key) {
    SkipListNodeST<Key, Value> node = getNode(key);
    return node == null ? null : node.value;
  }


}
