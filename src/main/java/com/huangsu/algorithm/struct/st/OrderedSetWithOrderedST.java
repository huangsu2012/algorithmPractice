package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 */
public class OrderedSetWithOrderedST<Key> implements OrderedSet<Key> {

  private final OrderedST<Key, Void> st;

  public OrderedSetWithOrderedST(OrderedST<Key, Void> st) {
    this.st = st;
  }

  @Override
  public Key min() {
    return st.min();
  }

  @Override
  public Key max() {
    return st.max();
  }

  @Override
  public Key floor(Key key) {
    return st.floor(key);
  }

  @Override
  public Key ceiling(Key key) {
    return st.ceiling(key);
  }

  @Override
  public int rank(Key key) {
    return st.rank(key);
  }

  @Override
  public Key select(int k) {
    return st.select(k);
  }

  @Override
  public void deleteMin() {
    st.deleteMin();
  }

  @Override
  public void deleteMax() {
    st.deleteMax();
  }

  @Override
  public int size(Key lo, Key hi) {
    return st.size(lo, hi);
  }

  @Override
  public Iterable<Key> keys(Key lo, Key hi) {
    return st.keys(lo, hi);
  }

  @Override
  public boolean add(Key key) {
    boolean contains = st.contains(key);
    if (!contains) {
      st.put(key, null);
    }
    return contains;
  }

  @Override
  public void delete(Key key) {
    st.delete(key);
  }

  @Override
  public boolean contains(Key key) {
    return st.contains(key);
  }

  @Override
  public boolean isEmpty() {
    return st.isEmpty();
  }

  @Override
  public int size() {
    return st.size();
  }

  @Override
  public Iterable<Key> keys() {
    return st.keys();
  }
}
