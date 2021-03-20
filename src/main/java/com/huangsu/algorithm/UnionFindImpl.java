package com.huangsu.algorithm;

/**
 * Created by huangsu2012@gmail.com on 2018/4/12.
 */
public class UnionFindImpl implements UnionFind {

  private int[][] connected;

  private int componentCount;

  public UnionFindImpl(int n) {
    connected = new int[n][1];
    for (int i = 0; i < n; i++) {
      connected[i][0] = i;
    }
    componentCount = n;
  }

  @Override
  public void union(int p, int q) {
    int pIndex = -1;
    int qIndex = -1;
    for (int i = 0; i < componentCount; i++) {
      int[] nodes = connected[i];
      for (int j = 0; j < nodes.length; j++) {
        if (nodes[j] == p) {
          pIndex = i;
        } else if (nodes[j] == q) {
          qIndex = i;
        }
      }
    }
    if (pIndex != -1 && qIndex != -1) {
      if (pIndex == qIndex) {
        return;
      }
      int[] pComponent = connected[pIndex];
      int[] qComponent = connected[qIndex];
      int[] newComponent = new int[pComponent.length + qComponent.length];
      System.arraycopy(pComponent, 0, newComponent, 0, pComponent.length);
      System.arraycopy(qComponent, 0, newComponent, pComponent.length, qComponent.length);
      if (pIndex < qIndex) {
        connected[pIndex] = newComponent;
        connected[qIndex] = null;
      } else {
        connected[qIndex] = newComponent;
        connected[pIndex] = null;
      }
      --componentCount;
    }
  }

  @Override
  public int find(int p) {
    int pIndex = -1;
    for (int i = 0; i < componentCount; i++) {
      int[] nodes = connected[i];
      for (int j = 0; j < nodes.length; j++) {
        if (nodes[j] == p) {
          pIndex = i;
          break;
        }
      }
    }
    return pIndex;
  }

  @Override
  public boolean connected(int p, int q) {
    int pIndex = -1;
    int qIndex = -1;
    for (int i = 0; i < componentCount; i++) {
      int[] nodes = connected[i];
      for (int j = 0; j < nodes.length; j++) {
        if (nodes[j] == p) {
          pIndex = i;
        } else if (nodes[j] == q) {
          qIndex = i;
        }
      }
    }
    return pIndex != -1 && qIndex != -1;
  }

  @Override
  public int count() {
    return componentCount;
  }

  public static void main(String[] args) {
    int n = 10000;
    UnionFind unionFind = new UnionFindImpl(n);

  }
}
