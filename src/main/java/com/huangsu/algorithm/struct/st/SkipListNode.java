package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/3.
 */
class SkipListNode<Key, Node extends SkipListNode<Key, Node>> {

  Key key;

  Node backward;

  SkipListNodeLevel<Key, Node>[] levels;

  @SuppressWarnings("unchecked")
  public SkipListNode(Key key, int level) {
    this.key = key;
    levels = (SkipListNodeLevel<Key, Node>[]) new SkipListNodeLevel[level];
  }


  void updateLevelByCopyForward(int l, Node toCopy, int span) {
    Node forwardNode = null;
    if (toCopy.levels.length > l && toCopy.levels[l] != null) {
      forwardNode = toCopy.levels[l].forward;
      if (span < 0) {
        span = toCopy.levels[l].span;
      }
    }
    if (span < 0) {
      span = 0;
    }
    updateLevel(l, forwardNode, span);
  }

  void updateLevel(int l, Node forwardNode, int span) {
    if (l < levels.length) {
      if (levels[l] == null) {
        levels[l] = new SkipListNodeLevel<>(span, forwardNode);
      } else {
        levels[l].forward = forwardNode;
        levels[l].span = span;
      }
    }
  }

  Node forwardNode(int l) {
    return l >= levels.length || levels[l] == null ? null : levels[l].forward;
  }
}
