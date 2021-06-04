package com.huangsu.algorithm.string;

/**
 * Created by huangsu2012@gmail.com on 2021/5/22.
 *
 * 字符串查找抽象
 */
public abstract class StringSearch {

  /**
   * 要查找的模式串，不能为null且不能为空
   */
  protected final String pat;

  public StringSearch(String pat) {
    if (pat == null || pat.length() == 0) {
      throw new IllegalArgumentException("pat is null or empty");
    }
    this.pat = pat;
  }

  /**
   * @param txt 待查找的文本
   * @return 如果未找到或者pat、txt任意一个为null，则返回-1；否则返回pat第一次出现的位置
   */
  public abstract int search(String txt);
}
