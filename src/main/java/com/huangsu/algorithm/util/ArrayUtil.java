package com.huangsu.algorithm.util;

import java.lang.reflect.Array;

/**
 * Created by huangsu2012@gmail.com on 2018/3/13.
 */
public class ArrayUtil {

  @SuppressWarnings("unchecked")
  public static <T> T[] resizeArray(T[] originArray, int size, int startIndex) {
    T[] temp = null;
//    if (originArray.length >= 4 * size) {
//      temp = (T[]) new Object[originArray.length / 2];
//    } else

    if (originArray.length <= size) {
      temp = (T[]) new Object[originArray.length * 2];
    }
    if (temp != null) {
      System.arraycopy(originArray, startIndex, temp, 0, size);
      return temp;
    }
    return originArray;
  }

  @SuppressWarnings("unchecked")
  public static <T> T[] newInstance(Class<T> tClass, int size) {
    return (T[]) Array.newInstance(tClass, size);
  }

  public static <T> boolean equals(T[] items, int index, T item) {
    return (item == items[index]) || (item != null && item.equals(items[index]));
  }
}
