package com.huangsu.algorithm.struct.graph.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 *
 * 最小生成树
 */
public interface MST {


  /**
   * @return 最小生成树的所有边
   */
  Iterable<Edge> edges();

  /**
   * @return 最小生成树权重
   */
  double weight();

}
