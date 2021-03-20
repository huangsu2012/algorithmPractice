package com.huangsu.algorithm;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.stack.LinkedStack;

/**
 * Created by huangsu2012@gmail.com on 2018/3/12.
 */
public class ReverseQueueTest {

  public static void main(String[] args) {
    LinkedStack<String> stack = new LinkedStack<>();
    LinkedQueue<String> queue = new LinkedQueue<>();
    for (int i = 0; i < 5; i++) {
      queue.enqueue(String.valueOf(i));
    }
    while (!queue.isEmpty()) {
      stack.push(queue.dequeue());
    }
    while (!stack.isEmpty()) {
      queue.enqueue(stack.pop());
    }
    for (String str : queue) {
      System.out.print(str + " ");
    }
  }
}
