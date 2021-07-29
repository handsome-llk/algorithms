package com.study.algorithms.chart.minimumTree;

/**
 * Kruskal:用于生成最小生成树
 */
public class Kruskal {

    /**
     * 设定结果集A，用于保存最小生成树的边。
     * 基本思路是将所有边按长度升序，然后遍历这些排好序的边。如果边的两端都可以在A中找到，则放弃这条边。
     * 若边的两个节点不能全部在A中找到，则将这条边加入A。
     *
     * 图解：pic/chart/minimumTree_2.png
     *
     * 为什么这个思路可行？首先必须注意到这个算法是为了生成最小生成树，而不是为了寻找两点间的最短距离
     *
     */

}
