package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;
import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2021/4/28.
 *
 * 加权有向图最短/最长路径抽象
 */
public abstract class AbstractEdgeWeightedDigraphPath implements EdgeWeightedDigraphPath {

  /**
   * 加权有向图
   */
  protected final EdgeWeightedDigraph g;
  /**
   * 起点
   */
  protected final int s;
  //索引为顶点，值为到达该顶点的权值最小/最大的边
  protected final DirectedEdge[] edgeTo;
  //索引为顶点，值为到达该顶点的权值最小/最大的边
  protected final double[] distTo;
  //是否为查找最短路径
  protected final boolean shortestPath;

  public AbstractEdgeWeightedDigraphPath(
      EdgeWeightedDigraph g, int s, boolean shortestPath) {
    this.g = g;
    this.s = s;
    this.shortestPath = shortestPath;
    edgeTo = new DirectedEdge[g.V()];
    distTo = new double[g.V()];
    Arrays.fill(distTo, shortestPath ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY);
    distTo[s] = 0;
    findPaths();
  }

  protected abstract void findPaths();

  /**
   * 放松一个顶点指出的所有边
   *
   * @param v 顶点
   */
  protected void relax(int v) {
    for (DirectedEdge e : g.adj(v)) {
      relax(e);
    }
  }

  /**
   * 对于最短路径来说放松一条边类似于将橡皮筋转移到一条更短的路径上，从而缓解橡皮筋的压力
   *
   * 对于最长路径这个方法名可能就不恰当了
   *
   * @param e 要放松的边
   */
  protected void relax(DirectedEdge e) {
    int v = e.from(), w = e.to();
    if (shortestPath) {
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
      }
    } else {
      if (distTo[w] < distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
      }
    }
  }


  /**
   * @param v 顶点v
   * @return 从s到v的最短距离或者无穷大（如果路径不存在的话）
   */
  public double distTo(int v) {
    return distTo[v];
  }

  /**
   * @param v 顶点v
   * @return 从s到v是否存在路径
   */
  public boolean hasPathTo(int v) {
    return shortestPath ? distTo[v] != Double.POSITIVE_INFINITY
        : distTo[v] != Double.NEGATIVE_INFINITY;
  }

  /**
   * @param v 顶点v
   * @return 从s到v的路径
   */
  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    Stack<DirectedEdge> stack = new LinkedStack<>();
    DirectedEdge edge = edgeTo[v];
    while (edge != null) {
      stack.push(edge);
      edge = edgeTo[edge.from()];
    }
    return stack;
  }
}
