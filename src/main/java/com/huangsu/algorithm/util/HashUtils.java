package com.huangsu.algorithm.util;

/**
 * Created by huangsu2012@gmail.com on 2021/3/25.
 */
public abstract class HashUtils {

  public static final int MAXIMUM_CAPACITY = 1 << 30;

  /**
   * Returns a power of two size for the given target capacity.
   *
   * 整体思路就是先-1防止传进来的参数就是2的N次方，
   *
   * 右移一位进行或运算此时高位应该有两个1，然后右移动两位进行或运算得到4个1，以此类推
   *
   * 因为整数只有32位，所以只需要进行到右移16位即可
   */
  public static int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }

  /**
   * X % 2^n = X & (2^n – 1)
   *
   * 2^n表示2的n次方，也就是说，一个数对2^n取模 == 一个数和(2^n – 1)做按位与运算 。
   *
   * 假设n为3，则2^3 = 8，表示成2进制就是1000。2^3 -1 = 7 ，即0111。
   *
   * 此时X & (2^3 – 1) 就相当于取X的2进制的最后三位数。
   *
   * 从2进制角度来看，X / 8相当于 X >> 3，即把X右移3位，此时得到了X / 8的商，而被移掉的部分(后三位)，则是X % 8，也就是余数
   */
  public static int indexOf(Object key, int size) {
    return hash(key) & (size - 1);
  }

  public static int indexOf(int keyHash, int size) {
    return keyHash & (size - 1);
  }

  public static int hash(Object key) {
    int h;
    return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);//高低位异或运算防止取模运算只有低位起作用
  }
}
