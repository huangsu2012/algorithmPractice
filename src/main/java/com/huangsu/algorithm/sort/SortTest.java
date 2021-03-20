package com.huangsu.algorithm.sort;

import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2021/2/21.
 */
public class SortTest {

  public static void main(String[] args) {
    String[] sortTypes = new String[]{"insert", "select", "shell", "bubble", "merge1", "merge2",
        "merge3", "mergeBU", "quick", "binaryHeap", "binaryHeap2", "binaryHeap3"};
    for (String sortType : sortTypes) {
      sort(sortType);
    }
  }

  private static void sort(String sortType) {
    sort(Integer.class, new Integer[]{121, 99, 222, 100, 33, 78, 44, 588, 33, 12, 34, 57, 99, 100,
        450, 19}, sortType);
  }

  private static <T extends Comparable<T>> void sort(Class<T> tClass, T[] items, String sortType) {
    switch (sortType) {
      case "insert":
        InsertSort.sort(items);
        break;
      case "select":
        SelectSort.sort(items);
        break;
      case "shell":
        ShellSort.sort(items);
        break;
      case "bubble":
        BubbleSort.sort(items);
        break;
      case "merge1":
        MergeSort.sort(tClass, items, 0, items.length - 1);
        break;
      case "merge2":
        MergeSort2.sort(tClass, items, null, 0, items.length - 1);
        break;
      case "merge3":
        MergeSort3.sort(tClass, items, null, 0, items.length - 1);
        break;
      case "mergeBU":
        MergeSortBU.sort(tClass, items, null);
        break;
      case "quick":
        QuickSort.sort(items);
        break;
      case "binaryHeap":
        BinaryHeapSort.sort(items);
        break;
      case "binaryHeap2":
        BinaryHeapSort2.sort(items);
        break;
      case "binaryHeap3":
        BinaryHeapSort3.sort(items);
        break;
    }
    System.out.println(sortType + ":" + Arrays.toString(items));
  }
}
