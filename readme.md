算法4中的主要算法实现，打勾的为已实现

### [其它](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm)
- [x] 二分查找相关 BinarySearch
- [x] 连通性问题 UnionFindImpl，UnionFindQuickFind，UnionFindQuickUnion
### [排序](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/sort)
- [x] 选择排序 SelectSort  
- [x] 冒泡排序 BubbleSort  
- [x] 插入排序 InsertSort,InsertSort2  
- [x] 希尔排序 ShellSort
- [x] 归并排序 MergeSort,MergeSort2,MergeSort3,MergeSortBU  
- [x] 快速排序 QuickSort,QuickSort2,QuickSort3
- [x] 二叉堆排序 BinaryHeapSort,BinaryHeapSort2,BinaryHeapSort3

### 数据结构
#### [背包Bag](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/bag)
- [x] 基于数组实现 ArrayBag  
- [x] 基于链表实现 LinkedBag
#### [列表List](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/list)
- [x] 基于数组实现 ArrayList  
- [ ] 基于链表实现 LinkedList

#### [队列Queue](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/queue)
- [x] 基于数组实现 ArrayQueue  
- [x] 基于链表实现 LinkedQueue，CircleLinkedQueue  
- [ ] 基于数组的循环队列  
- [x] 基于数组的双向队列 ArrayDeque  
- [x] 基于链表的双向队列 LinkedDeque  
#### [栈Stack](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/stack)
1. 基于数组的 ArrayStack  
2. 基于链表的 LinkedStack
#### [符号表ST](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/st)
- [x] 基于链表的符号表 SequentialSearchST
- [x] 基于二分查找的有序符号表 BinarySearchOrderedST
- [x] 基于二叉树的符号表 BTOrderedST
- [ ] 基于红黑树的有序符号表
- [x] 基于跳表的符号表 SkipListOrderedST
- [x] 基于hash算法的符号表 SeparateChainingHashST  
- [x] 位图 BitIntByteST

#### [集合Set](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/st)
- [x] 基于链表的集合SequentialSearchSet
- [x] 基于二分查找的有序集合BinarySearchOrderedSet
- [x] 基于二叉树的有序集合BTOrderedSet
- [ ] 基于红黑树的有序集合
- [x] 基于跳表的有序集合SkipListOrderedSet
- [x] 基于hash算法的集合SeparateChainingHashSet

#### [优先队列PriorityQueue](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/priorityqueue)
- [x] 基于二叉堆实现的大顶堆 ArrayHeapMaxPriorityQueue  
- [x] 基于二叉堆实现的小顶堆 ArrayHeapMinPriorityQueue  
- [ ] 基于d叉堆实现的大顶堆 
- [ ] 基于d叉堆实现的小顶堆 
- [x] 基于有序数组实现的大顶堆 ArraySortedMaxPriorityQueue  
- [x] 基于有序数组实现的小顶堆 ArraySortedMinPriorityQueue  
- [x] 基于二叉堆实现的小顶堆索引优先队列 ArrayHeapIndexMinPriorityQueue  
- [x] 基于二叉堆实现的大顶堆索引优先队列 ArrayHeapIndexMaxPriorityQueue  

#### [无向图](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/graph)
- [x] 无向图邻接矩阵实现 AdjacencyMatrixGraph
- [x] 无向图邻接表实现AdjacencyListGraph
- [x] 无向图符号图 SimpleSymbolGraph
- [x] 无向图广度优先搜索单点可达性 BreadthFirstSearch
- [x] 无向图深度优先搜索单点可达性 DepthFirstSearch
- [x] 无向图广度优先搜索单点路径问题 BreadthFirstSearchPaths
- [x] 无向图深度优先搜索单点路径问题 DepthFirstSearchPaths
- [x] 无向图连通分量问题 ConnectedComponents
- [x] 无向图检测无向图是否存在环 Cycle
- [x] 无向图双色问题或者叫二分图问题 TwoColor

#### [有向图](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/graph/di)
- [x] 有向图邻接表实现 AdjacencyListDigraph
- [x] 有向图符号图 SimpleSymbolDigraph
- [x] 有向图可达性 DirectedDFS
- [x] 有向图广度优先搜索单点路径问题 BreadthFirstSearchDirectedPaths
- [x] 有向图深度优先搜索单点路径问题 DepthFirstSearchDirectedPaths
- [x] 有向图有向环检测 DirectedCycle
- [x] 有向图基于深度优先搜索的顶点排序 DepthFirstOrder
- [x] 有向图无环图拓扑排序- 基于深度优先顶点的后序遍历逆序 Topological
- [x] 有向图无环图拓扑排序- 基于队列的实现方式 TopologicalWithQueue
- [x] 有向图强连通分量Kosaraju算法 KosarajuSCC
- [x] 有向图顶点对可达性 TransitiveClosure
#### [无向加权图](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/graph/weighted)
- [x] 无向加权图邻接表实现 AdjacencyListEdgeWeightedGraph
- [ ] 无向加权图使用原始数据类型实现 
- [x] 无向加权图连通图最小生成树Prime延时实现 LazyPrimMST
- [x] 无向加权图连通图最小生成树Prime即时实现 PrimMST
- [x] 无向加权图连通图最小生成树Kruskal实现 KruskalMST
- [ ] 无向加权图最小生成森林 
- [ ] 无向加权图最小生成树Boruvka实现 
- [ ] 外部最小生成树问题（假设内存只能存储V条边，图很大） 
#### [有向加权图](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/struct/graph/di/weighted)
- [x] 有向加权图邻接表实现 AdjacencyListEdgeWeightedDigraph
- [x] 有向加权图（非负权重图）最短路径Dijkstra算法 DijkstraShortestPath
- [x] 有向加权图（非负权重图）最短路径Dijkstra算法lazy实现 LazyDijkstraShortestPath
- [x] 有向加权图（非负权重图）任意顶点对最短路径 DijkstraAllPairsSP
- [x] 有向加权图基于深度优先搜索的顶点排序 DepthFirstOrder
- [x] 有向加权图（无环加权图）最短/最长路径 AcyclicDigraphPath
- [x] 有向加权图（非负权重图）最长路径 DijkstraLongestPath
- [x] 优先级，相对任务最后期限限制下并行任务调度问题 CPM
- [x] 有向加权图负权重环检测 EdgeWeightedDigraphNegativeCycleFinder
- [x] 有向加权图最短路径 BellmanFordShortestPath
- [ ] 套汇问题 
- [ ] 最大网络流问题
### [字符串相关](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/string)
- [x] 字母表Alphabet
- [x] 低位优先排序LSD
- [x] 高位优先排序MSD
- [x] 三向快速排序QuickStringSort
- [x] R向查找树符号表TrieStringST
- [x] R向查找树集合TrieStringSet
- [x] 三向查找树符号表ThreeWayTrieStringST
- [x] 三向查找树集合ThreeWayTrieStringSet
- [x] 子字符串查找暴力算法StringSearchBruteForce
- [x] 子字符串查找kmp基于dfa算法StringSearchKMPDFA
- [x] 子字符串查找boyer moore坏字符算法StringSearchBoyerMoore
- [x] 子字符串查找Rabin Karp指纹字符串算法StringSearchRabinKarp
- [ ] 子字符串查找kmp基于不匹配数组算法
- [ ] 非确定性有限状态机正则表达式匹配
### [io相关](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/io)
- [x] 比特输入流BinaryStdIn
- [x] 比特输出流BinaryStdOut
- [x] 二进制转储BinaryDump
- [x] 十六进制转储HexDump
### [压缩相关](https://github.com/huangsu2012/algorithmPractice/blob/master/src/main/java/com/huangsu/algorithm/compression)
- [x] 定长编码小字母表压缩SmallAlphabetStringCompression
- [ ] 游程编码压缩
- [ ] 霍夫曼编码压缩
- [ ] LZW编码压缩