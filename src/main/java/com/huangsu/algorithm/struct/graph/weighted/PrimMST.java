package com.huangsu.algorithm.struct.graph.weighted;

import com.huangsu.algorithm.struct.priorityqueue.ArrayHeapIndexMinPriorityQueue;
import com.huangsu.algorithm.struct.priorityqueue.IndexMinPriorityQueue;
import com.huangsu.algorithm.struct.queue.LinkedQueue;
import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2021/4/23.
 *
 * prim即时版实现，相比于延时版本，即时版本只会将非树顶点的最小权重边加到优先队列中
 */
public class PrimMST extends AbstractMST {

  //索引为顶点，值为到达该顶点的权值最小的边对应的权值
  private final double[] distTo;
  //索引为顶点，值为到达该顶点的权值最小的边
  private final Edge[] edgeTo;
  //标识顶点是否访问过，在这个算法中表达的是某个顶点是否加入到最小生成树中
  private final boolean[] marked;
  //索引为顶点，值为权重
  private final IndexMinPriorityQueue<Double> minPriorityQueue;


  public PrimMST(EdgeWeightedGraph g) {
    super(g);
    distTo = new double[g.V()];
    edgeTo = new Edge[g.V()];
    Arrays.fill(distTo, Double.POSITIVE_INFINITY);
    minPriorityQueue = new ArrayHeapIndexMinPriorityQueue<>(g.V());
    marked = new boolean[g.V()];
    findMST();
  }

  private void findMST() {
    distTo[0] = 0;
    minPriorityQueue.insert(0, 0D);
    while (!minPriorityQueue.isEmpty()) {
      int v = minPriorityQueue.delMin();
      if (!marked[v]) {
        visit(v);
      }
    }
  }

  private void visit(int v) {
    marked[v] = true;
    for (Edge e : g.adj(v)) {
      int w = e.other(v);
      if (!marked[w]) {//如果w被标记意味着v-w这条边已经访问过了
        if (e.weight() < distTo[w]) {//
          edgeTo[w] = e;
          distTo[w] = e.weight();
          if (minPriorityQueue.contains(w)) {
            minPriorityQueue.change(w, e.weight());
          } else {
            minPriorityQueue.insert(w, e.weight());
          }
        }
      }
    }
  }

  @Override
  public Iterable<Edge> edges() {
    if (mst == null) {
      mst = new LinkedQueue<>();
      for (Edge e : edgeTo) {
        if (e != null) {
          mst.enqueue(e);
          weight += e.weight();
        }
      }
    }
    return mst;
  }
}
