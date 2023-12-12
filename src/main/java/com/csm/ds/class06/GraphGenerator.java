package com.csm.ds.class06;

/**
 * 因为图的表示方式有多种，邻接表，邻接矩阵，数组等等，图的表示方式不同，图的算法可能会左不同的调整
 * 所以要提供不同的接口，将图其他的表示方式转换成自己熟悉的方式
 */
public class GraphGenerator {

    /**
     * 把一个图的表示方式转换成自己熟悉的图的表示方式
     *
     * @param matrix 二维数组 每一行表示 边的起点node和终点node，以及他们边的权重  【weight from  to】
     * @return
     */
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        /*
         *遍历整个二维数组，构建图
         */
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {//如果from是新来的点，就加入到的图的点集中
                graph.nodes.put(from, new GraphNode(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new GraphNode(to));//同理如果to是新来的节点，加入到点击
            }
            //from和to在点集中已存在，建立边的关系
            GraphNode fromNode = graph.nodes.get(from);
            GraphNode toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);//把toNode加入到自己的指向节点集中
            fromNode.out++;//fromNode的出度++
            toNode.in++;//toNode的入度++
            fromNode.edges.add(edge);//加入到属于fromNode的边集中
            graph.edges.add(edge);//hashSet的不存放重复值//加入到graph的边集中
        }
        return graph;
    }
}
