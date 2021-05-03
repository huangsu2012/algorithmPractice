package com.huangsu.algorithm.util;

import com.huangsu.algorithm.struct.graph.Graph;

/**
 * Created by huangsu2012@gmail.com on 2021/3/27.
 */
public abstract class GraphUtils {

  /**
   * @param graph 无向图
   * @param v 顶点v
   * @return 顶点v的度数(其实就是与顶点v相邻的顶点数)
   */
  public static int degree(Graph graph, int v) {
    int degree = 0;
    for (Integer ignored : graph.adj(v)) {
      ++degree;
    }
    return degree;
  }

  /**
   * @param graph 无向图
   * @return 所有顶点的最大度数
   */
  public static int maxDegree(Graph graph) {
    int max = 0;
    for (int i = 0; i < graph.V(); i++) {
      int d = degree(graph, i);
      if (d > max) {
        max = d;
      }
    }
    return max;
  }

  /**
   * @param graph 无向图
   * @return 所有顶点的平均度数
   */
  public static int avgDegree(Graph graph) {
    return 2 * graph.E() / graph.V();//对于任意两个相连的顶点，一条边都会增加各个顶点一度的值，所有总的度数就是边数乘以2
  }

  /**
   * @param graph 无向图
   * @return 自环的个数
   */
  public static int numberOfSelfLoops(Graph graph) {
    int loopCount = 0;
    for (int i = 0; i < graph.V(); i++) {
      for (int j : graph.adj(i)) {
        if (i == j) {
          ++loopCount;
          break;
        }
      }
    }
    return loopCount / 2;//因为每条边被遍历过两次
  }

  public static String toString(Graph graph) {
    StringBuilder sb = new StringBuilder();
    sb.append(graph.V()).append(" ").append("vertices,").append(graph.E()).append(" ")
        .append("edges\n");
    for (int i = 0; i < graph.V(); i++) {
      sb.append(i).append(": ");
      for (int j : graph.adj(i)) {
        sb.append(j).append(",");
      }
    }
    return sb.toString();
  }
}
