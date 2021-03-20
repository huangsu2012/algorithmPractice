package com.huangsu.algorithm;

import com.huangsu.algorithm.struct.stack.LinkedStack;
import com.huangsu.algorithm.struct.stack.Stack;

/**
 * Created by huangsu2012@gmail.com on 2018/3/18.
 *
 * 中序表达式转为后续表达式
 */
public class MathInfixToPostfix {

  public static void main(String[] args) {
//    String infix = "(2*1+3)*4/5*(1-2)+2";
    String infix = "5+((1+2)*4)-3";
    Stack<String> ops = new LinkedStack<>();
    Stack<String> vals = new LinkedStack<>();
    char[] infixArray = infix.trim().toCharArray();
    for (char c : infixArray) {
      String s = String.valueOf(c);
      switch (s) {
        case "+":
        case "-":
          if (!ops.isEmpty()) {
            for (String topOp = ops.peek(); "*".equals(topOp) || "/".equals(topOp); ) {
              if (!toPostfix(ops, vals) || ops.isEmpty()) {
                break;
              }
              topOp = ops.peek();
            }
          }
          ops.push(s);
          break;
        case "*":
        case "/":
          ops.push(s);
          break;
        case "(":
          ops.push(s);
          break;
        case ")":
          for (; !ops.isEmpty(); ) {
            if (!toPostfix(ops, vals)) {
              break;
            }
          }
          break;
        default:
          vals.push(s);
          break;
      }
    }
    for (; !ops.isEmpty(); ) {
      if (!toPostfix(ops, vals)) {
        break;
      }
    }
    if (!ops.isEmpty()) {
      System.out.println("wrong expression");
    } else {
      String postfix = vals.pop();
      System.out.println("postfix:" + postfix + ",calculateResult:" + calculateResult(postfix));
    }
  }

  private static double calculateResult(String postfix) {
    char[] postfixArray = postfix.toCharArray();
    Stack<Double> vals = new LinkedStack<>();
    for (char c : postfixArray) {
      String s = String.valueOf(c);
      double val2;
      double val1;
      switch (s) {
        case "+":
          vals.push(vals.pop() + vals.pop());
          break;
        case "-":
          val2 = vals.pop();
          val1 = vals.pop();
          vals.push(val1 - val2);
          break;
        case "*":
          vals.push(vals.pop() * vals.pop());
          break;
        case "/":
          val2 = vals.pop();
          val1 = vals.pop();
          vals.push(val1 / val2);
          break;
        default:
          vals.push(Double.valueOf(s));
          break;
      }
    }
    return vals.pop();
  }

  private static boolean toPostfix(Stack<String> ops, Stack<String> vals) {
    if (ops.isEmpty()) {
      return false;
    }
    String topOp = ops.pop();
    if ("(".equals(topOp) || ")".equals(topOp)) {
      return false;
    }
    String val2 = vals.pop();
    String val1 = vals.pop();
    vals.push(val1 + val2 + topOp);
    return true;
  }
}
