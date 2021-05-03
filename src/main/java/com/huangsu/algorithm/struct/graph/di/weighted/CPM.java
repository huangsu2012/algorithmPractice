package com.huangsu.algorithm.struct.graph.di.weighted;

/**
 * Created by huangsu2012@gmail.com on 2021/4/28.
 *
 * 并行任务调度问题
 */
public class CPM {

  private final int taskCount;

  private final EdgeWeightedDigraph g;

  private final int start;
  private final int end;

  private boolean deadlineLimitProblem;

  private EdgeWeightedDigraphPath weightedDigraphPath;

  public CPM(int taskCount) {
    this.taskCount = taskCount;
    g = new AdjacencyListEdgeWeightedDigraph(taskCount * 2 + 2);
    start = 2 * taskCount;
    end = start + 1;
  }

  /**
   * 添加一个任务
   *
   * @param dependOnTask 该任务依赖的任务，如果没有传入-1
   * @param task 要添加的任务
   * @param costTime 任务花费时间
   */
  public void addTask(int dependOnTask, int task, double costTime) {
    if (dependOnTask < taskCount && dependOnTask != task
        && task < taskCount && task >= 0
        && costTime >= 0) {
      weightedDigraphPath = null;
      g.addEdge(new DirectedEdge(start, task, 0));//对任务添加起点到该任务起点权重为零的边
      if (dependOnTask >= 0) {//对任务依赖的任务，添加依赖任务终点指向任务起点权重为零的边
        g.addEdge(new DirectedEdge(dependOnTask + taskCount, task, 0));
      }
      g.addEdge(new DirectedEdge(task, task + taskCount, costTime));//对任务添加一个该任务起点到其终点权重为花费时间的边
      g.addEdge(new DirectedEdge(task + taskCount, end, 0));//对任务添加该任务终点到终点权重为零的边
    }
  }

  /**
   * 添加任务的相对时间限制
   *
   * 任务v在w启动之后d个的时间单位开始，本质上是修改了w的开始时间
   *
   * 其应该等于startV-d，要完成这个修改很明显应该添加一条从v的结束顶点（因为startV明显包含了v的起始顶点到其结束顶点的边）到w的开始顶点的边
   *
   * 如果startV-d>startW这条新添加的边就会形成一个新的执行路径
   *
   * @param afterTask 任务必须在该任务之后的多长时间内执行
   * @param task 要添加相对时间限制的任务
   * @param afterTime 相对于afterTask多长时候后执行,正数
   */
  public void addDeadlineLimit(int afterTask, int task, double afterTime) {
    if (afterTask < taskCount && afterTask != task
        && task < taskCount && task >= 0
        && afterTime >= 0) {
      weightedDigraphPath = null;
      deadlineLimitProblem = true;
      g.addEdge(new DirectedEdge(task + taskCount, afterTask, -afterTime));
    }
  }

  public double startTime(int task) {
    calPath();
    return deadlineLimitProblem ? -weightedDigraphPath.distTo(task)
        : weightedDigraphPath.distTo(task);
  }

  public double totalTime() {
    return startTime(end);
  }

  private void calPath() {
    if (weightedDigraphPath == null) {
      if (!deadlineLimitProblem) {
        weightedDigraphPath = new AcyclicDigraphPath(g, start, false);
      } else {//对于有任务的相对时间限制的情况，由于可能存在环，所以AcyclicDigraphPath不能解决
        EdgeWeightedDigraph reverseWeightGraph = new AdjacencyListEdgeWeightedDigraph(g.V());
        for (DirectedEdge edge : g.edges()) {
          reverseWeightGraph.addEdge(new DirectedEdge(edge.from(), edge.to(), -edge.weight()));
        }
        weightedDigraphPath = new BellmanFordShortestPath(reverseWeightGraph, start);
      }
    }
  }
}
