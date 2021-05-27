package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSkipListOrdered.SkipListNodeSet;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/5.
 *
 * 基于跳表实现的有序Set
 */
public class SkipListOrderedSet<Key> extends
    AbstractSkipListOrdered<Key, Void, SkipListNodeSet<Key>> implements OrderedSet<Key> {

  public SkipListOrderedSet() {
    super();
  }

  public SkipListOrderedSet(Comparator<Key> comparator) {
    super(comparator);
  }

  public SkipListOrderedSet(int maxLevel, double p, Comparator<Key> comparator) {
    super(maxLevel, p, comparator);
  }

  @Override
  protected SkipListNodeSet<Key> insertCreateNode(Key key, Void aVoid, int toInsertLevel) {
    return new SkipListNodeSet<>(key, toInsertLevel);
  }

  @Override
  protected void insertReplaceNodeValue(SkipListNodeSet<Key> node, Void aVoid) {

  }

  @Override
  public boolean add(Key key) {
    return insert(key, null);
  }

  @Override
  public Iterator<Key> iterator() {
    return keys().iterator();
  }
}
