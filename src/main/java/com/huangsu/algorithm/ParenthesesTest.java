package com.huangsu.algorithm;

import com.huangsu.algorithm.struct.stack.LinkedStack;

/**
 * Created by huangsu2012@gmail.com on 2018/3/12.
 */
public class ParenthesesTest {

  public static void main(String[] args) {
    String[] strArray = new String[]{"[", "(", ")", "]", "{", "}", "{", "[", "(", ")", "(", ")",
        "]", "(", ")", "}"};
    LinkedStack<Integer> stack = new LinkedStack<>();
    for (String str : strArray) {
      int mappingVal = getIntMappingVal(str);
      if (mappingVal < 0 && -1 * mappingVal != stack.peek()) {
        break;
      }
      if (mappingVal > 0) {
        stack.push(mappingVal);
      } else {
        stack.pop();
      }
    }
    System.out.print("isCorrect:" + stack.isEmpty());
  }

  private static int getIntMappingVal(String val) {
    switch (val) {
      case "[":
        return 2;
      case "]":
        return -2;
      case "(":
        return 1;
      case ")":
        return -1;
      case "{":
        return 3;
      case "}":
        return -3;
    }
    return Integer.MAX_VALUE;
  }
}
