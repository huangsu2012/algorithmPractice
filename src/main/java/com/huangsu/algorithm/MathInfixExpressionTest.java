package com.huangsu.algorithm;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2018/3/15.
 */
public class MathInfixExpressionTest {

  public static void main(String[] args) {
    String testExpression = "1+2)*3-4)*5-6)))";
    Stack<String> ops = new LinkedStack<>();
    Stack<String> vals = new LinkedStack<>();
    char[] testExpressionArray = testExpression.toCharArray();
    for (char c : testExpressionArray) {
      String s = String.valueOf(c);
      switch (s) {
        case ")":
          String op = ops.pop();
          String val2 = vals.pop();
          String val1 = vals.pop();
          vals.push("(" + val1 + op + val2 + ")");
          break;
        case "+":
        case "-":
        case "*":
        case "/":
          ops.push(s);
          break;
        default:
          vals.push(s);
          break;
      }
    }
    System.out.println(vals.pop());
  }

}
