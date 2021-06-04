package com.huangsu.algorithm.string;

/**
 * Created by huangsu2012@gmail.com on 2021/5/22.
 */
public class StringSearchBruteForce extends StringSearch {

  public StringSearchBruteForce(String pat) {
    super(pat);
  }

  @Override
  public int search(String txt) {
    if (txt != null && txt.length() >= pat.length()) {
      int i = 0, j = 0, M = pat.length(), N = txt.length();
      for (; i < N && j < M; i++) {
        if (pat.charAt(j) == txt.charAt(i)) {
          ++j;
        } else {
          i -= j;
          j = 0;
        }
      }
      if (j == M) {
        return i - M;
      }
    }
    return -1;
  }
}
