package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/27.
 *
 * 邻接矩阵实现无向图
 */
public class AdjacencyMatrixGraph extends AbstractGraph implements Graph {

  private boolean[][] adj;

  public AdjacencyMatrixGraph(int v) {
    super(v);
    adj = new boolean[v][v];
  }


  @Override
  public void addEdge(int v, int w) {
    boolean edge = adj[v][w];
    if (!edge) {
      adj[v][w] = true;
      adj[w][v] = true;
      ++E;
    }
  }

  @Override
  public Iterable<Integer> adj(int v) {
    Queue<Integer> queue = new LinkedQueue<>();
    for (int i = 0; i < V; i++) {
      boolean edge = adj[v][i];
      if (edge) {
        queue.enqueue(i);
      }
    }
    return queue;
  }
}
