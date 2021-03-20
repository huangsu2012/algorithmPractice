package com.huangsu.algorithm;

import java.util.Arrays;

/**
 * Created by huangsu2012@gmail.com on 2018/5/10.
 */
public class SquareMatrixMultiply {

  public static int[][] multiply(int[][] matrixA, int[][] matrixB) {
    int[][] matrixC = new int[matrixA.length][matrixA[0].length];
    for (int i = 0; i < matrixA.length; i++) {
      for (int j = 0; j < matrixA[i].length; j++) {
        int sum = 0;
        for (int k = 0; k < matrixB.length; k++) {
          sum += matrixA[i][k] * matrixB[k][j];
        }
        matrixC[i][j] = sum;
      }
    }
    return matrixC;
  }

  public static void main(String[] args) {
    int[][] matrixA = new int[][]{new int[]{1, 3, 4}, new int[]{5, 6, 7}, new int[]{8, 9, 10}};
    int[][] matrixB = new int[][]{new int[]{3, 3, 4}, new int[]{6, 6, 7}, new int[]{10, 9, 10}};
    System.out.println(Arrays.deepToString(multiply(matrixA, matrixB)));
  }

}
