package com.huangsu.algorithm.string;

import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2021/5/25.
 *
 * Boyer-Moore字符串查找算法实现
 */
public class StringSearchBoyerMoore extends StringSearch {

  private final int R;
  private final int[] right;

  public StringSearchBoyerMoore(String pat, int r) {
    super(pat);
    R = r;
    right = buildRightArray(pat);
  }

  @Override
  public int search(String txt) {
    if (txt != null && txt.length() >= pat.length()) {
      int N = txt.length();
      int M = pat.length();
      int i = 0;
      int skip;
      for (; i < N; i += skip) {
        skip = 0;
        for (int j = M - 1; j >= 0; j--) {
          char txtC = txt.charAt(i + j);
          if (txtC != pat.charAt(j)) {
            skip = j - right[txtC];
            if (skip < 1) {
              skip = 1;
            }
            break;
          }
        }
        if (skip == 0) {
          return i;
        }
      }

    }
    return -1;
  }

//  @Override
//  public int search(String pat, String txt) {
//    if (pat != null && txt != null) {
//      int[] right = buildRightArray(pat);
//      int N = txt.length();
//      int M = pat.length();
//      int i = 0, j = M - 1;
//      for (; i < N && j >= 0; ) {
//        char txtC = txt.charAt(i + j);
//        if (txtC != pat.charAt(j)) {
//          int skip = j - right[txtC];
//          if (skip < 1) {
//            skip = 1;
//          }
//          i += skip;
//          j = M - 1;
//        } else {
//          j--;
//        }
//      }
//      if (j == 0) {
//        return i;
//      }
//    }
//    return -1;
//  }

  private int[] buildRightArray(String pat) {
    int[] right = new int[R];
    Arrays.fill(right, -1);
    for (int i = 0; i < pat.length(); i++) {
      right[pat.charAt(i)] = i;
    }
    return right;
  }
}
