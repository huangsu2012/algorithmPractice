package com.huangsu.algorithm.struct.graph.di;

/**
 * Created by huangsu2012@gmail.com on 2021/4/18.
 *
 * 有向图无环图拓扑排序抽象
 */
abstract class AbstractTopological {

  final Iterable<Integer> order;

  AbstractTopological(Digraph g) {
    order = findOrder(g);
  }

  abstract Iterable<Integer> findOrder(Digraph g);

  /**
   * @return true 如果是无环图的话
   */
  public boolean isDAG() {
    return order != null;
  }

  /**
   * @return 拓扑有序的所有顶点
   */
  public Iterable<Integer> order() {
    return order;
  }
}
