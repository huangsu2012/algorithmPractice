package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.st.AbstractSetCollectionOrderedBT.AbstractBTNode;
import java.util.Comparator;

/**
 * Created by huangsu2012@gmail.com on 2021/5/19.
 *
 * 二叉查找树节点相关定义
 */
public abstract class AbstractSetCollectionOrderedBT<Key, Node extends AbstractBTNode<Key, Node>> extends
    AbstractSetCollection<Key> implements SetCollectionOrdered<Key> {

  protected Node tree;
  protected final Comparator<Key> keyComparator;

  public AbstractSetCollectionOrderedBT() {
    this(null);
  }

  public AbstractSetCollectionOrderedBT(Comparator<Key> keyComparator) {
    this.keyComparator = keyComparator;
  }

  /**
   *
   * 普通二叉节点只包含键
   */
  protected static class AbstractBTNode<Key, Node extends AbstractBTNode<Key, Node>> extends
      OrderedSetNode<Key> {

    protected Node left;
    protected Node right;

    protected AbstractBTNode(Key key, int nodeCount) {
      super(key, nodeCount);

    }
  }

  /**
   *
   * 普通二叉节点包含键和值
   */
  protected static class AbstractBTNodeST<Key, Value, Node extends AbstractBTNode<Key, Node>> extends
      AbstractBTNode<Key, Node> {

    protected Value value;

    protected AbstractBTNodeST(Key key, Value value, int nodeCount) {
      super(key, nodeCount);
      this.value = value;
    }
  }

  /**
   *
   * 普通含有父节点的二叉节点只包含键
   */
  protected static class AbstractBTNodeWithP<Key, Node extends AbstractBTNodeWithP<Key, Node>> extends
      AbstractBTNode<Key, Node> {

    protected Node parent;

    protected AbstractBTNodeWithP(Node parent, Key key, int nodeCount) {
      super(key, nodeCount);
      this.parent = parent;
    }
  }

  /**
   *
   * 普通含有父节点的二叉节点包含键和值
   */
  protected static class AbstractBTNodeWithPST<Key, Value, Node extends AbstractBTNodeWithPST<Key, Value, Node>> extends
      AbstractBTNodeWithP<Key, Node> {

    protected Value value;

    protected AbstractBTNodeWithPST(Node parent, Key key, Value value, int nodeCount) {
      super(parent, key, nodeCount);
      this.value = value;
    }
  }

  protected static class BTNode<Key> extends
      AbstractBTNode<Key, BTNode<Key>> {

    protected BTNode(Key key, int nodeCount) {
      super(key, nodeCount);
    }
  }

  protected static class BTNodeST<Key, Value> extends
      AbstractBTNodeST<Key, Value, BTNodeST<Key, Value>> {


    protected BTNodeST(Key key, Value value, int nodeCount) {
      super(key, value, nodeCount);
    }
  }

  protected static class BTNodeWithP<Key> extends
      AbstractBTNodeWithP<Key, BTNodeWithP<Key>> {


    protected BTNodeWithP(
        BTNodeWithP<Key> parent, Key key, int nodeCount) {
      super(parent, key, nodeCount);
    }
  }

  protected static class BTNodeWithPST<Key, Value> extends
      AbstractBTNodeWithPST<Key, Value, BTNodeWithPST<Key, Value>> {


    protected BTNodeWithPST(
        BTNodeWithPST<Key, Value> parent, Key key, Value value, int nodeCount) {
      super(parent, key, value, nodeCount);
    }
  }

  /**
   * 普通红黑树节点只包含键
   */
  protected static class AbstractBTNodeRedBlack<Key, Node extends AbstractBTNodeRedBlack<Key, Node>> extends
      AbstractBTNode<Key, Node> {
    //父节点指向当前节点的链接颜色是否为红色
    protected boolean red;

    protected AbstractBTNodeRedBlack(Key key, int nodeCount) {
      super(key, nodeCount);
      this.red = true;
    }

    protected AbstractBTNodeRedBlack(Key key, int nodeCount, boolean red) {
      super(key, nodeCount);
      this.red = red;
    }
  }

  /**
   * 普通红黑树节点包含键和值
   */
  protected static class AbstractBTNodeRedBlackST<Key, Value, Node extends AbstractBTNodeRedBlackST<Key, Value, Node>> extends
      AbstractBTNodeRedBlack<Key, Node> {

    protected Value value;

    protected AbstractBTNodeRedBlackST(Key key, Value value, int nodeCount) {
      super(key, nodeCount);
      this.value = value;
    }

    protected AbstractBTNodeRedBlackST(Key key, Value value, int nodeCount, boolean red) {
      super(key, nodeCount, red);
      this.value = value;
    }
  }

  /**
   * 普通含有父节点的红黑树节点只包含键
   */
  protected static class AbstractBTNodeRedBlackWithP<Key, Node extends AbstractBTNodeRedBlackWithP<Key, Node>> extends
      AbstractBTNodeRedBlack<Key, Node> {

    protected Node parent;

    public AbstractBTNodeRedBlackWithP(Node parent, Key key, int nodeCount) {
      super(key, nodeCount);
      this.parent = parent;
    }

    public AbstractBTNodeRedBlackWithP(Node parent, Key key, int nodeCount, boolean red) {
      super(key, nodeCount, red);
      this.parent = parent;
    }
  }

  /**
   * 普通含有父节点的红黑树节点包含键和值
   */
  protected static class AbstractBTNodeRedBlackWithPST<Key, Value, Node extends AbstractBTNodeRedBlackWithPST<Key, Value, Node>> extends
      AbstractBTNodeRedBlackWithP<Key, Node> {


    public AbstractBTNodeRedBlackWithPST(Node parent, Key key, int nodeCount) {
      super(parent, key, nodeCount);
    }

    public AbstractBTNodeRedBlackWithPST(Node parent, Key key, int nodeCount, boolean red) {
      super(parent, key, nodeCount, red);
    }
  }


  protected static class BTNodeRedBlack<Key> extends
      AbstractBTNodeRedBlack<Key, BTNodeRedBlack<Key>> {


    protected BTNodeRedBlack(Key key, int nodeCount) {
      super(key, nodeCount);
    }

    protected BTNodeRedBlack(Key key, int nodeCount, boolean red) {
      super(key, nodeCount, red);
    }
  }

  protected static class BTNodeRedBlackST<Key, Value> extends
      AbstractBTNodeRedBlackST<Key, Value, BTNodeRedBlackST<Key, Value>> {


    protected BTNodeRedBlackST(Key key, Value value, int nodeCount) {
      super(key, value, nodeCount);
    }

    protected BTNodeRedBlackST(Key key, Value value, int nodeCount, boolean red) {
      super(key, value, nodeCount, red);
    }
  }


  protected static class BTNodeRedBlackWithP<Key> extends
      AbstractBTNodeRedBlackWithP<Key, BTNodeRedBlackWithP<Key>> {

    public BTNodeRedBlackWithP(
        BTNodeRedBlackWithP<Key> parent, Key key, int nodeCount) {
      super(parent, key, nodeCount);
    }

    public BTNodeRedBlackWithP(
        BTNodeRedBlackWithP<Key> parent, Key key, int nodeCount, boolean red) {
      super(parent, key, nodeCount, red);
    }
  }

  protected static class BTNodeRedBlackWithPST<Key, Value> extends
      AbstractBTNodeRedBlackWithPST<Key, Value, BTNodeRedBlackWithPST<Key, Value>> {


    public BTNodeRedBlackWithPST(
        BTNodeRedBlackWithPST<Key, Value> parent, Key key, int nodeCount) {
      super(parent, key, nodeCount);
    }

    public BTNodeRedBlackWithPST(
        BTNodeRedBlackWithPST<Key, Value> parent, Key key, int nodeCount, boolean red) {
      super(parent, key, nodeCount, red);
    }
  }
}
