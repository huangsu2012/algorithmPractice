package com.huangsu.algorithm.struct.graph.di.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/26.
 */
public interface EdgeWeightedDigraphPath {

  /**
   * @param v 顶点v
   * @return 从s到v的最短距离或者无穷（如果路径不存在的话）
   */
  double distTo(int v);

  /**
   * @param v 顶点v
   * @return 从s到v是否存在路径
   */
  boolean hasPathTo(int v);

  /**
   * @param v 顶点v
   * @return 从s到v的最短或最长路径
   */
  Iterable<DirectedEdge> pathTo(int v);
}
