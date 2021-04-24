package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/3/28.
 *
 * 检测无向图是否存在环
 */
public class Cycle {

  private final Graph g;

  private final boolean[] marked;
  private boolean hasCycle;

  public Cycle(Graph g) {
    this.g = g;
    marked = new boolean[g.V()];
    findCycle();
  }

  private void findCycle() {
    Stack<Integer> stack = new LinkedStack<>();
    for (int s = 0; s < g.V(); s++) {
      if (!marked[s]) {
        stack.push(s);
        int u = s;
        while (!stack.isEmpty()) {
          if (hasCycle) {
            return;
          }
          int v = stack.pop();
          marked[v] = true;
          for (int w : g.adj(v)) {
            if (!marked[w]) {
              stack.push(w);
              u = v;
            } else if (w != u) {//不等于上一个被访问点又已经访问过，说明绕回到了之前访问过的点，也就是存在环
              hasCycle = true;
            }
          }
        }
      }
    }
  }

//  private void findCycle() {
//    for (int s = 0; s < g.V(); s++) {
//      if (!marked[s]) {
//        dfs(s, s);
//      }
//    }
//  }
//
//  private void dfs(int v, int u) {
//    marked[v] = true;
//    for (int w : g.adj(v)) {
//      if (!marked[w]) {
//        dfs(w, v);
//      } else if (w != u) {
//        hasCycle = true;
//      }
//    }
//  }

  public boolean hasCycle() {
    return hasCycle;
  }

  public static void main(String[] args) {
    Graph graph = new AdjacencyListGraph(7);
    graph.addEdge(0, 5);
    graph.addEdge(0, 6);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(5, 3);
    graph.addEdge(5, 4);
    graph.addEdge(3, 4);
    graph.addEdge(6, 4);
    System.out.println(new Cycle(graph).hasCycle());
  }
}
