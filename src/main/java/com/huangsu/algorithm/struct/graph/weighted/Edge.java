package com.huangsu.algorithm.struct.graph.weighted;

import java.util.Objects;

/**
 * Created by huangsu2012@gmail.com on 2021/4/22.
 *
 * 带权值的边
 */
public class Edge implements Comparable<Edge> {

  private final int v;
  private final int w;
  private final double weight;

  public Edge(int v, int w, double weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  public double weight() {
    return weight;
  }

  /**
   * @return 边两端顶点之一
   */
  public int either() {
    return v;
  }

  /**
   * @return 与v不同的另一个顶点
   */
  public int other(int v) {
    if (this.v == v) {
      return this.w;
    }
    if (this.w == v) {
      return this.v;
    }
    throw new RuntimeException("Inconsistent edge");
  }

  @Override
  public int compareTo(Edge o) {
    return Double.compare(weight, o.weight);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Edge edge = (Edge) o;
    return v == edge.v &&
        w == edge.w;
  }

  @Override
  public int hashCode() {
    return Objects.hash(v, w);
  }
}
