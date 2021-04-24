package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/4/19.
 *
 * 有向图强连通分量api，顶点v与w强连通意味着存在v到w的路径且存在w到v的路径（其实就是w，v之间存在环） 每个顶点都是和自己强连通的
 *
 * 对于一个单向无环有向图（其实就是有向无环图拓扑排序后的结果），如何遍历其强连通分量，最合理的方式是逆向遍历
 *
 * 而如果我们将所有的强连通分量都看作一个顶点，那么这些强连通分量将构成一个单向无环的有向图。
 *
 * 从没有指向其它强连通分量的强连通分量开始，这样做dfs的时候一定是在同一个连通分量里面做，不会跑到下一个连通分量中。
 */
public class KosarajuSCC {

  private final int[] ids;
  private final boolean[] marked;
  private int count;
  private final Digraph g;

  public KosarajuSCC(Digraph g) {
    this.g = g;
    ids = new int[g.V()];
    marked = new boolean[g.V()];
    findStronglyConnected();
  }

  //非递归实现版本
  private void findStronglyConnected() {
    DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g.reverse());
    Iterable<Integer> reversePostOrder = depthFirstOrder.getReversePostOrder();
    Stack<Integer> stack = new LinkedStack<>();
    for (int s : reversePostOrder) {
      if (!marked[s]) {
        stack.push(s);
        while (!stack.isEmpty()) {
          int v = stack.peek();
          marked[v] = true;
          ids[v] = count;
          boolean shouldKeepVOnStack = false;//如果顶点v没有指向未被访问的顶点，此时顶点v就应该从遍历栈移除，因为s->v的路径已经走完了
          for (int w : g.adj(v)) {
            if (!marked[w]) {
              stack.push(w);
              shouldKeepVOnStack = true;
            }
          }
          if (!shouldKeepVOnStack) {
            stack.pop();
          }
        }
        ++count;
      }
    }
  }

//  //递归实现版本
//  private void findStronglyConnected() {
//    DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g.reverse());
//    Iterable<Integer> reversePostOrder = depthFirstOrder.getReversePostOrder();
//    for (int v : reversePostOrder) {
//      if (!marked[v]) {
//        dfs(v);
//        ++count;
//      }
//    }
//  }
//
//  private void dfs(int v) {
//    marked[v] = true;
//    ids[v] = count;
//    for (int w : g.adj(v)) {
//      if (!marked[w]) {
//        dfs(w);
//      }
//    }
//
//  }

  /**
   * @param v 顶点v
   * @param w 顶点w
   * @return true 如果v和w是强连通的
   */
  public boolean stronglyConnected(int v, int w) {
    return ids[v] == ids[w];
  }

  /**
   * @return 强连通分量的总数
   */
  public int count() {
    return count;
  }

  /**
   * @param v 顶点v
   * @return v所在强连通分量的标识符
   */
  public int id(int v) {
    return ids[v];
  }
}
