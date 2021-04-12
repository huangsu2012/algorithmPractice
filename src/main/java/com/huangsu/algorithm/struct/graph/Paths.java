package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/3/28.
 *
 * 无向图路径查找
 */
public abstract class Paths {

  final Graph g;
  final int s;
  final boolean[] marked;
  /**
   * 下标为顶点，对应值为与该顶点相连的顶点
   */
  final int[] edgeTo;

  protected Paths(Graph g, int s) {
    this.g = g;
    this.s = s;
    this.marked = new boolean[g.V()];
    this.edgeTo = new int[g.V()];
    doSearchPaths();
  }

  protected abstract void doSearchPaths();

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    Stack<Integer> path = new LinkedStack<>();
    int w = v;
    while (w != s) {
      path.push(w);
      w = edgeTo[w];
    }
    path.push(s);
    return path;
  }
}
