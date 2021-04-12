package com.huangsu.algorithm.struct.graph;

/**
 * Created by huangsu2012@gmail.com on 2021/3/27.
 */
public interface Graph {

  /**
   *
   * @return 顶点数
   */
  int V();

  /**
   *
   * @return 边数
   */
  int E();

  /**
   * 在v与w之间添加一条边
   * @param v 顶点v
   * @param w 顶点w
   */
  void addEdge(int v,int w);
  /**
   * 返回与v相邻的所有顶点
   */
  Iterable<Integer> adj(int v);
}
