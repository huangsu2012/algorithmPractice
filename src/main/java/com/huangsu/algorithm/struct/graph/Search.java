package com.huangsu.algorithm.struct.graph;

/**
 * Created by huangsu2012@gmail.com on 2021/3/27.
 */
public abstract class Search {

  final Graph g;

  final int s;

  final boolean[] marked;

  int markedCount = -1;

  public Search(Graph g, int s) {
    this.g = g;
    this.s = s;
    marked = new boolean[g.V()];
    doSearch();
  }

  protected abstract void doSearch();

  /**
   * @param v 待查找顶点
   * @return 对于给点的图以及起点，如果起点与v相连返回true
   */
  public boolean marked(int v) {
    return marked[v];
  }

  /**
   * @return 与起点连通的总数
   */
  public int count() {
    if (markedCount < 0) {
      markedCount = 0;
      for (int i = 0; i < g.V(); i++) {
        if (marked[i]) {
          ++markedCount;
        }
      }
    }
    return markedCount;
  }
}
