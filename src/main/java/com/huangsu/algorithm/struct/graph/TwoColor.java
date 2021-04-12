package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;
import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2021/4/2.
 *
 * 无向图双色问题或者叫二分图问题
 */
public class TwoColor {

  private final byte COLOR_UNDEFINED = -1;
  private final Graph g;
  private final byte[] colors;
  private boolean isTwoColorable;

  public TwoColor(Graph g) {
    this.g = g;
    colors = new byte[g.V()];
    Arrays.fill(colors, COLOR_UNDEFINED);
  }

  public boolean isBipartite() {
    return isTwoColorable;
  }

  private void judgeTwoColor() {
    Stack<Integer> stack = new LinkedStack<>();
    isTwoColorable = true;
    final byte COLOR_0 = 0;
    final byte COLOR_1 = 1;
    for (int s = 0; s < g.V(); s++) {
      if (colors[s] == COLOR_UNDEFINED) {
        colors[s] = COLOR_0;
        stack.push(s);
        while (!stack.isEmpty()) {
          int v = stack.pop();
          for (int w : g.adj(v)) {
            if (colors[w] == COLOR_UNDEFINED) {
              colors[w] = colors[v] == COLOR_0 ? COLOR_1 : COLOR_0;
              stack.push(w);
            } else if (colors[w] == colors[v]) {
              isTwoColorable = false;
              break;
            }
          }
        }
      }
      if (!isTwoColorable) {
        break;
      }
    }
  }
}
