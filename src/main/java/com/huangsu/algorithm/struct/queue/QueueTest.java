package com.huangsu.algorithm.struct.queue;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 */
public class QueueTest {

  public static void main(String[] args) {
    String[] opStrArray = new String[]{"to", "be", "or", "not", "to", "-", "be", "-", "-", "that",
        "-", "-", "-", "is"};
    testQueue(opStrArray, new ArrayQueue<>(9));
    testQueue(opStrArray, new ArrayQueueMoreEfficient<>(9));
    testQueue(opStrArray, new LinkedQueue<>());
    testQueue(opStrArray, new CircleLinkedQueue<>());
  }

  private static void testQueue(String[] opStrArray, Queue<String> queue) {
    for (String opStr : opStrArray) {
      if ("-".equals(opStr)) {
        System.out.print(queue.dequeue() + " ");
      } else {
        queue.enqueue(opStr);
      }
    }
    System.out.println("(" + queue.size() + " left on queue)");
    for (String str : queue) {
      System.out.print(str + " ");
    }
  }
}
