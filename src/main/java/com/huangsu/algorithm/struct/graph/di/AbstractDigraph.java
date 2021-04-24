package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.util.GraphUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/4/12.
 *
 * 有向图抽象实现
 */
abstract class AbstractDigraph implements Digraph {

  final int V;
  int E;

  public AbstractDigraph(int v) {
    V = v;
  }

  @Override
  public int V() {
    return V;
  }

  @Override
  public int E() {
    return E;
  }

  @Override
  public Digraph reverse() {
    Digraph reverse = newDigraph(V);
    for (int i = 0; i < V; i++) {
      for (int j : adj(i)) {
        reverse.addEdge(j, i);
      }
    }
    return reverse;
  }

  protected abstract Digraph newDigraph(int V);

  @Override
  public String toString() {
    return "Digraph:" + GraphUtils.toString(this);
  }
}
