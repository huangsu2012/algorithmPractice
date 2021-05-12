package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by huangsu2012@gmail.com on 2021/5/5.
 *
 * 跳表核心逻辑抽象
 */
public abstract class AbstractSkipListOrdered<Key, Value, Node extends SkipListNode<Key, Node>> implements
    SetCollectionOrdered<Key> {

  final Comparator<Key> keyComparator;
  final static int DEFAULT_MAX_LEVEL = 32;
  final static double DEFAULT_P = 0.25;
  private Node head;
  /**
   * 用于计算一个节点层数的最大层级
   */
  private int maxLevel;
  /**
   * 用于计算一个节点的层数的概率值,一个节点的平均层数为1/1-p
   */
  private double p;
  private final Random r = new Random();
  private int level;
  private int size;

  public AbstractSkipListOrdered() {
    this(null);
  }

  public AbstractSkipListOrdered(Comparator<Key> keyComparator) {
    this(DEFAULT_MAX_LEVEL, DEFAULT_P, keyComparator);
  }

  public AbstractSkipListOrdered(int maxLevel, double p, Comparator<Key> keyComparator) {
    this.keyComparator = keyComparator;
    this.maxLevel = maxLevel;
    this.p = p;
    head = insertCreateNode(null, null, maxLevel);
  }

  @Override
  public Key min() {
    return size <= 0 ? null : head.levels[0].forward.key;
  }

  @Override
  public Key max() {
    return maxNode().key;
  }

  private Node maxNode() {
    Node node = head;
    int l = level - 1;
    Node fNode;
    while (l > -1) {
      fNode = node.forwardNode(l);
      if (fNode == null) {
        --l;
      } else {
        node = fNode;
      }
    }
    return node;
  }

  @Override
  public Key floor(Key key) {
    Node node = head;
    Node fNode;
    int l = level - 1;
    while (l > -1) {
      fNode = node.forwardNode(l);
      int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
      if (compareVal == 0) {
        break;
      } else if (compareVal > 0) {
        --l;
      } else {
        node = fNode;
      }
    }
    return node.key;
  }

  @Override
  public Key ceiling(Key key) {
    Node node = ceilingNode(key);
    return node == null ? null : node.key;
  }

  private Node ceilingNode(Key key) {
    Node node = head;
    Node fNode;
    int l = level - 1;
    while (l > -1) {
      fNode = node.forwardNode(l);
      int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
      if (compareVal == 0) {
        break;
      } else if (compareVal > 0) {
        --l;
        if (l < 0) {
          node = fNode;
        }
      } else {
        node = fNode;
      }
    }
    return node;
  }

  @Override
  public int rank(Key key) {
    Node node = head;
    Node fNode;
    int l = level - 1;
    int r = 0;
    while (l > -1) {
      fNode = node.forwardNode(l);
      int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
      if (compareVal == 0) {
        break;
      } else if (compareVal > 0) {
        --l;
      } else {
        r += fNode.levels[l].span;
        node = fNode;
      }
    }
    return r;
  }

  @Override
  public Key select(int k) {
    Node node = head;
    Node fNode;
    int l = level - 1;
    int r = 0;
    while (node != null && l > -1 && r < k) {
      fNode = node.forwardNode(l);
      if (fNode == null || r + fNode.levels[l].span > k) {
        --l;
      } else {
        r += fNode.levels[l].span;
        node = fNode;
      }
    }
    return node == null ? null : node.key;
  }

  @Override
  public Iterable<Key> keys(Key lo, Key hi) {
    Queue<Key> queue = new LinkedQueue<>();
    Node node = ceilingNode(lo);
    while (node != null) {
      int compareVal = SortUtils.compareTo(node.key, hi, keyComparator);
      if (compareVal <= 0) {
        queue.enqueue(node.key);
        node = node.levels[0].forward;
      } else {
        break;
      }
    }
    return queue;
  }

  @Override
  public Iterable<Key> keys() {
    Queue<Key> queue = new LinkedQueue<>();
    Node node = head.forwardNode(0);
    while (node != null) {
      queue.enqueue(node.key);
      node = node.forwardNode(0);
    }
    return queue;
  }

  boolean insert(Key key, Value value) {
    Node node = head;
    Node fNode = null;
    int l = level - 1;
    int toInsertNodeRank = 0;
    while (l > -1) {
      fNode = node.forwardNode(l);
      int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
      if (compareVal == 0) {
        insertReplaceNodeValue(fNode, value);
        return false;
      } else if (compareVal > 0) {
        --l;
      } else {
        toInsertNodeRank += fNode.levels[l].span;
        node = fNode;
      }
    }
    int toInsertLevel = randomLevel();
    Node toInsert = insertCreateNode(key, value, toInsertLevel);
    toInsert.backward = node;
    if (fNode != null) {
      fNode.backward = toInsert;
    }
    Node nodeBack = head;
    int nodeBackSpanTotal = 0;
    for (int i = toInsertLevel - 1; i >= 0; ) {
      if (node.levels.length > i) {
        toInsert.updateLevelByCopyForward(i, node, -1);
        node.updateLevel(i, toInsert, 1);
        --i;
      } else {
        fNode = nodeBack.forwardNode(i);
        int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
        if (compareVal > 0) {
          int toInsertNodeSpan = 0;
          int nodeBackSpan = toInsertNodeRank + 1;
          if (nodeBack.levels[i] != null) {
            toInsertNodeSpan = nodeBack.levels[i].span + nodeBackSpanTotal - toInsertNodeRank;
            nodeBackSpan = toInsertNodeRank - nodeBackSpanTotal + 1;
          }
          toInsert.updateLevelByCopyForward(i, nodeBack, toInsertNodeSpan);
          nodeBack.updateLevel(i, toInsert, nodeBackSpan);
          --i;
        } else if (compareVal < 0) {
          nodeBackSpanTotal += fNode.levels[i].span;
          nodeBack = fNode;
        }
      }
    }

    level = Math.max(level, toInsertLevel);
    ++size;
    return true;
  }

  protected abstract Node insertCreateNode(Key key, Value value, int toInsertLevel);

  protected abstract void insertReplaceNodeValue(Node node, Value value);


  Node getNode(Key key) {
    Node node = head;
    Node fNode;
    int l = level - 1;
    while (l > -1) {
      fNode = node.forwardNode(l);
      int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
      if (compareVal == 0) {
        return node;
      } else if (compareVal > 0) {
        --l;
      } else {
        node = fNode;
      }
    }
    return null;
  }

  @Override
  public boolean contains(Key key) {
    return getNode(key) != null;
  }

  @Override
  public void delete(Key key) {
    Node node = head;
    Node fNode;
    int l = level - 1;
    while (l > -1) {
      fNode = node.forwardNode(l);
      int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
      if (compareVal == 0) {
        node = fNode;
        break;
      } else if (compareVal > 0) {
        --l;
      } else {
        node = fNode;
      }
    }
    deleteNode(node);
  }

  @Override
  public void deleteMin() {
    deleteNode(head.levels[0].forward);
  }

  @Override
  public void deleteMax() {
    deleteNode(maxNode());
  }

  private void deleteNode(Node node) {
    if (node != null && node != head) {
      int toDelLevel = node.levels.length;
      if (node.levels[0].forward != null) {
        node.levels[0].forward.backward = node.backward;
      }
      if (node.backward != null) {
        node.backward.levels[0].forward = node.levels[0].forward;
      }
      Node nodeBack = head;
      Node fNode;
      for (int i = toDelLevel - 1; i >= 1; ) {
        fNode = nodeBack.forwardNode(i);
        int compareVal = SortUtils.compareTo(fNode.key, node.key, keyComparator);
        if (compareVal >= 0) {
          nodeBack.updateLevelByCopyForward(i, node, node.levels[i].span + nodeBack.levels[i].span);
          --i;
        } else {
          nodeBack = fNode;
        }
      }
      --size;
    }
  }

//  /**
//   * @param node 需要获取前驱节点的节点
//   * @param l 层级，从0开始
//   * @return 某个节点在某个层级上的前驱节点
//   */
//  private Node<Key, Value> getNodeBackward(Node<Key, Value> node, int l) {
//    Node<Key, Value> nodeBack;
//    if (node.backward == null || node.backward.levels.length < l) {
//      nodeBack = head;
//      while (nodeBack.levels.length >= l
//          && nodeBack.levels[l].forward != null
//          && nodeBack.levels[l].forward != node) {
//        nodeBack = nodeBack.levels[l].forward;
//      }
//    } else {
//      nodeBack = node.backward;
//    }
//    return nodeBack;
//  }


  private int randomLevel() {
    int level = 1;
    // random()返回一个[0...1)的随机数
    while (r.nextDouble() < p && level < maxLevel) {
      level = level + 1;
    }
    return level;
  }

  @Override
  public int size(Key lo, Key hi) {
    int s = 0;
    Node node = ceilingNode(lo);
    while (node != null) {
      int compareVal = SortUtils.compareTo(node.key, hi, keyComparator);
      if (compareVal <= 0) {
        ++s;
        node = node.levels[0].forward;
      } else {
        break;
      }
    }
    return s;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }
}
