package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/5/2.
 *
 * BellmanFord算法查找最短路径，只对从起点可达的所有顶点不包含负权重环的加权有向图有效
 */
public class BellmanFordShortestPath extends AbstractEdgeWeightedDigraphPath implements
    EdgeWeightedDigraphNegativeCycle {

  private Queue<Integer> queue;
  private boolean[] onQueue;
  private int relaxCount;
  private Iterable<DirectedEdge> negativeCycle;

  public BellmanFordShortestPath(
      EdgeWeightedDigraph g, int s) {
    super(g, s, true);
  }

  @Override
  protected void findPaths() {
    queue = new LinkedQueue<>();
    onQueue = new boolean[g.V()];
    queue.enqueue(s);
    onQueue[s] = true;
    while (!queue.isEmpty() && !hasNegativeCycle()) {
      int v = queue.dequeue();
      onQueue[v] = false;
      relax(v);
    }
  }

  //  //简单实现，每一轮放松所有边，重复v轮
//  @Override
//  protected void findPaths() {
//    for(int pass=0;pass<g.V();pass++){
//      for(int v=0;v<g.V();v++){
//        for(DirectedEdge e:g.adj(v)){
//          relax(e);
//        }
//      }
//    }
//  }

  /**
   * 放松一个顶点指出的所有边
   *
   * @param v 顶点
   */
  protected void relax(int v) {
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (!onQueue[w]) {
          queue.enqueue(w);
          onQueue[w] = true;
        }
      }
      if (relaxCount++ % g.V() == 0) {
        findNegativeCycle();
      }
    }
  }


  private void findNegativeCycle() {
    int V = edgeTo.length;
    EdgeWeightedDigraph spt = new AdjacencyListEdgeWeightedDigraph(V);
    for (DirectedEdge directedEdge : edgeTo) {
      if (directedEdge != null) {
        spt.addEdge(directedEdge);
      }
    }
    negativeCycle = new EdgeWeightedDigraphNegativeCycleFinder(spt).negativeCycle();
  }

  @Override
  public boolean hasNegativeCycle() {
    return negativeCycle != null;
  }

  @Override
  public Iterable<DirectedEdge> negativeCycle() {
    return negativeCycle;
  }

}
