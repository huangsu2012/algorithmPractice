package com.huangsu.algorithm.struct.graph.weighted;

import com.huangsu.algorithm.struct.queue.Queue;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 *
 * 最小生成树抽象
 *
 * 图的生成树是指包含该图的所有顶点的无环连通子图，这意味着非连通图不存在生成树
 *
 * 加权无向图的最小生成树是指权值最小的生成树
 *
 * 图的切分是指将图中所有顶点分成两个非空且不重合的两个集合
 *
 * 横切边是一条连接两个属于不同集合顶点的边
 */
public abstract class AbstractMST implements MST {

  protected final EdgeWeightedGraph g;
  //构成最小生成树的边
  protected Queue<Edge> mst;
  //最小生成树的总权重
  protected double weight;

  protected AbstractMST(EdgeWeightedGraph g) {
    this.g = g;
  }


  @Override
  public Iterable<Edge> edges() {
    return mst;
  }

  @Override
  public double weight() {
    return weight;
  }
}
