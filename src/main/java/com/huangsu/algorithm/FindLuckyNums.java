package com.huangsu.algorithm;

/**
 * Created by huangsu2012@gmail.com on 2017/10/18.
 */
public class FindLuckyNums {

  public static void main(String[] args) {
    findLuckyNums(0, 1000, 59);
  }

  private static void findLuckyNums(long start, long end, long toFind) {
    int maxLen = String.valueOf(end).length();
    int minLen = String.valueOf(start).length();
    int toFindLen = String.valueOf(toFind).length();
    StringBuilder sb = new StringBuilder();
    if (toFindLen > maxLen || toFind > end) {
      return;
    }
    int count = 0;
    for (int i = Math.max(minLen, toFindLen); i <= maxLen; i++) {
      count = findLuckyNums(count, sb, end, i, toFind, toFindLen);
    }
    System.out.println("count:" + count);
    System.out.println(sb);
  }

  private static int findLuckyNums(int count, StringBuilder sb,
                                   long end, int numLen,
                                   long toFind, int toFindLen) {


    return count;
  }
}
