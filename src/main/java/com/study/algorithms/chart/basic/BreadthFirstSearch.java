package com.study.algorithms.chart.basic;

/**
 * 广度优先搜索
 */
public class BreadthFirstSearch {

// 白色结点：未被发现的结点
// 黑色结点：所有与黑色结点邻接的结点都已经被发现
// 灰色结点：其邻接结点中可能存在未被发现的白色结点
// d：该结点到源结点s的距离
// Π：该结点的前驱结点（父结点）
// Q：先进先出队列Q用来管理灰色结点集
// Adj：Adj[u]表示u的相邻结点构成的链表
// G = (V, E); 图G，V为G所有的节点，E为G所有的边

//  图见pic/chart/BreadthFirstSearch_1.png

//    BFS(G, s)
//
//    for each vertex u in G.V - {s} {
//        u.color = WHITE
//        u.d = Integer.max
//        u.Π = NIL
//    }
//    s.color = GRAY
//    s.d = 0
//    s.Π = NIL
//    Q = 空集合
//    ENQUEUE(Q, s)
//    while Q != 空集合 {
//        u = DEQUEUE(Q)
//        for each v in G.Adj[u] {
//            if v.color == WHITE {
//                v.color = GRAY
//                v.d = u.d + 1
//                v.Π = u
//                ENQUEUE(Q, v)
//            }
//        }
//        u.color = BLACK
//    }

}
