package com.huangsu.algorithm.util;

/**
 * Created by huangsu2012@gmail.com on 2018/3/31.
 */
public abstract class SortUtils {


  /**
   * 交换数组两个元素的位置
   *
   * @param items 待交换的数组
   * @param i 需要交换的元素的索引
   * @param j 需要交换的元素的索引
   * @param <T> 继承Comparable接口的类型
   */
  public static <T extends Comparable<T>> void exec(T[] items, int i, int j) {
    T temp = items[i];
    items[i] = items[j];
    items[j] = temp;
  }

  /**
   * @return true 如果v<w
   */
  public static <T extends Comparable<T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  public static <T extends Comparable<T>> boolean isSorted(T[] items) {
    for (int i = 1; i < items.length; i++) {
      if (less(items[i], items[i - 1])) {
        return false;
      }
    }
    return true;
  }

  public static <T extends Comparable<T>> int compareTo(T key1, T key2) {
    if (key1 == key2) {
      return 0;
    }
    if (key1 == null) {
      return -1;
    }
    if (key2 == null) {
      return 1;
    }
    return key1.compareTo(key2);
  }
}
