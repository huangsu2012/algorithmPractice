package com.huangsu.algorithm.struct.graph.di.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/24.
 *
 * 加权有向边
 */
public class DirectedEdge implements Comparable<DirectedEdge> {

  private final int from;

  private final int to;

  private final double weight;

  public DirectedEdge(int from, int to, double weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  public int from() {
    return from;
  }

  public int to() {
    return to;
  }

  public double weight() {
    return weight;
  }

  @Override
  public int compareTo(DirectedEdge o) {
    return Double.compare(weight, o.weight);
  }
}
