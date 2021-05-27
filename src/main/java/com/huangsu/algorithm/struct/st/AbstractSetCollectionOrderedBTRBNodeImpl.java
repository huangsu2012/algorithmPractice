package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.AbstractBTNodeRedBlackWithP;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/20.
 *
 * 红黑树有序符号表抽象类，主要提供添加以及删除相关方法递归实现
 */
public abstract class AbstractSetCollectionOrderedBTRBNodeImpl<Key, Value, Node extends AbstractBTNodeRedBlackWithP<Key, Node>> extends
    AbstractSetCollectionOrderedBTImpl<Key, Node> {

  public AbstractSetCollectionOrderedBTRBNodeImpl() {
  }

  public AbstractSetCollectionOrderedBTRBNodeImpl(Comparator<Key> comparator) {
    super(comparator);
  }
}
