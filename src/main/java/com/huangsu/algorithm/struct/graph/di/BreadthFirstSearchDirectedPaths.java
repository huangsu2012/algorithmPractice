package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/28.
 *
 * 使用广度优先的有向图路径查找
 */
public class BreadthFirstSearchDirectedPaths extends DirectedPaths {

  public BreadthFirstSearchDirectedPaths(Digraph g, int s) {
    super(g, s);
  }

  @Override
  protected void doSearchPaths() {
    Queue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(s);
    marked[s] = true;
    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w : g.adj(v)) {
        if (!marked[w]) {
          queue.enqueue(w);
          marked[w] = true;
          edgeTo[w] = v;
        }
      }
    }
  }
}
