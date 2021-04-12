package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.bag.Bag;
import com.huangsu.algorithm.struct.bag.LinkedBag;

/**
 * Created by huangsu2012@gmail.com on 2021/3/27.
 *
 * 邻接表实现无向图
 */
public class AdjacencyListGraph extends AbstractGraph implements Graph {

  private Bag<Integer>[] adj;

  @SuppressWarnings("unchecked")
  public AdjacencyListGraph(int v) {
    super(v);
    adj = (Bag<Integer>[]) new Bag[v];
  }


  @Override
  public void addEdge(int v, int w) {
    Bag<Integer> vBag = getAdj(v);
    vBag.add(w);
    Bag<Integer> wBag = getAdj(w);
    wBag.add(v);
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
