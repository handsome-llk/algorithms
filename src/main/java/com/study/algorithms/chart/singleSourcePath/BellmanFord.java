package com.study.algorithms.chart.singleSourcePath;

public class BellmanFord {

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


    // 图解：pic/chart/BellmanFord_1.png
    // 但是我感觉这个算法效率很低呀。。。。。一万条路的话。。。循环差不多有1一亿次了

//    BELLMAN-FORD(G, w, s)
//
//    INITIALIZE-SINGLE-SOURCE(G, s)
//    // 为啥是G.V - 1。因为s已经赋值，所以循环里可以拍掉s就是G.V - 1
//    for i = 1 to |G.V| - 1 {
//        for each edge(u, v) in G.E {
//            RELAX(u, v, w)
//        }
//    }
//
//    // 这一段就是为了检查输入图G是否包含可以从源结点到达的权重为负值的环路
//    for each edge(u, v) in G.E {
//        if v.d > u.d + w(u, v) {
//            return false;
//        }
//    }
//    return ture;

}
