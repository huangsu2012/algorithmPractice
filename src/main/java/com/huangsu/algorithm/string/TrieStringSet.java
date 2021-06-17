package com.huangsu.algorithm.string;

import com.huangsu.algorithm.string.AbstractRWayTrieStringSetCollection.RWayTrieNodeSet;

/**
 * Created by huangsu2012@gmail.com on 2021/5/18.
 */
public class TrieStringSet extends
    AbstractRWayTrieStringSetCollection<Void, RWayTrieNodeSet> implements
    StringSet {

  public TrieStringSet(int r) {
    super(r);
  }


  @Override
  public boolean add(String s) {
    return insert(s, null);
  }

  @Override
  public boolean delete(String s) {
    int oldSize = size;
    deleteNode(s);
    return oldSize > size;
  }


  @Override
  protected RWayTrieNodeSet insertCreateNode(Void aVoid) {
    return new RWayTrieNodeSet(R, false);
  }
}
