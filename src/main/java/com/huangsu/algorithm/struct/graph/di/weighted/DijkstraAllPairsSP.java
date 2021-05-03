package com.huangsu.algorithm.struct.graph.di.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/26.
 *
 * 加权有向图顶点对路径以及权重问题
 */
public class DijkstraAllPairsSP {

  public final DijkstraShortestPath[] all;

  public DijkstraAllPairsSP(EdgeWeightedDigraph g) {
    all = new DijkstraShortestPath[g.V()];
    for (int v = 0; v < g.V(); v++) {
      all[v] = new DijkstraShortestPath(g, v);
    }
  }

  public Iterable<DirectedEdge> pathTo(int s, int t) {
    return all[s].pathTo(t);
  }

  public double dist(int s, int t) {
    return all[s].distTo(t);
  }
}
