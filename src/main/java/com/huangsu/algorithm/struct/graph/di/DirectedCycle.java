package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/4/13.
 */
public class DirectedCycle {

  private final Digraph g;
  //下标为相应的顶点，值为指向该顶点的顶点
  private final int[] edgeTo;
  //标识顶点是否被访问过
  private final boolean[] marked;
  //标识顶点是否在深度遍历的栈中
  private final boolean[] onStack;
  private Stack<Integer> cycleVertices;

  public DirectedCycle(Digraph g) {
    this.g = g;
    edgeTo = new int[g.V()];
    marked = new boolean[g.V()];
    onStack = new boolean[g.V()];
    findCycle();
  }

  //寻找有向图环的非递归实现，注意和递归实现对比
  private void findCycle() {
    Stack<Integer> stack = new LinkedStack<>();
    for (int s = 0; s < g.V(); s++) {
      if (!marked[s]) {
        stack.push(s);
        onStack[s] = true;
        while (!stack.isEmpty()) {
          int v = stack.peek();
          marked[v] = true;
          boolean shouldKeepVOnStack = false;//如果顶点v没有指向未被访问的顶点，此时顶点v就应该从遍历栈移除，因为s->v的路径已经走完了
          for (int w : g.adj(v)) {
            if (hasCycle()) {
              return;
            }
            if (!marked[w]) {
              shouldKeepVOnStack = true;
              stack.push(w);
              onStack[w] = true;
              edgeTo[w] = v;
            } else if (onStack[w]) {//当存在边v->w时，且w已经在深度遍历的栈中时，说明环是存在的；注意这里和无向图判断是否存在环最大的不同，
              // 无向图中是如果w已经被访问过，且该顶点与栈中上一个被访问的顶点不同时就可以说明环是存在的，
              // 可以这样理解，对于无向图如果深度优先遍历找到了路径s-x-w,也找到了路径s-y-w（s指起点，x，y表达任意多的顶点，x,y至少有一个顶点是不同的），那么环s-x-w-y-s就是存在的
              //但是对于有向图这是不成立的
              cycleVertices = new LinkedStack<>();
              for (int x = v; x != w; x = edgeTo[x]) {
                cycleVertices.push(x);
              }
              cycleVertices.push(w);
              cycleVertices.push(v);//既是环的起点也是终点
            }
          }
          if (!shouldKeepVOnStack) {
            stack.pop();
            onStack[v] = false;
          }
        }
      }
    }
  }

//  //寻找有向图环的递归实现
//  private void findCycle() {
//    for (int v = 0; v < g.V(); v++) {
//      dfs(v);
//    }
//  }
//
//  private void dfs(int v) {
//    marked[v] = true;
//    onStack[v] = true;
//    for (int w : g.adj(v)) {
//      if (hasCycle()) {
//        return;
//      }
//      if (!marked[w]) {
//        edgeTo[w] = v;
//        dfs(w);
//      } else if (onStack[w]) {
//        cycleVertices = new LinkedStack<>();
//        for (int x = v; x != w; x = edgeTo[x]) {
//          cycleVertices.push(x);
//        }
//        cycleVertices.push(w);
//        cycleVertices.push(v);
//      }
//    }
//    onStack[v] = false;
//  }

  public boolean hasCycle() {
    return cycleVertices != null;
  }

  public Iterable<Integer> cycle() {
    return cycleVertices;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("DirectedCycle,digraph").append(" ");
    if (hasCycle()) {
      sb.append("find cycle:");
      for (Integer v : cycleVertices) {
        sb.append(v).append("->");
      }
      sb.delete(sb.length() - 2, sb.length());
    } else {
      sb.append("not find cycle");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Digraph graph = new AdjacencyListDigraph(7);
    graph.addEdge(0, 5);
    graph.addEdge(0, 1);
    graph.addEdge(6, 0);
    graph.addEdge(6, 4);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
//    graph.addEdge(3, 2);
    graph.addEdge(5, 4);
//    graph.addEdge(3, 5);
    graph.addEdge(4, 3);
    graph.addEdge(4, 2);
    System.out.println(new DirectedCycle(graph));
  }

}
