package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/25.
 */
public class BitIntByteST implements BitIntST {

  private final static int ARRAY_ELEMENT_BIT = 8;
  private final int numCount;
  private final byte[] bytes;
  private final int start;

  public BitIntByteST(int numCount) {
    this(0, numCount);
  }

  public BitIntByteST(int start, int numCount) {
    this.start = start;
    int bytesSize = numCount / ARRAY_ELEMENT_BIT + 1;
    this.numCount = numCount;
    bytes = new byte[bytesSize];
  }


  @Override
  public void set(int k) {
    k -= start;
    if (k > numCount) {
      return;
    }
    int index = k / ARRAY_ELEMENT_BIT;
    int bit = k % ARRAY_ELEMENT_BIT;
    bit = 1 << bit;
    bytes[index] = (byte) (bytes[index] | bit);
  }

  @Override
  public boolean get(int k) {
    k -= start;
    if (k > numCount) {
      return false;
    }
    int index = k / ARRAY_ELEMENT_BIT;
    int bit = k % ARRAY_ELEMENT_BIT;
    bit = 1 << bit;
    return (bytes[index] & bit) == bit;
  }

  @Override
  public void delete(int k) {
    k -= start;
    if (k > numCount) {
      return;
    }
    int index = k / ARRAY_ELEMENT_BIT;
    int bit = k % ARRAY_ELEMENT_BIT;
    bit = 1 << bit;
    bit = ~bit;
    bytes[index] = (byte) (bytes[index] & bit);//这里不能用异或操作，否则删除一个没有加入集合的数字会导致它反而在集合中
  }
}
