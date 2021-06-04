package com.huangsu.algorithm.string;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by huangsu2012@gmail.com on 2021/5/27.
 *
 * 指纹字符串查找算法实现
 *
 * 核心思想：(a+b)%r=a%r+b%r;(a*b)%r=(a%r)*(b%r)
 */
public class StringSearchRabinKarp extends StringSearch {

  private final int R;
  private final long Q;

  public StringSearchRabinKarp(String pat, int r) {
    super(pat);
    R = r;
    Q = BigInteger.probablePrime(64, new Random()).longValue();//生成一个随机的大素数
  }

  @Override
  public int search(String txt) {
    if (txt != null && txt.length() >= pat.length()) {
      int M = pat.length();
      int N = txt.length();
      long patHash = hash(pat, M);
      long RM = 1;
      for (int j = 0; j < M - 1; j++) {
        RM *= R % M;
      }
      long txtHash = hash(txt, M);
      //一个长度为M的R进制数可以表示为xi=ti*R^M-1+ti+1
      for (int i = 1; i < N - M; i++) {
        if (txtHash == patHash) {
          if (check(pat, txt, i)) {
            return i;
          }
        }
        txtHash = (txtHash + Q - (txt.charAt(i) * RM) % Q) % Q;//(a+r)%r=a%r,额外加上一个Q只是为了得到的计算结果为正数
        txtHash = (txtHash * R + txt.charAt(i + M)) % Q;
      }
    }
    return -1;
  }

  private boolean check(String pat, String txt, int i) {
    return true;//需要Q为很大的素数，大于10的20次方，否则需要逐个字符比较
  }

  private long hash(String key, int M) {
    long h = 0;
    for (int j = 0; j < M; j++) {
      h = (R * h + key.charAt(j)) % Q;
    }
    return h;
  }
}
