package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 */
public class SetWithST<Key> implements Set<Key> {

  private final ST<Key, Key> st;

  public SetWithST(ST<Key, Key> st) {
    this.st = st;
  }

  @Override
  public boolean add(Key key) {
    int oldSize = st.size();
    st.put(key, key);
    return st.size() > oldSize;
  }

  @Override
  public boolean delete(Key key) {
    return st.delete(key) != null;
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
