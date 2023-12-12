package com.csm.ds.class06;

import java.util.*;

/**
 * 图的拓扑排序：(依赖)
 * 首先：有环图是没办法进行拓扑排序的，会造成循环依赖
 * 既然是无环图，那么一定有入度为0的节点，找到这个入度为0的节点，打印、加入结果集（处理）后，从图中消除这个节点对其它的邻接节点的产生的影响（边，入度）
 * 消除影响后，找到下一个入度为0的节点，往复这个操作最终完成拓扑排序
 */
public class Code03_TopologySort {
    public static List<GraphNode> sortedTopology(Graph graph) {
        //map 统计 key:node value:剩余的入度
        HashMap<GraphNode, Integer> inMap = new HashMap<>();
        //queue把所有入度为0的node入队
        Queue<GraphNode> zeroInQueue = new LinkedList<>();
        //遍历整个图的节点集，放入map,并把入度为0的节点入队
        for (GraphNode node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        //存放拓扑排序的结果
        List<GraphNode> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            GraphNode cur = zeroInQueue.poll();
            result.add(cur);//出队的时候，将入度为0的节点加入结果集
            //消除入度为0的节点对邻接节点造成的影响（减少对相邻节点的入度）
            for (GraphNode next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {//如果入度更新后，有节点的入度变成了0
                    zeroInQueue.add(next);//就加入到队列中
                }
            }
        }
        return result;
    }
}
