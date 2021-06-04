package com.huangsu.algorithm.string;


import com.huangsu.algorithm.string.AbstractThreeWayTrieStringSetCollection.ThreeWayTrieNodeSet;

/**
 * Created by huangsu2012@gmail.com on 2021/5/18.
 */
public class ThreeWayTrieStringSet extends
    AbstractThreeWayTrieStringSetCollection<Void, ThreeWayTrieNodeSet> implements StringSet {


  @Override
  public boolean add(String s) {
    return insert(s, null);
  }

  @Override
  protected ThreeWayTrieNodeSet insertCreateNode(Void aVoid) {
    return new ThreeWayTrieNodeSet();
  }


}
