package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.bag.Bag;
import com.huangsu.algorithm.struct.bag.LinkedBag;

/**
 * Created by huangsu2012@gmail.com on 2021/4/12.
 *
 * 邻接表实现有向图
 */
public class AdjacencyListDigraph extends AbstractDigraph {

  private Bag<Integer>[] adj;

  @SuppressWarnings("unchecked")
  public AdjacencyListDigraph(int v) {
    super(v);
    adj = (Bag<Integer>[]) new Bag[v];
  }

  @Override
  protected Digraph newDigraph(int V) {
    return new AdjacencyListDigraph(V);
  }

  @Override
  public void addEdge(int v, int w) {
    getAdj(v).add(w);
    ++E;
  }

  private Bag<Integer> getAdj(int v) {
    Bag<Integer> vBag = adj[v];
    if (vBag == null) {
      vBag = new LinkedBag<>();
      adj[v] = vBag;
    }
    return vBag;
  }

  @Override
  public Iterable<Integer> adj(int v) {
    return getAdj(v);
  }

}
