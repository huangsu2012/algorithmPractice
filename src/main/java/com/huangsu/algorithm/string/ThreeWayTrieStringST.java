package com.huangsu.algorithm.string;

import com.huangsu.algorithm.string.AbstractThreeWayTrieStringSetCollection.ThreeWayTrieNodeST;

/**
 * Created by huangsu2012@gmail.com on 2021/5/14.
 *
 * 三向单词查找树实现
 */
public class ThreeWayTrieStringST<Value> extends
    AbstractThreeWayTrieStringSetCollection<Value, ThreeWayTrieNodeST<Value>> implements
    StringST<Value> {


  public ThreeWayTrieStringST() {

  }


  @Override
  public void put(String s, Value value) {
    insert(s, value);
  }

  @Override
  public Value get(String s) {
    ThreeWayTrieNodeST<Value> node = getNode(s);
    return node == null ? null : node.value;
  }

  @Override
  protected ThreeWayTrieNodeST<Value> insertCreateNode(Value value) {
    ThreeWayTrieNodeST<Value> node = new ThreeWayTrieNodeST<>();
    node.value = value;
    return node;
  }

}
