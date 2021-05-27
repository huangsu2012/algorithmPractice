package com.huangsu.algorithm.struct.st;

/**
 * Created by huangsu2012@gmail.com on 2021/5/19.
 */
public abstract class AbstractSetCollection<Key> implements SetCollection<Key> {


  protected static class SetNode<Key> {

    protected Key key;

    protected SetNode(Key key) {
      this.key = key;
    }
  }


  /**
   * Created by huangsu2012@gmail.com on 2021/4/7.
   */
  protected static class OrderedSetNode<Key> extends SetNode<Key> {

    /**
     * 以该节点为根的子树中的节点总数
     */
    protected int nodeCount;

    protected OrderedSetNode(Key key, int nodeCount) {
      super(key);
      this.nodeCount = nodeCount;
    }
  }

}
