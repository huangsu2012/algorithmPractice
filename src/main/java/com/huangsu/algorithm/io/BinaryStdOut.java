package com.huangsu.algorithm.io;

/**
 * Created by huangsu2012@gmail.com on 2021/5/29.
 *
 * 向输出中写入比特流
 */
public interface BinaryStdOut {

  int BYTE_BIT = 8;

  /**
   * 写入一比特的值
   */
  void write(boolean b);

  /**
   * 写入指定字符的低8位
   */
  default void writeChar(char c) {
    writeChar(c, BYTE_BIT);
  }

  /**
   * 写入指定字符的16位
   */
  default void writeCharFull(char c) {
    writeChar(c, 16);
  }

  /**
   * 写入指定字符的低r（1～16）位
   *
   * @param c 要写入的字符
   * @param r 位数
   */
  void writeChar(char c, int r);

  /**
   * 写入指定数字的低8位
   */
  default void writeInt(int i) {
    writeInt(i, BYTE_BIT);
  }

  /**
   * 写入指定数字的32位
   */
  default void writeIntFull(int i) {
    writeInt(i, 32);
  }

  /**
   * 写入指定数字的低r（1～32）位
   *
   * @param i 要写入的数字
   * @param r 位数
   */
  void writeInt(int i, int r);


  /**
   * 关闭比特流,同时未写入到输出流的输出流的数据将写入输出流
   */
  void close();
}
