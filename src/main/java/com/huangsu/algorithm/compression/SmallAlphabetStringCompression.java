package com.huangsu.algorithm.compression;

import com.huangsu.algorithm.io.BinaryStdIn;
import com.huangsu.algorithm.io.BinaryStdInImpl;
import com.huangsu.algorithm.io.BinaryStdOut;
import com.huangsu.algorithm.io.BinaryStdOutImpl;
import com.huangsu.algorithm.string.Alphabet;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by huangsu2012@gmail.com on 2021/5/31.
 *
 * 对于小型确定的字母表的定长比特压缩实现
 */
public class SmallAlphabetStringCompression implements Compression {

  private final Alphabet alphabet;

  public SmallAlphabetStringCompression(Alphabet alphabet) {
    this.alphabet = alphabet;
  }

  @Override
  public void compress(InputStream in, OutputStream out) {
    BinaryStdIn stdIn = new BinaryStdInImpl(in);
    BinaryStdOut stdOut = new BinaryStdOutImpl(out);

    int charBit = alphabet.lgR();//每个字符使用的比特数
    String s = stdIn.readString();
    stdIn.close();

    int N = s.length();
    stdOut.writeIntFull(N);
    for (int i = 0; i < N; i++) {
      stdOut.writeInt(alphabet.toIndex(s.charAt(i)), charBit);
    }
    stdOut.close();
  }

  @Override
  public void expand(InputStream in, OutputStream out) {
    BinaryStdIn stdIn = new BinaryStdInImpl(in);
    BinaryStdOut stdOut = new BinaryStdOutImpl(out);
    int charBit = alphabet.lgR();//每个字符使用的比特数
    int N = stdIn.readIntFull();
    for (int i = 0; i < N; i++) {
      char c = alphabet.toChar(stdIn.readInt(charBit));
      stdOut.writeCharFull(c);
    }
    stdIn.close();
    stdOut.close();
  }
}
