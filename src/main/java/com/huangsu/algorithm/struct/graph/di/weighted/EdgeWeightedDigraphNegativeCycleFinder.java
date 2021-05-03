package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/5/2.
 */
public class EdgeWeightedDigraphNegativeCycleFinder implements EdgeWeightedDigraphNegativeCycle {

  private final EdgeWeightedDigraph g;
  private Stack<DirectedEdge> negativeCycle;

  public EdgeWeightedDigraphNegativeCycleFinder(
      EdgeWeightedDigraph g) {
    this.g = g;
    findNegativeCycle();
  }

  private void findNegativeCycle() {
    final boolean[] marked = new boolean[g.V()];
    final boolean[] onStack = new boolean[g.V()];
    final DirectedEdge[] edgeTo = new DirectedEdge[g.V()];
    final Stack<Integer> stack = new LinkedStack<>();
    for (int s = 0; s < g.V(); s++) {
      if (marked[s]) {
        continue;
      }
      stack.push(s);
      onStack[s] = true;
      while (!stack.isEmpty()) {
        int v = stack.peek();
        marked[v] = true;
        boolean shouldPop = true;
        for (DirectedEdge edge : g.adj(v)) {
          int w = edge.to();
          if (!marked[w]) {
            stack.push(w);
            onStack[w] = true;
            edgeTo[w] = edge;
            shouldPop = false;
            break;
          } else if (onStack[w]) {
            DirectedEdge cycleEdge;
            double weight = 0;
            int x = v;
            while (x != w) {
              cycleEdge = edgeTo[x];
              weight += cycleEdge.weight();
              x = cycleEdge.from();
            }
            weight += edge.weight();

            if (weight < 0) {
              negativeCycle = new LinkedStack<>();
              x = v;
              while (x != w) {
                cycleEdge = edgeTo[x];
                negativeCycle.push(cycleEdge);
                x = cycleEdge.from();
              }
              negativeCycle.push(edge);
              return;
            }
          }
        }
        if (shouldPop) {
          stack.pop();
          onStack[v] = false;
        }
      }
    }
  }

  @Override
  public boolean hasNegativeCycle() {
    return negativeCycle != null;
  }

  @Override
  public Iterable<DirectedEdge> negativeCycle() {
    return negativeCycle;
  }
}
