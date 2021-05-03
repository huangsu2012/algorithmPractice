package com.huangsu.algorithm.struct.st;

import com.huangsu.algorithm.struct.queue.LinkedQueue;
import com.huangsu.algorithm.struct.queue.Queue;
import com.huangsu.algorithm.util.SortUtils;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by huangsu2012@gmail.com on 2021/3/21.
 *
 * 基于跳表实现的有序符号表
 */
public class SkipListOrderedST<Key, Value> extends AbstractOrderedST<Key, Value> implements
    OrderedST<Key, Value> {

  private final static int DEFAULT_MAX_LEVEL = 32;
  private final static double DEFAULT_P = 0.25;
  private Node<Key, Value> head;
  /**
   * 用于计算一个节点层数的最大层级
   */
  private int maxLevel;
  /**
   * 用于计算一个节点的层数的概率值,一个节点的平均层数为1/1-p
   */
  private double p;
  private Random r = new Random();
  private int level;
  private int size;

  public SkipListOrderedST() {
    this(DEFAULT_MAX_LEVEL, DEFAULT_P, null);
  }

  public SkipListOrderedST(Comparator<Key> keyComparator) {
    this(DEFAULT_MAX_LEVEL, DEFAULT_P, keyComparator);
  }

  public SkipListOrderedST(int maxLevel, double p, Comparator<Key> keyComparator) {
    super(keyComparator);
    this.maxLevel = maxLevel;
    this.p = p;
    head = new Node<>(null, null, maxLevel);
  }

  @Override
  public Key min() {
    return size <= 0 ? null : head.levels[0].forward.key;
  }

  @Override
  public Key max() {
    return maxNode().key;
  }

  private Node<Key, Value> maxNode() {
    Node<Key, Value> node = head;
    int l = level - 1;
    Node<Key, Value> fNode;
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
    Node<Key, Value> node = head;
    Node<Key, Value> fNode;
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
    Node<Key, Value> node = ceilingNode(key);
    return node == null ? null : node.key;
  }

  private Node<Key, Value> ceilingNode(Key key) {
    Node<Key, Value> node = head;
    Node<Key, Value> fNode;
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
    Node<Key, Value> node = head;
    Node<Key, Value> fNode;
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
    Node<Key, Value> node = head;
    Node<Key, Value> fNode;
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
    Node<Key, Value> node = ceilingNode(lo);
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
    Node<Key, Value> node = head.forwardNode(0);
    while (node != null) {
      queue.enqueue(node.key);
      node = node.forwardNode(0);
    }
    return queue;
  }

  @Override
  public void put(Key key, Value value) {
    Node<Key, Value> node = head;
    Node<Key, Value> fNode = null;
    int l = level - 1;
    int toInsertNodeRank = 0;
    while (l > -1) {
      fNode = node.forwardNode(l);
      int compareVal = fNode == null ? 1 : SortUtils.compareTo(fNode.key, key, keyComparator);
      if (compareVal == 0) {
        fNode.value = value;
        return;
      } else if (compareVal > 0) {
        --l;
      } else {
        toInsertNodeRank += fNode.levels[l].span;
        node = fNode;
      }
    }
    int toInsertLevel = randomLevel();
    Node<Key, Value> toInsert = new Node<>(key, value, toInsertLevel);
    toInsert.backward = node;
    if (fNode != null) {
      fNode.backward = toInsert;
    }
    Node<Key, Value> nodeBack = head;
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
  }

  @Override
  public Value get(Key key) {
    Node<Key, Value> node = getNode(key);
    return node == null ? null : node.value;
  }

  private Node<Key, Value> getNode(Key key) {
    Node<Key, Value> node = head;
    Node<Key, Value> fNode;
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
    Node<Key, Value> node = head;
    Node<Key, Value> fNode;
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

  private void deleteNode(Node<Key, Value> node) {
    if (node != null && node != head) {
      int toDelLevel = node.levels.length;
      if (node.levels[0].forward != null) {
        node.levels[0].forward.backward = node.backward;
      }
      if (node.backward != null) {
        node.backward.levels[0].forward = node.levels[0].forward;
      }
      Node<Key, Value> nodeBack = head;
      Node<Key, Value> fNode;
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
    Node<Key, Value> node = ceilingNode(lo);
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

  private static class Node<Key, Value> {

    private Key key;
    private Value value;
    private Node<Key, Value> backward;
    /**
     * 存放指向各层链表后一个节点的指针（后向指针）
     */
    private NodeLevel<Key, Value>[] levels;

    @SuppressWarnings("unchecked")
    public Node(Key key, Value value, int level) {
      this.key = key;
      this.value = value;
      levels = (NodeLevel<Key, Value>[]) new NodeLevel[level];
    }

    void updateLevelByCopyForward(int l, Node<Key, Value> toCopy, int span) {
      Node<Key, Value> forwardNode = null;
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

    void updateLevel(int l, Node<Key, Value> forwardNode, int span) {
      if (l < levels.length) {
        if (levels[l] == null) {
          levels[l] = new NodeLevel<>(span, forwardNode);
        } else {
          levels[l].forward = forwardNode;
          levels[l].span = span;
        }
      }
    }

    Node<Key, Value> forwardNode(int l) {
      return l >= levels.length || levels[l] == null ? null : levels[l].forward;
    }
  }

  private static class NodeLevel<Key, Value> {

    /**
     * 表示当前的节点跨越了多少个节点。span用于计算元素排名(rank)
     */
    private int span;
    /**
     * 当前层级的下一个节点
     */
    private Node<Key, Value> forward;

    public NodeLevel(int span,
        Node<Key, Value> forward) {
      this.span = span;
      this.forward = forward;
    }
  }
}
