package com.huangsu.algorithm.string;


import com.huangsu.algorithm.string.AbstractRWayTrieStringSetCollection.RWayTrieNodeST;

/**
 * Created by huangsu2012@gmail.com on 2021/5/10.
 */
public class TrieStringST<Value> extends
    AbstractRWayTrieStringSetCollection<Value, RWayTrieNodeST<Value>> implements
    StringST<Value> {


  public TrieStringST(int R) {
    super(R);
  }

  @Override
  protected RWayTrieNodeST<Value> insertCreateNode(Value value) {
    return new RWayTrieNodeST<>(R, value);
  }

  @Override
  protected void insertReplaceNodeValue(RWayTrieNodeST<Value> node, Value value) {
    node.value = value;
  }

  @Override
  protected boolean isKeyEndCh(RWayTrieNodeST<Value> node) {
    return node.value != null;
  }


  @Override
  public void put(String s, Value value) {
    insert(s, value);
  }

  @Override
  public Value get(String s) {
    RWayTrieNodeST<Value> node = getNode(s);
    return node == null ? null : node.value;
  }


}
