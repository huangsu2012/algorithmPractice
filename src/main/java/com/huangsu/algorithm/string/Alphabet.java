package com.huangsu.algorithm.string;

import com.huangsu.algorithm.struct.st.OrderedSet;
import com.huangsu.algorithm.struct.st.SkipListOrderedSet;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 *
 * 字母表api
 */
public class Alphabet {

  /**
   * 二进制
   */
  public final static Alphabet BINARY = new Alphabet('0', '1');
  /**
   *
   */
  public final static Alphabet DNA = new Alphabet("ACTG".toCharArray());
  /**
   * 八进制
   */
  public final static Alphabet OCTAL = new Alphabet('0', '7');
  /**
   * 10进制
   */
  public final static Alphabet DECIMAL = new Alphabet('0', '9');
  /**
   * 16进制
   */
  public final static Alphabet HEXDECIMAL = new Alphabet(DECIMAL, 'A', 'F');

  /**
   *
   */
  public final static Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY".toCharArray());

  /**
   * 26个英文字母小写
   */
  public final static Alphabet LOWERCASE = new Alphabet('a', 'z');
  /**
   * 26个英文字母大写
   */
  public final static Alphabet UPPERCASE = new Alphabet('A', 'Z');
  /**
   * BASE64字符集
   */
  public final static Alphabet BASE64 = new Alphabet(new Alphabet("+/".toCharArray()), '0', 'z');

  private final static char UNDEFINED_CHAR = (char) -1;
  public final static Alphabet ASCII = new Alphabet(0, 127);
  public final static Alphabet EXTENDED_ASCII = new Alphabet(0, 255);
  private final int lgR;
  private final int R;
  private final char[] chars;


  public Alphabet(String s) {
    OrderedSet<Character> orderedSet = new SkipListOrderedSet<>();
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);
      orderedSet.add(c);
    }
    R = orderedSet.size();
    lgR = calLgR(R);
    chars = new char[R];
    int index = 0;
    for (char c : orderedSet.keys()) {
      chars[index++] = c;
    }
  }

  public Alphabet(int start, int end) {
    this.chars = new char[end - start + 1];
    for (int c = start; c <= end; c++) {
      int index = c - start;
      chars[index] = (char) c;
    }
    R = chars.length;
    lgR = calLgR(R);
  }

  public Alphabet(Alphabet alphabet, int start, int end) {
    char[] chars = new char[end - start + 1 + alphabet.R];
    char[] alphabetChars = alphabet.chars;
    int alphabetIndex = 0;
    int i = 0;
    for (; i < chars.length; ) {
      if (alphabetIndex < alphabet.R && alphabetChars[alphabetIndex] <= start) {
        if (alphabetChars[alphabetIndex] != start) {
          chars[i++] = alphabetChars[alphabetIndex];
        }
        alphabetIndex++;
      } else if (start <= end) {
        chars[i++] = (char) start;
        ++start;
      } else {
        if (alphabetIndex < alphabet.R) {
          chars[i++] = alphabetChars[alphabetIndex++];
        } else {
          break;
        }
      }
    }
    if (i < chars.length) {
      this.chars = new char[i];
      System.arraycopy(chars, 0, this.chars, 0, i);
    } else {
      this.chars = chars;
    }

    R = chars.length;
    lgR = calLgR(R);
  }

  /**
   * @param chars 有序字符数组
   */
  private Alphabet(char[] chars) {
    this.chars = chars;
    R = chars.length;
    lgR = calLgR(R);

  }


  public int R() {
    return R;
  }

  public int lgR() {
    return lgR;
  }

  public char toChar(int index) {
    return index < R ? chars[index] : UNDEFINED_CHAR;
  }

  public int toIndex(char c) {
    char firstCh = chars[0];
    int low = (c - firstCh) % R;
    int high;
    if (c == chars[low]) {//对于连续的字符数组来说这里就能直接返回，如果不是则使用二分查找
      return low;
    } else if (c < chars[low]) {
      high = low;
      low = 0;
    } else {
      high = R - 1;
    }
    int mid;
    for (; low <= high; ) {
      mid = low + (high - low) / 2;
      if (chars[mid] == c) {
        return mid;
      } else if (chars[mid] > c) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }

  public boolean contains(char c) {
    return toIndex(c) != -1;
  }

  public int[] toIndices(String s) {
    int[] indices = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      indices[i] = toIndex(s.charAt(i));
    }
    return indices;
  }

  public String toChars(int[] indices) {
    char[] chars = new char[indices.length];
    for (int i = 0; i < indices.length; i++) {
      chars[i] = toChar(indices[i]);
    }
    return new String(chars);
  }


  private static int calLgR(int R) {
    int n = R - 1;
    int c = 0;
    while (n > 0) {
      n = n >>> 1;
      ++c;
    }
    return c;
  }

  @Override
  public String toString() {
    return "Alphabet{" +
        "chars=" + new String(chars) + ",R=" + R + ",lgR:" + lgR +
        '}';
  }

  public static void main(String[] args) {
//    Alphabet alphabet = Alphabet.UPPERCASE;
//    System.out.println(alphabet.toIndex('G'));
//    System.out.println(alphabet.toIndex('A'));
//    System.out.println(alphabet.toIndex('Z'));

//    Alphabet alphabet2 = Alphabet.PROTEIN;
//    System.out.println(alphabet2.toIndex('G'));
//    System.out.println(alphabet2.toIndex('A'));
//    System.out.println(alphabet2.toIndex('Z'));
    System.out.println(Character.MAX_VALUE);
    System.out.println((('0' - '+') % 1024));
  }
}
