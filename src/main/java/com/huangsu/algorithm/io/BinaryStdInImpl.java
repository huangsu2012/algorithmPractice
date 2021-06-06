package com.huangsu.algorithm.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by huangsu2012@gmail.com on 2021/5/29.
 */
public class BinaryStdInImpl implements BinaryStdIn {

  private final static char ILLEGAL_CHAR = (char) -1;
  private InputStream in;
  private int currentByte = -1;
  private int currentByteOffset;
  private boolean empty;
  private boolean closed;

  public BinaryStdInImpl(InputStream in) {
    if (in == null) {
      throw new NullPointerException("InputStream is null");
    }
    this.in = in;
  }

  @Override
  public boolean readBoolean() {
    return read(1) == 1;
  }

  @Override
  public char readChar(int r) {
    if (r < 1 || r > 16) {
      return ILLEGAL_CHAR;
    }
    return (char) read(r);
  }

  @Override
  public int readInt(int r) {
    if (r < 1 || r > 32) {
      return -1;
    }
    return read(r);
  }

  @Override
  public String readString() {
    byte[] toReadBytes = new byte[2];
    StringBuilder stringBuilder = new StringBuilder();
    while (!empty) {
      try {
        int read = in.read(toReadBytes);
        if (read == -1) {
          empty = true;
          break;
        }
        int c = 0;
        for (int i = 0; i < read; i++) {
          c = c | (toReadBytes[i] << (i * BYTE_BIT));
        }
        stringBuilder.append((char) c);
      } catch (IOException e) {
        break;
      }
    }
    return stringBuilder.length() > 0 ? stringBuilder.toString() : null;
  }

  private int read(int r) {
    if (empty || r < 1 || closed) {
      return -1;
    }
    int result = 0;
    int readBit = 0;
    if (currentByte != -1 && currentByteOffset < BYTE_BIT) {
      readBit = Math.min(r, BYTE_BIT - currentByteOffset);
      r -= readBit;
      result = currentByte >>> readBit;
      currentByteOffset = currentByteOffset + readBit;
    }
    while (r > 0 && !empty) {
      try {
        currentByte = in.read();
        if (currentByte == -1) {
          empty = true;
          break;
        }
        int toReadBit = Math.min(BYTE_BIT, r);
        currentByteOffset = BYTE_BIT - toReadBit;
        int currentByteTemp = currentByte & (1 << toReadBit);
        result = result | (currentByteTemp << readBit);
        readBit += toReadBit;
        r -= toReadBit;
      } catch (IOException e) {
        currentByte = -1;
        currentByteOffset = 0;
//        empty = true;//不知道是否可以恢复读取
        result = -1;
        break;
      }
    }
    return result;
  }

  @Override
  public boolean isEmpty() {
    return empty;
  }

  @Override
  public void close() {
    if (closed) {
      return;
    }
    closed = true;
    try {
      in.close();
    } catch (IOException e) {//no op
    }
  }
}
