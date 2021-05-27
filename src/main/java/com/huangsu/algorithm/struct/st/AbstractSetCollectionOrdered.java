package com.huangsu.algorithm.struct.st;

import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/19.
 */
public abstract class AbstractSetCollectionOrdered<Key> implements SetCollectionOrdered<Key> {

  protected final Comparator<Key> keyComparator;

  public AbstractSetCollectionOrdered() {
    this(null);
  }

  protected AbstractSetCollectionOrdered(Comparator<Key> keyComparator) {
    this.keyComparator = keyComparator;
  }
}
