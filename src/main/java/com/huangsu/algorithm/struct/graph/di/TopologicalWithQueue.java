package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/4/18.
 *
 * 有向图无环图拓扑排序-基于队列的实现方式
 */
public class TopologicalWithQueue extends AbstractTopological {

  public TopologicalWithQueue(Digraph g) {
    super(g);
  }

  @Override
  Iterable<Integer> findOrder(Digraph g) {
    final int[] indegrees = new int[g.V()];
    Queue<Integer> queue = new LinkedQueue<>();
    Queue<Integer> order = new LinkedQueue<>();
    for (int v = 0; v < g.V(); v++) {
      for (int w : g.adj(v)) {
        indegrees[w] += 1;
      }
    }
    for (int v = 0; v < g.V(); v++) {
      if (indegrees[v] == 0) {
        queue.enqueue(v);
      }
    }
    boolean[] marked = new boolean[g.V()];
    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      if (marked[v]) {
        continue;
      }
      marked[v] = true;
      order.enqueue(v);
      for (int w : g.adj(v)) {
        indegrees[w] -= 1;
        if (indegrees[w] == 0) {
          queue.enqueue(w);
        }
      }
    }
    if (order.size() != g.V()) {
      return null;
    }
    return order;
  }
}
