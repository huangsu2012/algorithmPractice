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
        while (!stack.isEmpty()) {
          int v = stack.pop();
          marked[v] = true;
          for (int w : g.adj(v)) {
            if (!marked[w]) {
              stack.push(w);
            } else if (w != s) {//不等于起点又已经访问过，说明绕回到了之前访问过的点，也就是存在环
              hasCycle = true;
              break;
            }
          }
        }
        if (hasCycle) {
          break;
        }
      }
    }
  }

  public boolean hasCycle() {
    return hasCycle;
  }
}
