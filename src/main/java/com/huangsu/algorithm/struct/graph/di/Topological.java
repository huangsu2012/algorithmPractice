package com.huangsu.algorithm.struct.graph.di;

/**
 * Created by huangsu2012@gmail.com on 2021/4/16.
 *
 * 有向图无环图拓扑排序-基于深度优先顶点的后序遍历逆序
 */
public class Topological extends AbstractTopological {


  public Topological(Digraph g) {
    super(g);
  }

  @Override
  Iterable<Integer> findOrder(Digraph g) {
    DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g);
    if (!depthFirstOrder.hasCycle()) {
      return depthFirstOrder.getReversePostOrder();
    } else {
      return null;
    }
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Topological,digraph").append(" ");
    if (isDAG()) {
      sb.append("order:");
      for (Integer v : order) {
        sb.append(v).append("->");
      }
      sb.delete(sb.length() - 2, sb.length());
    } else {
      sb.append("find cycle");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Digraph g = new AdjacencyListDigraph(13);
    g.addEdge(0, 1);
    g.addEdge(0, 6);
    g.addEdge(0, 5);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 5);
    g.addEdge(5, 4);
    g.addEdge(6, 4);
    g.addEdge(6, 9);
    g.addEdge(7, 6);
    g.addEdge(8, 7);
    g.addEdge(9, 10);
    g.addEdge(9, 11);
    g.addEdge(9, 12);
    g.addEdge(11, 12);
    System.out.println(new Topological(g));
  }
}
