package com.huangsu.algorithm.string;

/**
 * Created by huangsu2012@gmail.com on 2021/5/22.
 *
 * KMP基于确定有限状态机实现字符串查找
 */
public class StringSearchKMPDFA extends StringSearch {

  private final int R;

  public StringSearchKMPDFA(String pat, int R) {
    super(pat);
    this.R = R;
  }

  @Override
  public int search(String txt) {
    if (txt != null && txt.length() >= pat.length()) {
      int[][] dfa = buildDFA(pat);
      int i = 0, j = 0, M = pat.length(), N = txt.length();
      for (; i < N && j < M; i++) {
        j = dfa[txt.charAt(i)][j];
      }
    }
    return -1;
  }

  private int[][] buildDFA(String pat) {
    int[][] dfa = new int[R][pat.length()];
    dfa[pat.charAt(0)][0] = 1;
    for (int x = 0, j = 1; j < pat.length(); j++) {
      for (int c = 0; c < R; c++) {
        dfa[c][j] = dfa[c][x];//对于所有非匹配的字符重置为上一个状态
      }
      dfa[pat.charAt(j)][j] = j + 1;//对于匹配的字符，将状态设置为下一个字符的位置
      x = dfa[pat.charAt(j)][x];
    }
    return dfa;
  }
}
