package com.huangsu.algorithm.struct.graph.di.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/24.
 *
 * 加权有向图api
 */
public interface EdgeWeightedDigraph {

  /**
   * @return 顶点数
   */
  int V();

  /**
   * @return 边数
   */
  int E();

  /**
   * 向图中添加一条边
   *
   * @param e 要添加的边
   */
  void addEdge(DirectedEdge e);

  /**
   * @return 从v指出的所有边
   */
  Iterable<DirectedEdge> adj(int v);

  /**
   * @return 该有向图的所有边
   */
  Iterable<DirectedEdge> edges();
}
