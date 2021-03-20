package com.huangsu.algorithm;

/**
 * Created by huangsu2012@gmail.com on 2018/4/13.
 */
public abstract class UnionFindArrayImpl implements UnionFind {

  int[] id;

  int count;

  public UnionFindArrayImpl(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
    count = id.length;
  }


  @Override
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  @Override
  public int count() {
    return count;
  }
}
