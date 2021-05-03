package com.huangsu.algorithm.struct.graph.di.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/5/2.
 *
 * 检测加权有向图中是否存在负权重环
 */
public interface EdgeWeightedDigraphNegativeCycle {

  boolean hasNegativeCycle();

  Iterable<DirectedEdge> negativeCycle();
}
