package com.study.algorithms.chart.singleSourcePath;

/**
 * 有向无环图中的单元最短路径问题：
 * 大致思路：
 * 1、先对节点进行拓扑排序
 * 2、对每个节点做循环
 * 3、在第2步中，对每个节点的每条边做循环
 * 4、在第三步中每条边做松弛处理
 *
 * 图解：pic/chart/DirectedAcyclicGraph_1.png
 *
 */
public class DirectedAcyclicGraph {

//    INITIALIZE-SINGLE-SOURCE(G, s)
//
//    for each vertex v in G.V {
//        v.d = Integer.max
//        v.p = NIL
//    }
//    s.d = 0;
//
//
//    RELAX(u, v, w)
//
//    if v.d > u.d + w(u, v) {
//        v.d = u.d + w(u, v)
//        w.p = u
//    }
//
//
//    DAG-SHORTEST-PATHS(G, w, s)
//
//    topologically sort the vertices of GCMParameterSpec
//    for each vertex u, taken in topologically sorted order {
//        for each vertex v in G.Adj[u] {
//            RELAX(u, v, w)
//        }
//    }

}
