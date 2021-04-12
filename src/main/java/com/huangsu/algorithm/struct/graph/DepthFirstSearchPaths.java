package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/3/28.
 *
 * 使用深度优先的无向图路径查找
 */
public class DepthFirstSearchPaths extends Paths {

  protected DepthFirstSearchPaths(Graph g, int s) {
    super(g, s);
  }

  @Override
  protected void doSearchPaths() {
    Stack<Integer> stack = new LinkedStack<>();
    stack.push(s);
    while (!stack.isEmpty()) {
      int v = stack.pop();
      marked[v] = true;
      for (int w : g.adj(v)) {
        if (!marked[w]) {//该块里面被注释的代码唯一的价值就是为了保持和递归实现一致的顶点访问顺序而已，对于当前问题的解决没有意义
//          Integer sameLevelLastV = null;//对于同一层的顶点我们需要保持其被访问的顺序
//          if (!stack.isEmpty()) {
//            sameLevelLastV = stack.pop();
//          }
          stack.push(w);
//          if (sameLevelLastV != null) {
//            stack.push(sameLevelLastV);
//          }
          edgeTo[w] = v;
        }
      }
    }
  }
}
