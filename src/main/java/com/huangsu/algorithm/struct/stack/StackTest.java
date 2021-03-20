package com.huangsu.algorithm.struct.stack;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 */
public class StackTest {

  public static void main(String[] args) {
    String[] opStrArray = new String[]{"to", "be", "or", "not", "to", "-", "be", "-", "-", "that",
        "-", "-", "-", "is"};
    testStack(opStrArray, new ArrayStack<>());
    testStack(opStrArray, new LinkedStack<>());
  }

  private static void testStack(String[] opStrArray, Stack<String> stack) {
    for (String opStr : opStrArray) {
      if ("-".equals(opStr)) {
        System.out.print(stack.pop() + " ");
      } else {
        stack.push(opStr);
      }
    }
    System.out.println("(" + stack.size() + " left on stack)");
    for (String str : stack) {
      System.out.print(str + " ");
    }
  }
}
