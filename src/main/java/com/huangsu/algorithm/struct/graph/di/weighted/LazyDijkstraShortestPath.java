package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.priorityqueue.ArrayHeapMinPriorityQueue;
import com.huangsu.algorithm.struct.priorityqueue.MinPriorityQueue;

/**
 * Created by huangsu2012@gmail.com on 2021/4/26.
 *
 * dijkstra算法查找最短路径延迟实现版本，只对非负权重有向图有效
 */
public class LazyDijkstraShortestPath extends AbstractEdgeWeightedDigraphPath {

  //标识顶点是否访问过，在这个算法中表达的是某个顶点是否加入到最小生成树中
  private final boolean[] marked;
  private final MinPriorityQueue<DirectedEdge> minEdges;

  public LazyDijkstraShortestPath(EdgeWeightedDigraph g, int s) {
    super(g, s, true);
    marked = new boolean[g.V()];
    minEdges = new ArrayHeapMinPriorityQueue<>(g.V());
  }

  @Override
  protected void findPaths() {
    relax(s);
    while (!minEdges.isEmpty()) {
      DirectedEdge edge = minEdges.delMin();
      int v = edge.from();
      int w = edge.to();
      if (!marked[v]) {
        relax(v);
      }
      if (!marked[w]) {
        relax(w);
      }
    }
  }

  @Override
  protected void relax(int v) {
    marked[v] = true;
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        minEdges.insert(e);
      }
    }
  }
}
