package com.huangsu.algorithm;

import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2018/5/9.
 *
 * 查找数组中和最大的子数组
 */
public class MaxSumArray {

  public static int[] findMaxSumArray(int[] array, int lo, int hi) {
    if (lo == hi) {
      return new int[]{lo, hi, array[lo]};
    } else {
      int mid = (hi + lo) / 2;
      int[] leftResult = findMaxSumArray(array, lo, mid);
      int[] rightResult = findMaxSumArray(array, mid + 1, hi);
      int[] crossResult = findMaxSumArrayCross(array, lo, mid, hi);
      if (leftResult[2] >= rightResult[2] && leftResult[2] >= crossResult[2]) {
        return leftResult;
      } else if (rightResult[2] >= leftResult[2] && rightResult[2] >= crossResult[2]) {
        return rightResult;
      } else {
        return crossResult;
      }
    }
  }

  private static int[] findMaxSumArrayCross(int[] array, int lo, int mid, int hi) {
    int sum = 0;
    int leftSum = Integer.MIN_VALUE;
    int leftMinIndex = mid;
    for (int i = mid; i >= lo; i--) {
      sum += array[i];
      if (sum > leftSum) {
        leftSum = sum;
        leftMinIndex = i;
      }
    }

    sum = 0;
    int rightSum = Integer.MIN_VALUE;
    int rightMaxIndex = mid + 1;
    for (int i = mid + 1; i <= hi; i++) {
      sum += array[i];
      if (sum > rightSum) {
        rightSum = sum;
        rightMaxIndex = i;
      }
    }
    return new int[]{leftMinIndex, rightMaxIndex, leftSum + rightSum};
  }

//  public static int[] findMaxSumArrayFast(int[] array) {
//    int sum = 0;
//    int rightIndex = 0;
//    int maxSum = Integer.MIN_VALUE;
//    for (int i = 0; i < array.length; i++) {
//      sum += array[i];
//      if (sum > maxSum) {
//        maxSum = sum;
//        rightIndex = i;
//      }
//    }
//  }

  public static void main(String[] args) {
    int[] array = new int[]{4, -3, 5, 7, -6, 8, 8, -7, 4, 5, 6, -8};
    System.out.println(Arrays.toString(findMaxSumArray(array, 0, array.length - 1)));
  }
}
