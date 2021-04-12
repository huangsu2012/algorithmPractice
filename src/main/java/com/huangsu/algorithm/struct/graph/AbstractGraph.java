package com.huangsu.algorithm.struct.graph;

/**
 * Created by huangsu2012@gmail.com on 2021/3/27.
 */
abstract class AbstractGraph implements Graph {

  final int V;
  int E;

  public AbstractGraph(int v) {
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

}
