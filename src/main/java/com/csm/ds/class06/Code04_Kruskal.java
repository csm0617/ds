package com.csm.ds.class06;

/**
 * 求无向图的最小生成树算法: (!!!一定是无向图)
 * 1.克鲁斯卡尔算法 Kruskal
 * 2.普利姆算法 Prim
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * 克鲁斯卡尔算法 Kruskal: 避圈法
 * 只要不会出现环那么就依次加入最小的边，如果加入后会形成环就舍弃这条边
 */
public class Code04_Kruskal {
    /**
     * 简易版并查集
     */
    public static class MySets {
        /*
            key: node  value: node所在的集合
         */
        public HashMap<GraphNode, List<GraphNode>> setMap = new HashMap<>();


        public MySets(List<GraphNode> nodes) {
            /*
             * 初始化，给每一个node创建一个List,setMap中设置好node和存放的集合
             */
            for (GraphNode node : nodes) {
                List<GraphNode> set = new ArrayList<>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        /**
         * 判断两个节点是不是在一个集合
         *
         * @param from 边的from节点
         * @param to   边的to节点
         * @return
         */
        public boolean isSameSet(GraphNode from, GraphNode to) {
            List<GraphNode> fromSet = setMap.get(from);
            List<GraphNode> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void union(GraphNode from, GraphNode to) {
            List<GraphNode> fromSet = setMap.get(from);
            List<GraphNode> toSet = setMap.get(to);
            for (GraphNode toNode : toSet) {
                fromSet.add(toNode);//加入到from中
                setMap.put(toNode, fromSet);
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMsT(Graph graph) {
        //初始化简易版的并查集
        MySets mySets = new MySets(new ArrayList<>(graph.nodes.values()));
        //初始化一个按边的权重从小到大排序的优先级队列
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        //将边集加入到优先级队列中
        priorityQueue.addAll(graph.edges);
        Set<Edge> result = new HashSet<>();//存放构成最小生成树的边集
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();//依次取出最小的边
            if (!mySets.isSameSet(edge.from, edge.to)) {//如果最小边的from和to不在一个集合中
                result.add(edge);//那么这条边可以作为最小生成树的边
                mySets.union(edge.from, edge.to);//加入后，合并from和to所在的集合
            }
            //在同一个集合，说明这条边添加以后会构成环
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[][] edges = new Integer[][]{{1, 1, 3}, {2, 4, 5}, {3, 1, 2}, {4, 3, 4}, {6, 2, 5}};
        Graph graph = GraphGenerator.createGraph(edges);
        Set<Edge> result = kruskalMsT(graph);
        result.stream().forEach(o-> System.out.println(o));
    }
}
