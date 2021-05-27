package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/25.
 *
 * 整型位图抽象
 */
public interface BitIntST {

  /**
   * @param k 将数字k所在位设为true
   */
  void set(int k);

  /**
   * @return true 表示数字k存在位图中
   */
  boolean get(int k);

  /**
   * @param k 将数字k所在位设为false
   */
  void delete(int k);
}
