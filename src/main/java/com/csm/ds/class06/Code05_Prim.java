package com.csm.ds.class06;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 普利姆prim算法求最小生成树：
 * 随机从一个点出发fromNode，解锁所有的边，从所有的边中选择权重最小的边（小根堆），
 * 如果toNode之前没有考虑过（!set.contains），就解锁toNode所有的边加入小根堆，
 * 再重复操作选出权重最小的边。
 *
 */
public class Code05_Prim {
    /*
        边比较器
     */
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        //解锁的边放入小根堆中
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());//权重小根堆
        HashSet<GraphNode> set = new HashSet<>();//标记被访问过的节点
        Set<Edge> result = new HashSet<>();//存放最小生成树的边集
        for (GraphNode node : graph.nodes.values()) {//（其实可以不用遍历整个数组，这里是为了防止出现森林的情况）
            if (!set.contains(node)) {//没走过的点就加入到点集中
                set.add(node);//标记Node被访问过
                priorityQueue.addAll(node.edges);//解锁这个点所有的边
            }
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll();
                GraphNode toNode = edge.to;
                if (!set.contains(toNode)) {//toNode是新点
                    set.add(toNode);//标记toNode被访问过了
                    result.add(edge);//那么这条边可以作为最小生成树的边
                    //toNode作为新解锁的节点把由从toNode发散的边加入到小根堆
                    priorityQueue.addAll(toNode.edges);
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[][] edges = new Integer[][]{{1, 1, 3}, {2, 4, 5}, {3, 1, 2}, {4, 3, 4}, {6, 2, 5}};
        Graph graph = GraphGenerator.createGraph(edges);
        Set<Edge> result = primMST(graph);
        result.forEach(System.out::println);
    }
}
