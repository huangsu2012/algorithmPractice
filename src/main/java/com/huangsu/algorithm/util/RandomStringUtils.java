package com.huangsu.algorithm.util;

import com.huangsu.algorithm.string.Alphabet;
import java.util.Random;

/**
 * Created by huangsu2012@gmail.com on 2021/5/8.
 */
public abstract class RandomStringUtils {

  private final static Random r = new Random();

  public static String[] generateStrs(Alphabet alphabet, int strCount, int strLen) {
    return generateStrs(alphabet, strCount, strLen, -1);
  }

  /**
   * @param alphabet 字符集
   * @param strCount 字符串总数
   * @param minStrLen 每个字符串最少长度，包含
   * @param maxStrLen 每个字符串最大长度，不包含；如果该值为-1为定长字符串，长度为minStrLen
   * @return 随机字符串数组
   */
  public static String[] generateStrs(Alphabet alphabet, int strCount, int minStrLen,
      int maxStrLen) {
    String[] result = new String[strCount];
    boolean fixedLen = minStrLen + 1 == maxStrLen || maxStrLen == -1;
    for (int i = 0; i < strCount; i++) {
      int strLen = fixedLen ? minStrLen : r.nextInt(maxStrLen) + minStrLen;
      result[i] = generateStr(alphabet, strLen);
    }
    return result;
  }

  public static String generateStr(Alphabet alphabet, int strLen) {
    char[] chars = new char[strLen];
    for (int i = 0; i < strLen; i++) {
      chars[i] = alphabet.toChar(r.nextInt(alphabet.R()));
    }
    return new String(chars);
  }

  /**
   * @param alphabet 字符集
   * @param minStrLen 每个字符串最少长度，包含
   * @param maxStrLen 每个字符串最大长度，不包含；如果该值为-1为定长字符串，长度为minStrLen
   * @return 随机字符串
   */
  public static String generateStr(Alphabet alphabet, int minStrLen,
      int maxStrLen) {
    boolean fixedLen = minStrLen + 1 == maxStrLen || maxStrLen == -1;
    int strLen = fixedLen ? minStrLen : r.nextInt(maxStrLen) + minStrLen;
    return generateStr(alphabet, strLen);
  }


}
