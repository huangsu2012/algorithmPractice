package com.huangsu.algorithm.struct.graph.di.weighted;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2021/4/16.
 */
public class DepthFirstOrder {

  private final EdgeWeightedDigraph g;
  private final boolean[] marked;
  /**
   * 顶点的前序遍历顺序
   */
  private Queue<Integer> preOrder;
  /**
   * 顶点的后序遍历顺序
   */
  private Queue<Integer> postOrder;
  /**
   * 顶点的后序遍历逆序
   */
  private Stack<Integer> reversePostOrder;
  private boolean hasCycle;

  public DepthFirstOrder(EdgeWeightedDigraph g) {
    this.g = g;
    marked = new boolean[g.V()];
    preOrder = new LinkedQueue<>();
    postOrder = new LinkedQueue<>();
    reversePostOrder = new LinkedStack<>();
    traverseVertexes();
  }

//  /**
//   * 获取有向图顶点遍历顺序的非递归实现，注意最后结果与递归实现的不一致，但是不影响其逆后序对于拓扑排序的正确性
//   * 可以和与递归实现保持一致访问顺序的代码实现做一个对比
//   */
//  private void traverseVertexes() {
//    Stack<Integer> stack = new LinkedStack<>();
//    final boolean[] onStack = new boolean[g.V()];
//    for (int s = 0; s < g.V(); s++) {
//      if (!marked[s]) {
//        stack.push(s);
//        onStack[s] = true;
//        while (!stack.isEmpty()) {
//          int v = stack.peek();
//          boolean shouldKeepVOnStack = false;//如果顶点v没有指向未被访问的顶点，此时顶点v就应该从遍历栈移除，因为s->v的路径已经走完了
//          if (!marked[v]) {//由于是peek所以会存在顶点v已经访问过的情况
//            preOrder.enqueue(v);
//            marked[v] = true;
//            for (int w : g.adj(v)) {
//              if (!marked[w]) {
//                stack.push(w);
//                shouldKeepVOnStack = true;
//                onStack[w] = true;
//              } else if (onStack[w]) {
//                hasCycle = true;
//              }
//            }
//          }
//          if (!shouldKeepVOnStack) {
//            stack.pop();
//            if (onStack[v]) {//非递归实现这个判断是必须的，否则会加入重复顶点
//              postOrder.enqueue(v);
//              reversePostOrder.push(v);
//            }
//            onStack[v] = false;
//          }
//        }
//      }
//    }
//  }

  /**
   * 获取有向图顶点遍历顺序的非递归实现
   */
  private void traverseVertexes() {
    Stack<Integer> stack = new LinkedStack<>();
    final boolean[] onStack = new boolean[g.V()];
    for (int s = 0; s < g.V(); s++) {
      if (!marked[s]) {
        stack.push(s);
        onStack[s] = true;
        while (!stack.isEmpty()) {
          int v = stack.peek();
          boolean shouldKeepVOnStack = false;//如果顶点v没有指向未被访问的顶点，此时顶点v就应该从遍历栈移除，因为s->v的路径已经走完了
          if (!marked[v]) {//由于是peek所以会存在顶点v已经访问过的情况
            preOrder.enqueue(v);
            marked[v] = true;
          }
          for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
              stack.push(w);
              shouldKeepVOnStack = true;
              onStack[w] = true;
              break;//此处break唯一用处就是保持和递归遍历顺序一致
            } else if (onStack[w]) {
              hasCycle = true;
            }
          }
          if (!shouldKeepVOnStack) {
            stack.pop();
            if (onStack[v]) {//非递归实现这个判断是必须的，否则会加入重复顶点
              postOrder.enqueue(v);
              reversePostOrder.push(v);
            }
            onStack[v] = false;
          }
        }
      }
    }
  }

//  //递归实现版本
//  private void traverseVertexes() {
//    for (int v = 0; v < g.V(); v++) {
//      if (!marked[v]) {
//        dfs(v);
//      }
//    }
//  }
//
//  private void dfs(int v) {
//    marked[v] = true;
//    preOrder.enqueue(v);
//    for (int w : g.adj(v)) {
//      if (!marked[w]) {
//        dfs(w);
//      }
//    }
//    postOrder.enqueue(v);
//    reversePostOrder.push(v);
//  }

  public boolean hasCycle() {
    return hasCycle;
  }


  public Iterable<Integer> getPreOrder() {
    return preOrder;
  }

  public Iterable<Integer> getPostOrder() {
    return postOrder;
  }

  public Iterable<Integer> getReversePostOrder() {
    return reversePostOrder;
  }
}
