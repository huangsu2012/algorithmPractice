package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 */
class SkipListNodeLevel<Key, Node extends SkipListNode<Key, Node>> {

  /**
   * 表示当前的节点跨越了多少个节点。span用于计算元素排名(rank)
   */
  int span;
  /**
   * 当前层级的下一个节点
   */
  Node forward;


  public SkipListNodeLevel(int span,
      Node forward) {
    this.span = span;
    this.forward = forward;
  }
}
