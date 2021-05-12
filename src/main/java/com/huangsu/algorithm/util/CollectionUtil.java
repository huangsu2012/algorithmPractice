package com.huangsu.algorithm.util;

/**
 * Created by huangsu2012@gmail.com on 2021/5/12.
 */
public abstract class CollectionUtil {

//  public static <T> String toString(Collection<T> coll) {
//    return toString(coll);
//  }

  public static <T> String toString(Iterable<T> coll) {
    if (coll == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(coll.getClass().getSimpleName()).append(":").append("[");
    for (T t : coll) {
      sb.append(t).append(",");
    }
    if (sb.charAt(sb.length() - 1) == ',') {
      sb.delete(sb.length() - 1, sb.length());
    }
    sb.append("]");
    return sb.toString();
  }
}
