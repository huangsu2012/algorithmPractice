package com.huangsu.algorithm.struct.graph;

import com.huangsu.algorithm.struct.CollectionIterable;
import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.struct.st.ST;
import com.huangsu.algorithm.struct.st.SeparateChainingHashST;

/**
 * Created by huangsu2012@gmail.com on 2021/4/2.
 */
public class SimpleSymbolGraph<K> implements SymbolGraph<K> {

  private final ST<K, Integer> symbolIndexTable;
  private final K[] symbols;
  private final Graph g;

  @SuppressWarnings("unchecked")
  public SimpleSymbolGraph(CollectionIterable<K> vertexes) {
    int count = 0;
    symbolIndexTable = new SeparateChainingHashST<>(vertexes.size());
    for (K k : vertexes) {
      if (symbolIndexTable.contains(k)) {
        continue;
      }
      symbolIndexTable.put(k, count++);
    }
    symbols = (K[]) new Object[count];
    g = new AdjacencyListGraph(count);
    for (K k : symbolIndexTable.keys()) {
      symbols[symbolIndexTable.get(k)] = k;
    }
  }

  @Override
  public int V() {
    return g.V();
  }

  @Override
  public int E() {
    return g.E();
  }

  @SuppressWarnings("Duplicates")
  @Override
  public void addEdge(K v, K w) {
    Integer vIndex = symbolIndexTable.get(v);
    if (vIndex == null) {
      return;
    }
    Integer wIndex = symbolIndexTable.get(w);
    if (wIndex == null) {
      return;
    }
    g.addEdge(vIndex, wIndex);
  }

  @SuppressWarnings("Duplicates")
  @Override
  public Iterable<K> adj(K v) {
    Integer vIndex = symbolIndexTable.get(v);
    if (vIndex != null) {
      Queue<K> queue = new LinkedQueue<>();
      for (int w : g.adj(vIndex)) {
        queue.enqueue(symbols[w]);
      }
      return queue;
    }
    return null;
  }

  @Override
  public K name(int v) {
    return v >= 0 && v < symbols.length ? symbols[v] : null;
  }

  @Override
  public boolean contains(K key) {
    return symbolIndexTable.contains(key);
  }

  @Override
  public int index(K key) {
    return symbolIndexTable.get(key);
  }

  @Override
  public Graph G() {
    return g;
  }
}
