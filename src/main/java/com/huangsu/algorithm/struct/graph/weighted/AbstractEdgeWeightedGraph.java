package com.huangsu.algorithm.struct.graph.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 */
public abstract class AbstractEdgeWeightedGraph implements EdgeWeightedGraph {

  protected final int V;
  protected int E;

  public AbstractEdgeWeightedGraph(int v) {
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
