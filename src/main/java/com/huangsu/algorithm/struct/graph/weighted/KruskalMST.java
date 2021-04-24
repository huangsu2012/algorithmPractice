package com.huangsu.algorithm.struct.graph.weighted;

import com.huangsu.algorithm.UnionFind;
import com.huangsu.algorithm.UnionFindQuickUnion;
import com.huangsu.algorithm.struct.priorityqueue.ArrayHeapMinPriorityQueue;
import com.huangsu.algorithm.struct.priorityqueue.MinPriorityQueue;

/**
 * Created by huangsu2012@gmail.com on 2021/4/23.
 *
 * 加权无向图最小生成树kruskal实现
 *
 * 核心逻辑在于每次选择一条最小的权值的边加入最小生成树，且新加入的边不会导致环的产生
 */
public class KruskalMST extends AbstractMST {


  protected KruskalMST(EdgeWeightedGraph g) {
    super(g);
    findMST();
  }

  private void findMST() {
    MinPriorityQueue<Edge> minEdges = new ArrayHeapMinPriorityQueue<>(g.V());
    UnionFind uf = new UnionFindQuickUnion(g.V());
    for (Edge edge : g.edges()) {
      minEdges.insert(edge);
    }
    while (!minEdges.isEmpty()) {
      Edge e = minEdges.delMin();
      int v = e.either();
      int w = e.other(v);
      if (uf.connected(v, w)) {
        continue;
      }
      uf.union(v, w);
      weight += e.weight();
      mst.enqueue(e);
    }
  }
}
