package com.huangsu.algorithm.string;

import com.huangsu.algorithm.util.CollectionUtil;

/**
 * Created by huangsu2012@gmail.com on 2021/5/14.
 */
public class StringSTTest {

  public static void main(String[] args) {
    testStringST(new TrieStringST<>(256));
    testStringST(new ThreeWayTrieStringST<>());
  }

  private static void testStringST(StringST<Integer> stringST) {
    System.out.println(stringST.getClass().getName() + " testResult:");
    int i = 0;
    stringST.put("she", i++);
    stringST.put("sells", i++);
    stringST.put("sea", i++);
    stringST.put("shells", i++);
    stringST.put("by", i++);
    stringST.put("the", i++);
    stringST.put("shore", i);

    System.out.println("keys:");
    System.out.println(CollectionUtil.toString(stringST.keys()));
    System.out.println("keysThatMatch:");
    System.out.println(CollectionUtil.toString(stringST.keysThatMatch("")));
    System.out.println(CollectionUtil.toString(stringST.keysThatMatch("s..")));
    System.out.println(CollectionUtil.toString(stringST.keysThatMatch("..h")));
    System.out.println(CollectionUtil.toString(stringST.keysThatMatch(".he")));

    System.out.println("keysWithPrefix:");
    System.out.println(CollectionUtil.toString(stringST.keysWithPrefix("se")));
    System.out.println(CollectionUtil.toString(stringST.keysWithPrefix("she")));

    stringST.delete("by");
    stringST.delete("sea");
    System.out.println("keysThatMatchAfterDel:");
    System.out.println(CollectionUtil.toString(stringST.keysThatMatch("b.")));
    System.out.println(CollectionUtil.toString(stringST.keysThatMatch("s..")));
  }
}
