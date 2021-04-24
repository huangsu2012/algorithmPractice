package com.huangsu.algorithm.struct.graph.weighted;

import com.huangsu.algorithm.struct.bag.Bag;
import com.huangsu.algorithm.struct.bag.LinkedBag;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 */
public class AdjacencyListEdgeWeightedGraph extends AbstractEdgeWeightedGraph implements
    EdgeWeightedGraph {

  private Bag<Edge>[] adj;

  public AdjacencyListEdgeWeightedGraph(int v) {
    super(v);
  }

  @Override
  public void addEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    Bag<Edge> vBag = getAdj(v);
    vBag.add(e);
    Bag<Edge> wBag = getAdj(w);
    wBag.add(e);
    ++E;
  }

  @Override
  public Iterable<Edge> adj(int v) {
    return getAdj(v);
  }

  @Override
  public Iterable<Edge> edges() {
    Bag<Edge> edges = new LinkedBag<>();
    for (int v = 0; v < V; v++) {
      Bag<Edge> vEdges = adj[v];
      if (vEdges != null) {
        for (Edge edge : vEdges) {
          if (edge.other(v) > v) {//因为是从前往后遍历顶点，且忽略自环边
            edges.add(edge);
          }
        }
      }
    }
    return edges;
  }

  private Bag<Edge> getAdj(int v) {
    Bag<Edge> vBag = adj[v];
    if (vBag == null) {
      vBag = new LinkedBag<>();
      adj[v] = vBag;
    }
    return vBag;
  }

}
