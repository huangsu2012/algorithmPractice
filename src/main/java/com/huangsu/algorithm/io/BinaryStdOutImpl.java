package com.huangsu.algorithm.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by huangsu2012@gmail.com on 2021/5/30.
 */
public class BinaryStdOutImpl implements BinaryStdOut {

  private OutputStream out;
  //当前未写入输出流的字节数据
  private int currentByte = -1;
  //当前未写入输出流的字节数据的比特大小，只有该值等于8时才会调用输出流的写入操作
  private int currentByteBit;
  private boolean closed;

  public BinaryStdOutImpl(OutputStream out) {
    if (out == null) {
      throw new NullPointerException("out is null");
    }
    this.out = out;
  }

  @Override
  public void write(boolean b) {
    write(b ? 1 : 0, 1);
  }

  @Override
  public void writeChar(char c) {
    write(c, BYTE_BIT);
  }

  @Override
  public void writeChar(char c, int r) {
    if (r > 16 || r < 1) {
      return;
    }
    write(c, r);
  }

  @Override
  public void writeInt(int i, int r) {
    if (r > 32 || r < 1) {
      return;
    }
    write(i, r);
  }

  private void write(int b, int r) {
    if (closed) {
      return;
    }
    try {
      while (r > 0) {
        int toWrite;
        if (currentByte == -1) {
          toWrite = Math.min(r, 8);
          currentByte = (1 << toWrite) & b;
        } else {
          toWrite = Math.min(r, BYTE_BIT - currentByteBit);
          currentByte |= (1 << toWrite) & b;
        }
        currentByteBit += toWrite;
        r -= toWrite;
        b = b >>> toWrite;
        if (currentByteBit == BYTE_BIT) {
          out.write(currentByte);
          currentByte = -1;
          currentByteBit = 0;
        }
      }
    } catch (IOException e) {//no op
    }
  }

  @Override
  public void close() {
    if (closed) {
      return;
    }
    closed = true;
    try {
      if (currentByte != -1) {
        out.write(currentByte);
      }
      out.flush();
      out.close();
    } catch (IOException e) {//no op
    }
  }

}
