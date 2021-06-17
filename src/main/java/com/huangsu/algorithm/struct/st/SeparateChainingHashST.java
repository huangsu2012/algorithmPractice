package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionSeparateChainingHash.NodeHashST;
import com.huangsu.algorithm.util.HashUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/3/20.
 */
public class SeparateChainingHashST<Key, Value> extends
    AbstractSetCollectionSeparateChainingHash<Key, Value, NodeHashST<Key, Value>> implements
    ST<Key, Value> {


  public SeparateChainingHashST() {
  }

  public SeparateChainingHashST(int initCap) {
    super(initCap);
  }

  public SeparateChainingHashST(int initCap, float loadFactor) {
    super(initCap, loadFactor);
  }

  @Override
  public void put(Key key, Value value) {
    insert(key, value);
  }


  @Override
  public Value get(Key key) {
    int index = HashUtils.indexOf(key, tableLength);
    NodeHashST<Key, Value> e = table[index];
    while (e != null) {
      if (e.key == key || (key != null && key.equals(e.key))) {
        return e.value;
      } else {
        e = e.next;
      }
    }
    return null;
  }

  @Override
  public Value delete(Key key) {
    NodeHashST<Key, Value> node = deleteNode(key);
    return node == null ? null : node.value;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected NodeHashST<Key, Value>[] createNewTab(int tableLength) {
    return (NodeHashST<Key, Value>[]) new NodeHashST[tableLength];
  }

  @Override
  protected NodeHashST<Key, Value> insertCreateNode(Key key, Value value, int hash) {
    return new NodeHashST<>(key, value, hash);
  }


}
