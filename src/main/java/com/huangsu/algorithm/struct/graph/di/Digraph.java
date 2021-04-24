package com.huangsu.algorithm.struct.graph.di;

import com.huangsu.algorithm.struct.graph.Graph;

/**
 * Created by huangsu2012@gmail.com on 2021/4/12.
 */
public interface Digraph extends Graph {

  /**
   * @return 该有向图的反向图
   */
  Digraph reverse();
}
