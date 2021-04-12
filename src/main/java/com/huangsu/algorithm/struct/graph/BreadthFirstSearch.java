package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/3/27.
 *
 * 广度优先搜索实现
 */
public class BreadthFirstSearch extends Search {

  public BreadthFirstSearch(Graph g, int s) {
    super(g, s);
  }

  @Override
  protected void doSearch() {
    Queue<Integer> queue = new LinkedQueue<>();
    queue.enqueue(s);
    marked[s] = true;
    markedCount = 1;
    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      for (int w : g.adj(v)) {
        if (!marked[w]) {
          queue.enqueue(w);
          marked[w] = true;
          ++markedCount;
        }
      }
    }
  }
}
