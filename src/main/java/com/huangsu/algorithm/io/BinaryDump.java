package com.huangsu.algorithm.io;

/**
 * Created by huangsu2012@gmail.com on 2021/5/30.
 */
public class BinaryDump {

  public static void main(String[] args) {
    int width = Integer.parseInt(args[0]);
    int cnt;
    BinaryStdIn binaryStdIn = new BinaryStdInImpl(System.in);
    for (cnt = 0; !binaryStdIn.isEmpty(); cnt++) {
      if (width == 0) {
        continue;
      }
      if (cnt != 0 && cnt % width == 0) {
        System.out.println();
      }
      if (binaryStdIn.readBoolean()) {
        System.out.print("1");
      } else {
        System.out.print("0");
      }
    }
    System.out.println();
    System.out.println(cnt + " bits");
  }
}
