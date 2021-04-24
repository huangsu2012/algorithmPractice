package com.huangsu.algorithm.struct.graph.di;

/**
 * Created by huangsu2012@gmail.com on 2021/4/16.
 *
 * 用符号作为顶点的有向图API
 */
public interface SymbolDigraph<K> {

  /**
   * @return 顶点数
   */
  int V();

  /**
   * @return 边数
   */
  int E();

  /**
   * 在v与w之间添加一条边
   *
   * @param v 顶点v
   * @param w 顶点w
   */
  void addEdge(K v, K w);

  /**
   * 返回与v相邻的所有顶点
   */
  Iterable<K> adj(K v);

  /**
   * @param key 顶点key
   * @return 顶点key对应的索引
   */
  int index(K key);

  /**
   * @param v 索引v
   * @return 索引v的顶点名
   */
  K name(int v);

  boolean contains(K key);

  Digraph G();
}
