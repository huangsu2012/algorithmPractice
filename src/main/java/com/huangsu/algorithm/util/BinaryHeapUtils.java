package com.huangsu.algorithm.util;

/**
 * Created by huangsu2012@gmail.com on 2021/3/9.
 *
 * 二叉堆工具类
 */
public abstract class BinaryHeapUtils {

  public static <T extends Comparable<T>> void swimMax(T[] pq, int index) {
    swimMax(pq, index, false);
  }

  /**
   * 大顶堆上浮逻辑
   *
   * @param pq 数组
   * @param index 需要上浮的节点坐标
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void swimMax(T[] pq, int index, boolean zeroStart) {
    int pIndexOffset = 0;
    int minPIndex = 1;
    if (zeroStart) {
      pIndexOffset = -1;
      minPIndex = 0;
    }
    int pIndex = (index + pIndexOffset) / 2;
    while (index > minPIndex && SortUtils.compareTo(pq[index], pq[pIndex]) > 0) {
      SortUtils.exec(pq, index, pIndex);
      index = pIndex;
      pIndex = (pIndex + pIndexOffset) / 2;
    }
  }

  public static <T extends Comparable<T>> void swimMaxImproved(T[] pq, int index) {
    swimMaxImproved(pq, index, false);
  }

  /**
   * 大顶堆上浮逻辑,不使用交换元素的逻辑，而是使用改进后的插入排序逻辑
   *
   * @param pq 数组
   * @param index 需要上浮的节点坐标
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void swimMaxImproved(T[] pq, int index,
      boolean zeroStart) {
    int pIndexOffset = 0;
    int minPIndex = 1;
    if (zeroStart) {
      pIndexOffset = -1;
      minPIndex = 0;
    }
    int pIndex = (index + pIndexOffset) / 2;
    T item = pq[index];
    while (index > minPIndex && SortUtils.compareTo(item, pq[pIndex]) > 0) {
      pq[index] = pq[pIndex];
      index = pIndex;
      pIndex = (pIndex + pIndexOffset) / 2;
    }
    pq[index] = item;
  }

  public static <T extends Comparable<T>> void sinkMax(T[] pq, int pIndex, int size) {
    sinkMax(pq, pIndex, size, false);
  }

  /**
   * 大顶堆下沉逻辑
   *
   * @param pq 数组
   * @param pIndex 需要下沉的父节点坐标
   * @param size 数组元素数量
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void sinkMax(T[] pq, int pIndex, int size,
      boolean zeroStart) {
    int cIndexOffset = 0;
    if (zeroStart) {
      cIndexOffset = 1;
      --size;
    }
    int cIndex = pIndex * 2 + cIndexOffset;
    while (cIndex <= size) {
      if (cIndex < size && SortUtils.compareTo(pq[cIndex], pq[cIndex + 1]) < 0) {
        ++cIndex;
      }
      if (SortUtils.compareTo(pq[pIndex], pq[cIndex]) > 0) {
        break;
      }
      SortUtils.exec(pq, pIndex, cIndex);
      pIndex = cIndex;
      cIndex = cIndex * 2 + cIndexOffset;
    }
  }

  public static <T extends Comparable<T>> void sinkMaxImproved(T[] pq, int pIndex, int size) {
    sinkMaxImproved(pq, pIndex, size, false);
  }

  /**
   * 大顶堆下沉逻辑;不使用交换元素的逻辑，而是使用改进后的插入排序逻辑
   *
   * @param pq 数组
   * @param pIndex 需要下沉的父节点坐标
   * @param size 数组元素数量
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void sinkMaxImproved(T[] pq, int pIndex, int size,
      boolean zeroStart) {
    int cIndexOffset = 0;
    if (zeroStart) {
      cIndexOffset = 1;
      --size;
    }
    int cIndex = pIndex * 2 + cIndexOffset;
    T item = pq[pIndex];
    while (cIndex <= size) {
      if (cIndex < size && SortUtils.compareTo(pq[cIndex], pq[cIndex + 1]) < 0) {
        ++cIndex;
      }
      if (SortUtils.compareTo(item, pq[cIndex]) > 0) {
        break;
      }
      pq[pIndex] = pq[cIndex];
      pIndex = cIndex;
      cIndex = cIndex * 2 + cIndexOffset;
    }
    pq[pIndex] = item;
  }

  //  public static <T extends Comparable<T>> void sinkMaxZeroStart(T[] pq, int pIndex, int size) {
//    int cIndex = pIndex * 2 + 1;
//    while (cIndex < size) {
//      if (cIndex < size - 1 && SortUtils.compareTo(pq[cIndex], pq[cIndex + 1]) < 0) {
//        ++cIndex;
//      }
//      if (SortUtils.compareTo(pq[pIndex], pq[cIndex]) > 0) {
//        break;
//      }
//      SortUtils.exec(pq, pIndex, cIndex);
//      pIndex = cIndex;
//      cIndex = cIndex * 2 + 1;
//    }
//  }
  public static <T extends Comparable<T>> void swimMin(T[] pq, int index) {
    swimMin(pq, index, false);
  }

  /**
   * 小顶堆上浮逻辑
   *
   * @param pq 数组
   * @param index 需要上浮的节点坐标
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void swimMin(T[] pq, int index, boolean zeroStart) {
    int pIndexOffset = 0;
    int minPIndex = 1;
    if (zeroStart) {
      pIndexOffset = -1;
      minPIndex = 0;
    }
    int pIndex = (index + pIndexOffset) / 2;
    while (index > minPIndex && SortUtils.compareTo(pq[index], pq[pIndex]) < 0) {
      SortUtils.exec(pq, index, pIndex);
      index = pIndex;
      pIndex = (pIndex + pIndexOffset) / 2;
    }
  }


  public static <T extends Comparable<T>> void swimMinImproved(T[] pq, int index) {
    swimMinImproved(pq, index, false);
  }

  /**
   * 小顶堆上浮逻辑
   *
   * @param pq 数组
   * @param index 需要上浮的节点坐标
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void swimMinImproved(T[] pq, int index,
      boolean zeroStart) {
    int pIndexOffset = 0;
    int minPIndex = 1;
    if (zeroStart) {
      pIndexOffset = -1;
      minPIndex = 0;
    }
    T item = pq[index];
    int pIndex = (index + pIndexOffset) / 2;
    while (index > minPIndex && SortUtils.compareTo(item, pq[pIndex]) < 0) {
      pq[index] = pq[pIndex];
      index = pIndex;
      pIndex = (pIndex + pIndexOffset) / 2;
    }
    pq[index] = item;
  }

  public static <T extends Comparable<T>> void sinkMin(T[] pq, int pIndex, int size) {
    sinkMin(pq, pIndex, size, false);
  }

  /**
   * 小顶堆下沉逻辑
   *
   * @param pq 数组
   * @param pIndex 需要下沉的父节点坐标
   * @param size 数组元素数量
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void sinkMin(T[] pq, int pIndex, int size,
      boolean zeroStart) {
    int cIndexOffset = 0;
    if (zeroStart) {
      cIndexOffset = 1;
      --size;
    }
    int cIndex = pIndex * 2 + cIndexOffset;
    while (cIndex <= size) {
      if (cIndex < size && SortUtils.compareTo(pq[cIndex], pq[cIndex + 1]) > 0) {
        ++cIndex;
      }
      if (SortUtils.compareTo(pq[pIndex], pq[cIndex]) < 0) {
        break;
      }
      SortUtils.exec(pq, pIndex, cIndex);
      pIndex = cIndex;
      cIndex = cIndex * 2 + cIndexOffset;
    }
  }

  public static <T extends Comparable<T>> void sinkMinImproved(T[] pq, int pIndex, int size) {
    sinkMinImproved(pq, pIndex, size, false);
  }

  /**
   * 小顶堆下沉逻辑;不使用交换元素的逻辑，而是使用改进后的插入排序逻辑
   *
   * @param pq 数组
   * @param pIndex 需要下沉的父节点坐标
   * @param size 数组元素数量
   * @param zeroStart 数组元素下标是否从0开始
   */
  public static <T extends Comparable<T>> void sinkMinImproved(T[] pq, int pIndex, int size,
      boolean zeroStart) {
    int cIndexOffset = 0;
    if (zeroStart) {
      cIndexOffset = 1;
      --size;
    }
    int cIndex = pIndex * 2 + cIndexOffset;
    T item = pq[pIndex];
    while (cIndex <= size) {
      if (cIndex < size && SortUtils.compareTo(pq[cIndex], pq[cIndex + 1]) > 0) {
        ++cIndex;
      }
      if (SortUtils.compareTo(item, pq[cIndex]) < 0) {
        break;
      }
      pq[pIndex] = pq[cIndex];
      pIndex = cIndex;
      cIndex = cIndex * 2 + cIndexOffset;
    }
    pq[pIndex] = item;
  }
}
