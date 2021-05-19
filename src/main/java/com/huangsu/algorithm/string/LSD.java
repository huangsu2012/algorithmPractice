package com.huangsu.algorithm.string;

/**
 * Created by huangsu2012@gmail.com on 2021/5/7.
 *
 * 低位优先的字符串排序，适用于定长字符串
 *
 * 核心思想是基数排序
 */
public class LSD {


  public static void sort(String[] a, int R, int W) {
    int N = a.length;
    String[] aux = new String[a.length];
    for (int i = W - 1; i >= 0; i--) {
      int[] count = new int[R + 1];
      for (String s : a) {//计算每个字符出现的频率
        count[s.charAt(i) + 1] += 1;
      }
      for (int j = 0; j < R; j++) {//将频率转换为索引
        count[j + 1] += count[j];
      }
      for (String s : a) {//将元素分类
        aux[count[s.charAt(i) + 1]++] = s;
      }
      //回写
      System.arraycopy(aux, 0, a, 0, N);
    }
  }
}
