package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/4/12.
 *
 * 可达性api
 */
public class DirectedDFS {

  /**
   * 有向图
   */
  private final Digraph G;
  private final boolean[] marked;


  public DirectedDFS(Digraph g, int s) {
    this(g, new int[]{s});
  }

  public DirectedDFS(Digraph g, int[] sources) {
    G = g;
    marked = new boolean[g.V()];
    Stack<Integer> stack = new LinkedStack<>();
    for (int s : sources) {
      stack.push(s);
    }
    markReachable(stack);
  }

  public DirectedDFS(Digraph g, Iterable<Integer> sources) {
    G = g;
    marked = new boolean[g.V()];
    Stack<Integer> stack = new LinkedStack<>();
    for (Integer s : sources) {
      stack.push(s);
    }
    markReachable(stack);
  }

  private void markReachable(Stack<Integer> stack) {
    while (!stack.isEmpty()) {
      int v = stack.pop();
      marked[v] = true;
      for (int w : G.adj(v)) {
        if (!marked[w]) {//该块里面被注释的代码唯一的价值就是为了保持和递归实现一致的顶点访问顺序而已，对于当前问题的解决没有价值
//          Integer sameLevelLastV = null;//对于同一层的顶点我们需要保持其被访问的顺序
//          if (!stack.isEmpty()) {
//            sameLevelLastV = stack.pop();
//          }
          stack.push(w);
//          if (sameLevelLastV != null) {
//            stack.push(sameLevelLastV);
//          }
        }
      }
    }
  }

  public boolean marked(int v) {
    return marked[v];
  }

}
