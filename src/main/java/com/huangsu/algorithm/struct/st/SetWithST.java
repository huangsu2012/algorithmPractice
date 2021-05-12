package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 */
public class SetWithST<Key> implements Set<Key> {

  private final ST<Key, Void> st;

  public SetWithST(ST<Key, Void> st) {
    this.st = st;
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
