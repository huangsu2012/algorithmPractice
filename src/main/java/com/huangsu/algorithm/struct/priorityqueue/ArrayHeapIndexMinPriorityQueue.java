package com.huangsu.algorithm.struct.priorityqueue;

import com.huangsu.algorithm.sort.QuickSort;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by huangsu2012@gmail.com on 2021/3/14.
 */
public class ArrayHeapIndexMinPriorityQueue<T extends Comparable<T>> extends
    AbsArrayHeapIndexPriorityQueue<T> implements IndexMinPriorityQueue<T> {

  public ArrayHeapIndexMinPriorityQueue() {
    this(1);
  }

  public ArrayHeapIndexMinPriorityQueue(int initialCapacity) {
    super(initialCapacity);
  }

  @Override
  boolean minHeap() {
    return true;
  }

  @Override
  public T min() {
    return first();
  }

  @Override
  public int minIndex() {
    return firstIndex();
  }

  @Override
  public int delMin() {
    return delFirst();
  }

  public static void main(String[] args) {
    final int cap = 5;
    IndexMinPriorityQueue<String> minPriorityQueue = new ArrayHeapIndexMinPriorityQueue<>(cap);
    String[][] input = new String[cap][cap];
    String[] sortedInput = new String[cap * cap];
    int k = 0;
    Random random = new Random();
    for (int i = 0; i < cap; i++) {
      for (int j = 0; j < cap; j++) {
        input[i][j] = Character.toString((char) (random.nextInt(26) + 97));
        sortedInput[k++] = input[i][j];
      }
    }
    System.out.println(Arrays.deepToString(input));
    int[] inputIndex = new int[cap];
    for (int i = 0; i < cap; i++) {
      minPriorityQueue.insert(i, input[i][0]);
      inputIndex[i] = 0;
    }
    StringBuilder sb = new StringBuilder(5);
    for (; !minPriorityQueue.isEmpty(); ) {
      sb.append(minPriorityQueue.min()).append(" ");
      int index = minPriorityQueue.delMin();
      int j = inputIndex[index];
      if (j < input[index].length - 1) {
        minPriorityQueue.insert(index, input[index][++j]);
      }
      inputIndex[index] = j;
    }
    System.out.println(sb);
    QuickSort.sort(sortedInput);
    System.out.println(Arrays.toString(sortedInput));
  }
}
