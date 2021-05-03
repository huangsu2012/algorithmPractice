package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.bag.Bag;
import com.huangsu.algorithm.struct.bag.LinkedBag;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 *
 * 加权有向图邻接表实现
 */
public class AdjacencyListEdgeWeightedDigraph extends AbstractEdgeWeightedDigraph implements
    EdgeWeightedDigraph {

  private Bag<DirectedEdge>[] adj;

  public AdjacencyListEdgeWeightedDigraph(int v) {
    super(v);
  }

  @Override
  public void addEdge(DirectedEdge e) {
    getAdj(e.from()).add(e);
    ++E;
  }

  @Override
  public Iterable<DirectedEdge> adj(int v) {
    return getAdj(v);
  }

  @Override
  public Iterable<DirectedEdge> edges() {
    Bag<DirectedEdge> edges = new LinkedBag<>();
    for (int v = 0; v < V; v++) {
      Bag<DirectedEdge> vEdges = adj[v];
      if (vEdges != null) {
        for (DirectedEdge edge : vEdges) {
          edges.add(edge);
        }
      }
    }
    return edges;
  }

  private Bag<DirectedEdge> getAdj(int v) {
    Bag<DirectedEdge> vBag = adj[v];
    if (vBag == null) {
      vBag = new LinkedBag<>();
      adj[v] = vBag;
    }
    return vBag;
  }

}
