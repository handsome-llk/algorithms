package com.study.algorithms.chart.singleSourcePath;

/**
 * Dijkstra算法：
 * 解决的是带权重的有向图上单源最短路径问题，该算法要求所有边的权重都为非负值
 */
public class Dijkstra {

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
//    Dijkstra(G, w, s)
//
//    INITIALIZE-SINGLE-SOURCE(G, s)
//
//    S = 空集合
//    Q = G.V
//    while Q != 空集合 {
//        u = EXTRACT-MIN(Q)
//        S = S U {u}
//        for each vertex v in G.Adj[u] {
//            RELAX(u, v, w)
//        }
//    }

    /**
     *
     * 图解：pic/chart/dijkstra_1.png
     *
     * 对EXTRACT-MIN方法做一个说明：
     * 这个方法其实取出的是集合Q中d最小的节点。
     * 其实按我的理解，遍历整个Q集合寻找最小d的节点效率可能过低。
     * 因此当节点d被赋值时，且节点还没进入S时，可以将节点放入新集合B。当节点进入S时，可以移除B和Q中的该节点。
     *
     * 那循环这个节点的正确性如何保证。因为此时该节点的d是Q中最小的，所以可以保证之后的节点，即使存在通往该节点的路径，其产生
     * 的新d也不会比该节点的d小。
     *
     */

}
