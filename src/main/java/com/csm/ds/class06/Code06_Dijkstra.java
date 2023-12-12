package com.csm.ds.class06;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Dijkstra迪杰斯特拉算法，求从一个点出发的最短路径
 */
public class Code06_Dijkstra {
    public static HashMap<GraphNode, Integer> dijkstra(GraphNode head) {
        /*
           用来记录从head出发到所有点的最小距离
           Key: 从head出发到达key
           Value: 从head出发到达key的最小距离
           如果再表中没有T的记录，含义是从head出发到T这个点的距离为正无穷
         */
        HashMap<GraphNode, Integer> distanceMap = new HashMap<>();
        //一开始把head加入到表中，距离为0
        distanceMap.put(head, 0);
        //定义一个Set标记已经被选过的节点
        HashSet<GraphNode> selectedNodes = new HashSet<>();
        //找到当前distanceMap中距离最小的节点
        GraphNode minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        //当所有节点都被selectedNodes标记时，minNode == null 结束循环。
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);//拿到当前最小节点的距离
            for (Edge edge : minNode.edges) {//遍历minNode的所有边，更新维护distanceMap
                GraphNode toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {//如果节点没有被distanceMap记录（距离无穷大），就记录
                    distanceMap.put(toNode, distance + edge.weight);
                }
                //否则就把从当前节点到toNode的距离和distanceMap中的进行比较，更新为两者中最小的
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
            }
            //把minNode标记为已访问
            selectedNodes.add(minNode);
            //更新minNode，当所有节点都被selectedNodes标记，返回null结束循环
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * @param distanceMap   从head出发到节点的距离表
     * @param selectedNodes 被选择过的节点集合
     * @return 选出从当前节点出发到（过滤掉已经被选择过的节点）其他节点的距离最小的节点
     */
    public static GraphNode getMinDistanceAndUnselectedNode(
            HashMap<GraphNode, Integer> distanceMap,
            HashSet<GraphNode> selectedNodes
    ) {
        GraphNode minNode = null;
        Integer minDistance = Integer.MAX_VALUE;
        for (Map.Entry<GraphNode, Integer> entry : distanceMap.entrySet()) {
            GraphNode node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minDistance = distance;
                minNode = node;
            }
        }
        return minNode;
    }

    public static void main(String[] args) {
        Integer[][] edges = new Integer[][]{{1, 1, 3}, {1, 3, 1}, {2, 4, 5}, {2, 5, 4},
                {3, 1, 2}, {3, 2, 1}, {4, 3, 4}, {4, 4, 3}, {6, 2, 5}, {6, 5, 2}};
        Graph graph = GraphGenerator.createGraph(edges);
        int size = graph.nodes.size();
        int randomHeadIndex = (int) (Math.random() * size);//0 ~ size-1
        GraphNode randomHead = graph.nodes.get(randomHeadIndex);
        System.out.println("start from random-head : " + randomHead.value + " to others min distance:");
        System.out.println(dijkstra(randomHead));
    }
}
