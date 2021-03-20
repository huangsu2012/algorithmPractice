package com.huangsu.algorithm;

/**
 * Created by huangsu2012@gmail.com on 2018/4/13.
 */
public class UnionFindQuickUnion extends UnionFindArrayImpl {

  public UnionFindQuickUnion(int n) {
    super(n);
  }

  @Override
  public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) {
      return;
    }
    id[qRoot] = pRoot;
    --count;
  }

  @Override
  public int find(int p) {
    if (id[p] != p) {
      p = id[p];
    }
    return p;
  }
}
