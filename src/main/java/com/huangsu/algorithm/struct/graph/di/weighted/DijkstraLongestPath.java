package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.priorityqueue.ArrayHeapIndexMinPriorityQueue;
import com.huangsu.algorithm.struct.priorityqueue.IndexMinPriorityQueue;

/**
 * Created by huangsu2012@gmail.com on 2021/4/28.
 *
 * dijkstra算法查找最长路径，只对非负权重有向图有效
 */
public class DijkstraLongestPath extends AbstractEdgeWeightedDigraphPath {

  //索引为顶点，值为权重的负值
  private final IndexMinPriorityQueue<Double> minPriorityQueue;

  public DijkstraLongestPath(
      EdgeWeightedDigraph g, int s) {
    super(g, s, false);
    minPriorityQueue = new ArrayHeapIndexMinPriorityQueue<>();
  }

  @Override
  protected void findPaths() {
    minPriorityQueue.insert(s, 0d);
    while (!minPriorityQueue.isEmpty()) {//这里为什么不需要判断一个顶点是否被访问过？
      // 因为如果一条边任意一个顶点之前被访问过意味着存在环，在权重非负的情况下在环中的顶点（自环这种极端情况也是ok的）是没有机会第二次加入队列的，这是由relax方法保证的
      relax(minPriorityQueue.delMin());
    }
  }

  @Override
  protected void relax(DirectedEdge e) {
    int v = e.from(), w = e.to();
    if (distTo[w] < distTo[v] + e.weight()) {
      distTo[w] = distTo[v] + e.weight();
      edgeTo[w] = e;
      if (minPriorityQueue.contains(w)) {
        minPriorityQueue.change(w, -distTo[w]);
      } else {
        minPriorityQueue.insert(w, -distTo[w]);
      }
    }
  }
}
