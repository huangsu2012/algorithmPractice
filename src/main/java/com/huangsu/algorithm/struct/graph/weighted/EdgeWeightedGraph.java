package com.huangsu.algorithm.struct.graph.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 */
public interface EdgeWeightedGraph {

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
  void addEdge(Edge e);

  /**
   * @return 和v相关的所有边
   */
  Iterable<Edge> adj(int v);

  /**
   *
   * @return 图的所有边
   */
  Iterable<Edge> edges();

}
