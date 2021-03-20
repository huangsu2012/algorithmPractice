package com.huangsu.algorithm;

/**
 * Created by huangsu2012@gmail.com on 2018/4/13.
 */
public class UnionFindQuickFind extends UnionFindArrayImpl {

  public UnionFindQuickFind(int n) {
    super(n);
  }

  @Override
  public void union(int p, int q) {
    int pId = find(p);
    int qId = find(q);
    if (pId == qId) {
      return;
    }
    for (int i = 0; i < id.length; i++) {
      if (id[i] == qId) {
        id[i] = pId;
      }
    }
    --count;
  }

  @Override
  public int find(int p) {
    return id[p];
  }
}
