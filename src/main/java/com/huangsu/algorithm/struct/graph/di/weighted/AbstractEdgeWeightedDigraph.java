package com.huangsu.algorithm.struct.graph.di.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/24.
 */
public abstract class AbstractEdgeWeightedDigraph implements EdgeWeightedDigraph {

  protected final int V;
  protected int E;

  public AbstractEdgeWeightedDigraph(int v) {
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
