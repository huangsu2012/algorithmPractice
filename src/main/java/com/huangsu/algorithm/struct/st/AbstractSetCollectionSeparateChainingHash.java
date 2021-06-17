package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.struct.st.AbstractSetCollectionSeparateChainingHash.NodeHash;
import com.huangsu.algorithm.util.HashUtils;

/**
 * Created by huangsu2012@gmail.com on 2021/5/27.
 *
 * 基于拉链法实现的散列集合
 */
public abstract class AbstractSetCollectionSeparateChainingHash<Key, Value, Node extends NodeHash<Key, Node>> extends
    AbstractSetCollection<Key> {

  protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
  protected Node[] table;
  protected int tableLength;
  protected int size;
  protected int threshold;
  protected final float loadFactor;

  public AbstractSetCollectionSeparateChainingHash() {
    this(16, DEFAULT_LOAD_FACTOR);
  }

  public AbstractSetCollectionSeparateChainingHash(int initCap) {
    this(initCap, DEFAULT_LOAD_FACTOR);
  }

  public AbstractSetCollectionSeparateChainingHash(int initCap, float loadFactor) {
    if (loadFactor <= 0 || loadFactor >= 1) {
      throw new IllegalArgumentException("loadFactor illegal");
    }
    this.tableLength = HashUtils.tableSizeFor(initCap);
    this.loadFactor = loadFactor;
  }

  /**
   * @param key 要删除的key
   * @return 被删除的节点或者null（如果没找到的话）
   */
  protected Node deleteNode(Key key) {
    int hash = HashUtils.hash(key);
    int index = HashUtils.indexOf(hash, tableLength);
    Node e = table[index];
    Node prev = null;
    boolean finded = false;
    while (e != null) {
      if (e.key == key || (key != null && key.equals(e.key))) {
        finded = true;
        break;
      } else {
        prev = e;
        e = e.next;
      }
    }
    if (finded) {
      if (prev == null) {
        table[index] = null;
      } else {
        prev.next = e.next;
      }
      --size;
    }
    return finded ? e : null;
  }

  @Override
  public boolean contains(Key key) {
    int hash = HashUtils.hash(key);
    int index = HashUtils.indexOf(hash, tableLength);
    Node e = table[index];
    while (e != null) {
      if (e.key == key || (key != null && key.equals(e.key))) {
        return true;
      } else {
        e = e.next;
      }
    }
    return false;
  }

  @Override
  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedQueue<>();
    for (int i = 0; i < tableLength; i++) {
      Node e = table[i];
      while (e != null) {
        queue.enqueue(e.key);
        e = e.next;
      }
    }
    return queue;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @SuppressWarnings("unchecked")
  protected boolean insert(Key key, Value value) {
    resize();
    boolean add = false;
    int hash = HashUtils.hash(key);
    int index = HashUtils.indexOf(hash, tableLength);
    Node e = table[index];
    if (e == null) {
      e = insertCreateNode(key, value, hash);
      table[index] = e;
      add = true;
    } else {
      Node prev = null;
      while (e != null) {
        if (e.key == key || (key != null && key.equals(e.key))) {
          if (e instanceof NodeHashST) {
            ((NodeHashST<Key, Value>) e).value = value;
          }
          break;
        } else {
          prev = e;
          e = e.next;
        }
      }
      if (e == null) {
        prev.next = insertCreateNode(key, value, hash);
        add = true;
      }
    }
    if (add) {
      ++size;
    }
    return add;
  }

  /**
   * 在扩容的时候元素的位置要么是在原位置，要么是在原位置再移动2次幂的位置;这里假设只会进行容量增加
   *
   * 解释：
   *
   * 假设oldLen=16,newLen=32,hash为某个节点hash值
   *
   * oldLen二进制的后六位为010000,oldLen-1二进制的后六位为001111
   *
   * newLen二进制的后六位为100000,newLen-1二进制的后六位为011111
   *
   * hash&(oldLen-1)与hash&(newLen-1)二进制结果唯一不同的地方就是倒数第五位的计算结果，如果hash的倒数第五位是0则两者计算结果一致，也就是索引不变，如果是1则索引变为oldIndex+1*2^4=oldIndex+oldLen
   *
   * 而要知道第五位是否为0，只需要和oldLen做与运算即可，与运算结果为0则第五位为0
   *
   * 其它长度的tableLength同理
   */
  protected void resize() {
    if (size >= threshold) {
      int oldLen = threshold == 0 ? 0 : tableLength;
      tableLength = threshold == 0 ? tableLength : oldLen << 1;
      threshold = (int) (tableLength * loadFactor);
      if (tableLength > HashUtils.MAXIMUM_CAPACITY) {
        tableLength = HashUtils.MAXIMUM_CAPACITY;
      }
      Node[] newTab = createNewTab(tableLength);
      Node e;
      int newLenMaxIndex = tableLength - 1;
      for (int i = 0; i < oldLen; i++) {
        e = table[i];
        if (e != null) {
          if (e.next == null) {//如果只是单个元素那么直接移动到新位置
            newTab[e.hash & newLenMaxIndex] = e;
          } else {
            Node loHead = null, loTail = null;//不需要移动的头尾节点
            Node hiHead = null, hiTail = null;//需要移动的头尾节点
            do {
              if ((e.hash & oldLen) == 0) {// 原索引
                if (loTail == null) {
                  loHead = e;
                } else {
                  loTail.next = e;
                }
                loTail = e;
              } else {// 原索引+oldCap
                if (hiTail == null) {
                  hiHead = e;
                } else {
                  hiTail.next = e;
                }
                hiTail = e;
              }
              e = e.next;
            } while (e != null);
            // 原索引放到bucket里
            if (loTail != null) {
              loTail.next = null;
              newTab[i] = loHead;
            }
            // 原索引+oldCap放到bucket里
            if (hiTail != null) {
              hiTail.next = null;
              newTab[i + oldLen] = hiHead;
            }
          }
        }
      }
      this.table = newTab;
    }
  }

  protected abstract Node[] createNewTab(int tableLength);

  protected abstract Node insertCreateNode(Key key, Value value, int hash);

  protected static class NodeHash<Key, Node extends NodeHash<Key, Node>> extends SetNode<Key> {

    protected int hash;
    protected Node next;

    protected NodeHash(Key key, int hash) {
      super(key);
      this.hash = hash;
    }
  }

  protected static class NodeHashSet<Key> extends NodeHash<Key, NodeHashSet<Key>> {

    protected NodeHashSet(Key key, int hash) {
      super(key, hash);
    }
  }

  protected static class NodeHashST<Key, Value> extends NodeHash<Key, NodeHashST<Key, Value>> {

    protected Value value;

    protected NodeHashST(Key key, Value value, int hash) {
      super(key, hash);
      this.value = value;
    }
  }
}
