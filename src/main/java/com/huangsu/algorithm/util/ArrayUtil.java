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

  public static int[] resizeArray(int[] originArray, int size, int startIndex) {
    int[] temp = null;
//    if (originArray.length >= 4 * size) {
//      temp = (T[]) new Object[originArray.length / 2];
//    } else

    if (originArray.length <= size) {
      temp = new int[originArray.length * 2];
    }
    if (temp != null) {
      System.arraycopy(originArray, startIndex, temp, 0, size);
      return temp;
    }
    return originArray;
  }

  public static <T> void insert(T[] originArray, T item, int size, int insertIndex,
      boolean resizeArrayAuto) {
    if (size - insertIndex >= 0) {
      if (resizeArrayAuto) {
        resizeArray(originArray, size, 0);
      }
      System.arraycopy(originArray, insertIndex, originArray, insertIndex + 1, size - insertIndex);
      originArray[insertIndex] = item;
    }
  }

  public static void insert(int[] originArray, int item, int size, int insertIndex,
      boolean resizeArrayAuto) {
    if (size - insertIndex >= 0) {
      if (resizeArrayAuto) {
        resizeArray(originArray, size, 0);
      }
      System.arraycopy(originArray, insertIndex, originArray, insertIndex + 1, size - insertIndex);
      originArray[insertIndex] = item;
    }
  }

  @SuppressWarnings("Duplicates")
  public static <K, V> void insert(K[] keys, V[] values, K key, V value, int size, int insertIndex,
      boolean resizeArrayAuto) {
    if (size - insertIndex >= 0) {
      if (resizeArrayAuto) {
        resizeArray(keys, size, 0);
        resizeArray(values, size, 0);
      }
      System.arraycopy(keys, insertIndex, keys, insertIndex + 1, size - insertIndex);
      System.arraycopy(values, insertIndex, values, insertIndex + 1, size - insertIndex);
      keys[insertIndex] = key;
      values[insertIndex] = value;
    }
  }

  @SuppressWarnings("Duplicates")
  public static <V> void insert(int[] keys, V[] values, int key, V value, int size, int insertIndex,
      boolean resizeArrayAuto) {
    if (size - insertIndex >= 0) {
      if (resizeArrayAuto) {
        resizeArray(keys, size, 0);
        resizeArray(values, size, 0);
      }
      System.arraycopy(keys, insertIndex, keys, insertIndex + 1, size - insertIndex);
      System.arraycopy(values, insertIndex, values, insertIndex + 1, size - insertIndex);
      keys[insertIndex] = key;
      values[insertIndex] = value;
    }
  }

  @SuppressWarnings("Duplicates")
  public static <K> boolean delete(K[] keys, int size, int deleteIndex) {
    if (size - deleteIndex > 0) {
      System.arraycopy(keys, deleteIndex + 1, keys, deleteIndex, size - deleteIndex);
      keys[deleteIndex] = null;
      return true;
    }
    return false;
  }

  @SuppressWarnings("Duplicates")
  public static boolean delete(int[] keys, int size, int deleteIndex) {
    if (size - deleteIndex > 0) {
      System.arraycopy(keys, deleteIndex + 1, keys, deleteIndex, size - deleteIndex);
      keys[deleteIndex] = 0;
      return true;
    }
    return false;
  }

  @SuppressWarnings("Duplicates")
  public static <K, V> V delete(K[] keys, V[] values, int size, int deleteIndex) {
    if (size - deleteIndex > 0) {
      V value = values[deleteIndex];
      System.arraycopy(keys, deleteIndex + 1, keys, deleteIndex, size - deleteIndex);
      System.arraycopy(values, deleteIndex + 1, values, deleteIndex, size - deleteIndex);
      keys[deleteIndex] = null;
      values[deleteIndex] = null;
      return value;
    }
    return null;
  }

  @SuppressWarnings("Duplicates")
  public static <V> V delete(int[] keys, V[] values, int size, int deleteIndex) {
    if (size - deleteIndex > 0) {
      V value = values[deleteIndex];
      System.arraycopy(keys, deleteIndex + 1, keys, deleteIndex, size - deleteIndex);
      System.arraycopy(values, deleteIndex + 1, values, deleteIndex, size - deleteIndex);
      keys[deleteIndex] = 0;
      values[deleteIndex] = null;
      return value;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public static <T> T[] newInstance(Class<T> tClass, int size) {
    return (T[]) Array.newInstance(tClass, size);
  }

  @SuppressWarnings("all")
  public static <T> boolean equals(T[] items, int index, T item) {
    return (item == items[index]) || (item != null && item.equals(items[index]));
  }
}
