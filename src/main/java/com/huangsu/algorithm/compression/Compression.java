package com.huangsu.algorithm.compression;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by huangsu2012@gmail.com on 2021/5/31.
 */
public interface Compression {

  /**
   * 将输入流中的数据压缩并写入指定输出流
   *
   * @param in 待压缩的输入流
   * @param out 压缩数据要写入的输出流
   */
  void compress(InputStream in, OutputStream out);

  /**
   * 将压缩的输入流数据还原并写入指定输出流
   *
   * @param in 已压缩的输入流
   * @param out 还原后的数据要写入的输出流
   */
  void expand(InputStream in, OutputStream out);
}
