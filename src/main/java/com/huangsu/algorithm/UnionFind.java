package com.huangsu.algorithm;

/**
 * Created by huangsu2012@gmail.com on 2018/4/12.
 *
 * 连通分量问题抽象
 */
public interface UnionFind {

  /**
   * 添加一个连通分量
   */
  void union(int p, int q);

  /**
   * 查找p所属的分量
   */
  int find(int p);

  /**
   * @return true 如果两个整数是连通的话
   */
  boolean connected(int p, int q);

  /**
   * @return 连通分量的数量
   */
  int count();
}
