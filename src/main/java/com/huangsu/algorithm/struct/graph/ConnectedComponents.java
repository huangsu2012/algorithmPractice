package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;
import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2021/3/28.
 *
 * 无向图连通分量api
 */
public class ConnectedComponents {

  final Graph g;
  final int[] ids;
  //  final boolean[] marked;
  int count = 0;

  public ConnectedComponents(Graph g) {
    this.g = g;
    ids = new int[g.V()];
//    marked = new boolean[g.V()];
    Arrays.fill(ids, -1);
    findConnectedComponents();
  }

//  protected void findConnectedComponents() {
//    Stack<Integer> stack = new LinkedStack<>();
//    for (int s = 0; s < g.V(); s++) {
//      if (!marked[s]) {
//        stack.push(s);
//        while (!stack.isEmpty()) {
//          int v = stack.pop();
//          marked[v] = true;
//          ids[v] = count;
//          for (int w : g.adj(v)) {
//            if (!marked[w]) {
//              stack.push(w);
//            }
//          }
//        }
//        ++count;
//      }
//    }
//  }

  protected void findConnectedComponents() {
    Stack<Integer> stack = new LinkedStack<>();
    for (int s = 0; s < g.V(); s++) {
      if (notMarked(s)) {
        stack.push(s);
        while (!stack.isEmpty()) {
          int v = stack.pop();
          ids[v] = count;
          for (int w : g.adj(v)) {
            if (notMarked(w)) {
              stack.push(w);
            }
          }
        }
        ++count;
      }
    }
  }

  private boolean notMarked(int v) {
    return ids[v] >= 0;
  }

  /**
   * 顶点v和顶点w是连通的吗
   *
   * @param v 顶点v
   * @param w 顶点w
   * @return 如果v和w连通返回true
   */
  public boolean connected(int v, int w) {
    return id(v) == id(w);
  }

  /**
   * @return 返回连通分量的个数
   */
  public int count() {
    return count;
  }

  /**
   * @param v 顶点v
   * @return 顶点v所在连通分量的标识符
   */
  public int id(int v) {
    return ids[v];
  }
}
