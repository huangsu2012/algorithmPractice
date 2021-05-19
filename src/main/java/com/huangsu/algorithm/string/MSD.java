package com.huangsu.algorithm.string;

import com.huangsu.algorithm.sort.InsertSort2;
import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2021/5/7.
 *
 * 高位优先的字符串排序，适用于长度不同的字符串
 *
 * 性能瓶颈来自于大量小型子数组，以及相同前缀字符串过多
 *
 * 尤其对于递归实现来说，每次递归都会创建字符集大小count数组，额外空间消耗将比较严重
 */
public class MSD {

  private final static int INSERTION_SORT_COUNT = 15;

  /**
   * 高位优先排序，递归实现
   *
   * @param a 要排序的数组
   * @param R 字符集大小
   */
  public static void sortRecursion(String[] a, int R) {
    int N = a.length;
    String[] aux = new String[N];
    sortRecursion(a, aux, R, 0, 0, N - 1);
  }

  /**
   * 高位优先排序，非递归实现
   *
   * @param a 要排序的数组
   * @param R 字符集大小
   */
  public static void sort(String[] a, int R) {
    int N = a.length;
    String[] aux = new String[N];
    int[] count = new int[R + 2];//索引1表示字符结尾的频率，2表示第一个字符的频率以此类推

    Queue<int[]> groupStartEndIndexes = new LinkedQueue<>();
    groupStartEndIndexes.enqueue(new int[]{0, N - 1, 0});
    while (!groupStartEndIndexes.isEmpty()) {
      int[] startEnd = groupStartEndIndexes.dequeue();
      int start = startEnd[0];
      int end = startEnd[1];

      if (start >= end - INSERTION_SORT_COUNT) {
        InsertSort2.sort(a, start, end);
        return;
      }
      Arrays.fill(count, 0);
      doSort(a, aux, R, startEnd[2], startEnd[0], startEnd[1], count);
      int chIndex = startEnd[2] + 1;
      for (int r = 0; r < R; r++) {
        int rStart = start + count[r];
        int rEnd = start + count[r + 1] - 1;
        if (rStart >= rEnd) {
          continue;
        }
        groupStartEndIndexes.enqueue(new int[]{rStart, rEnd, chIndex});
      }
    }
  }

  private static void sortRecursion(String[] a, String[] aux, int R, int chIndex, int start,
      int end) {
    if (start >= end - INSERTION_SORT_COUNT) {
      InsertSort2.sort(a, start, end);
      return;
    }
    int[] count = new int[R + 2];//索引1表示字符结尾的频率，2表示第一个字符的频率以此类推
    doSort(a, aux, R, chIndex, start, end, count);
    for (int r = 0; r < R; r++) {
      int rStart = start + count[r];
      int rEnd = start + count[r + 1] - 1;
      if (rStart >= rEnd) {//避免不必要的方法调用
        continue;
      }
      sortRecursion(a, aux, R, chIndex + 1, rStart, rEnd);
    }
  }

  private static void doSort(String[] a, String[] aux, int R, int chIndex, int start, int end,
      int[] count) {
    for (int i = start; i <= end; i++) {
      count[charAt(a[i], chIndex) + 2]++;
    }
    for (int r = 0; r < R + 1; r++) {
      count[r + 1] += count[r];
    }

    for (int i = start; i <= end; i++) {
      aux[count[charAt(a[i], chIndex) + 1]++] = a[i];
    }

    if (end + 1 - start > 0) {
      System.arraycopy(aux, 0, a, start, end + 1 - start);
    }
  }

  private static int charAt(String s, int i) {
    return i < s.length() ? s.charAt(i) : -1;
  }
}
