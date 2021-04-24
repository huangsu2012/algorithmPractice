package com.huangsu.algorithm.struct.graph.weighted;

import com.huangsu.algorithm.struct.priorityqueue.ArrayHeapMinPriorityQueue;
import com.huangsu.algorithm.struct.priorityqueue.MinPriorityQueue;
import com.huangsu.algorithm.struct.queue.LinkedQueue;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 *
 * prim延时版实现，
 *
 * prim算法的核心就是任意权值最小的横切边都属于最小生成树
 *
 * 所以实现的核心逻辑就是每次将一条最小的横切边加入到最小生成树的贪心算法
 */
public class LazyPrimMST extends AbstractMST {

  //标识顶点是否访问过，在这个算法中表达的是某个顶点是否加入到最小生成树中
  private final boolean[] marked;
  private final MinPriorityQueue<Edge> minEdges;

  public LazyPrimMST(EdgeWeightedGraph g) {
    super(g);
    marked = new boolean[g.V()];
    mst = new LinkedQueue<>();
    minEdges = new ArrayHeapMinPriorityQueue<>(g.V());
    findMST();
  }

  private void findMST() {
    visit(0);
    while (!minEdges.isEmpty()) {
      Edge e = minEdges.delMin();
      int v = e.either();
      int w = e.other(v);
      if (marked[v] && marked[w]) {//意味着对应的边已经属于最小生成树
        continue;
      }
      mst.enqueue(e);
      weight += e.weight();
      if (!marked[v]) {
        visit(v);
      }
      if (!marked[w]) {
        visit(w);
      }
    }
  }

  /**
   * 对任意不属于最小生成树的顶点将其加入的同时，将与其关联且未加入最小生成树的顶点对应的边加入小顶堆
   *
   * @param v 要加入最小生成树的顶点
   */
  private void visit(int v) {
    marked[v] = true;
    for (Edge e : g.adj(v)) {
      if (!marked[e.other(v)]) {
        minEdges.insert(e);
      }
    }
  }
}
