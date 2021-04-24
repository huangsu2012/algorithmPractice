package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/4/18.
 *
 * 有向图顶点度相关的api
 *
 * 顶点的入度为指向该顶点的边的总数
 *
 * 顶点的出度为由该顶点指出的边的总数
 *
 * 出度为0的顶点为终点，因为其不可能到达任何顶点
 *
 * 入度为0的顶点叫起点，因为其不可能从任何顶点到达
 *
 * 一幅允许出现自环且每个顶点的出度均为1的有向图叫做映射
 */
public class Degrees {

  private final Digraph g;
  private final int[] indegrees;
  private final int[] outdegrees;
  private Queue<Integer> sources;
  private Queue<Integer> sinks;
  private boolean isMap;

  public Degrees(Digraph g) {
    this.g = g;
    indegrees = new int[g.V()];
    outdegrees = new int[g.V()];
    sources = new LinkedQueue<>();
    sinks = new LinkedQueue<>();
    traverseVertexes();
  }

  private void traverseVertexes() {
    isMap = g.V() > 0;
    for (int v = 0; v < g.V(); v++) {
      int outdegree = outdegrees[v];
      for (int w : g.adj(v)) {
        outdegree++;
        indegrees[w] += 1;
      }
      outdegrees[v] = outdegree;
      if (outdegree == 0) {
        sinks.enqueue(v);
      }
      if (outdegree != 1) {
        isMap = false;
      }
    }
    for (int indegree : indegrees) {
      if (indegree == 0) {
        sources.enqueue(indegree);
      }
    }
  }

  /**
   * @param v 顶点
   * @return 顶点v的入度
   */
  public int indegree(int v) {
    return indegrees[v];
  }

  /**
   * @param v 顶点
   * @return 顶点v的出度
   */
  public int outdegree(int v) {
    return outdegrees[v];
  }

  /**
   * @return 所有起点的集合
   */
  public Iterable<Integer> sources() {
    return sources;
  }

  /**
   * @return 所有终点的集合
   */
  public Iterable<Integer> sinks() {
    return sinks;
  }

  public boolean isMap() {
    return isMap;
  }
}
