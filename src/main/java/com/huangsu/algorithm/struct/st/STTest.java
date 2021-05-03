package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/3/23.
 */
public class STTest {

  public static void main(String[] args) {
    ST<Integer, Integer> st = new SkipListOrderedST<>();
    st.put(3, 3);
    st.put(37, 37);
    st.put(11, 11);
    st.put(19, 19);
    st.put(22, 22);
    st.delete(22);
    Iterable<Integer> iterable = st.keys();
    for (Integer i : iterable) {
      System.out.println(i);
    }
  }
}
