package com.huangsu.algorithm.string;

import com.huangsu.algorithm.struct.st.SetCollection;

/**
 * Created by huangsu2012@gmail.com on 2021/5/18.
 *
 * 字符串不可重复键集合抽象
 */
public interface StringSetCollection extends SetCollection<String> {

  /**
   * @return s的前缀中最长的键
   */
  String longestPrefixOf(String s);

  /**
   * @param s 前缀s
   * @return 所有以s为前缀的键
   */
  Iterable<String> keysWithPrefix(String s);

  /**
   * @param s "."能够匹配任意字符
   * @return 所有和s匹配的键
   */
  Iterable<String> keysThatMatch(String s);
}
