package com.huangsu.algorithm.io;

/**
 * Created by huangsu2012@gmail.com on 2021/5/29.
 *
 * 从输入中读取比特流
 */
public interface BinaryStdIn {

  int BYTE_BIT = 8;

  /**
   * @return 读取的1位数据并返回一个boolean值
   */
  boolean readBoolean();

  /**
   * @return 读取的8位bit值对应的char值
   */
  default char readChar() {
    return readChar(BYTE_BIT);
  }

  /**
   * @param r 位数，1～16
   * @return 读取的r位bit值对应的char值
   */
  char readChar(int r);

  /**
   * @return 读取的8位bit值对应的int值
   */
  default int readInt() {
    return readInt(BYTE_BIT);
  }

  /**
   * @return 读取的32位bit值对应的int值
   */
  default int readIntFull() {
    return readInt(32);
  }

  /**
   * @param r 位数，1～32
   * @return 读取的r位bit值对应的int值
   */
  int readInt(int r);

  /**
   * @return 以16位为基准，读取输入流中的bit并转为char，然后构造其代表的字符串
   */
  String readString();

  /**
   * @return 比特流是否为空
   */
  boolean isEmpty();

  /**
   * 关闭比特流
   */
  void close();
}
