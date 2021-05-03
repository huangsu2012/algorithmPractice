package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/4/26.
 *
 * 使用拓扑排序处理加权有向无环图的最短/最长路径问题
 */
public class AcyclicDigraphPath extends AbstractEdgeWeightedDigraphPath {

  protected AcyclicDigraphPath(EdgeWeightedDigraph g, int s, boolean shortestPath) {
    super(g, s, shortestPath);
  }

  @Override
  protected void findPaths() {
    Stack<Integer> topologicalVertexes = getTopologicalVertexes();
    if (topologicalVertexes != null) {
      for (int v : topologicalVertexes) {
        relax(v);
      }
    }
  }


  private Stack<Integer> getTopologicalVertexes() {
    final Stack<Integer> reversePostOrder = new LinkedStack<>();
    final Stack<Integer> stack = new LinkedStack<>();
    final boolean[] marked = new boolean[g.V()];
    final boolean[] onStack = new boolean[g.V()];
    stack.push(s);
    onStack[s] = true;
    while (!stack.isEmpty()) {
      int v = stack.peek();
      boolean shouldPop = true;
      marked[v] = true;
      for (DirectedEdge e : g.adj(v)) {
        int w = e.to();
        if (!marked[w]) {
          shouldPop = false;
          stack.push(w);
          onStack[w] = true;
          break;
        } else if (onStack[w]) {//说明存在环
          return null;
        }
      }
      if (shouldPop) {
        onStack[v] = false;
        stack.pop();
        reversePostOrder.push(v);
      }
    }
    return reversePostOrder;
  }
}
