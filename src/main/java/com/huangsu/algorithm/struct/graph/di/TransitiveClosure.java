package com.huangsu.algorithm.struct.graph.di;

/**
 * Created by huangsu2012@gmail.com on 2021/4/19.
 *
 * 有向图顶点对可达性问题
 */
public class TransitiveClosure {

  private final Digraph g;
  private final DirectedDFS[] all;

  public TransitiveClosure(Digraph g) {
    this.g = g;
    all = new DirectedDFS[g.V()];
    for (int v = 0; v < g.V(); v++) {
      all[v] = new DirectedDFS(g, v);
    }
  }

  /**
   * @param v 顶点v
   * @param w 顶点w
   * @return true 如果存在v->w的路径
   */
  public boolean reachable(int v, int w) {
    return all[v].marked(w);
  }
}
