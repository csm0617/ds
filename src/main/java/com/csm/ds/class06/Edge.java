package com.csm.ds.class06;

import java.util.Comparator;

/**
 * 边
 */
public class Edge implements Comparator<Edge> {
    public int weight;//权重
    public GraphNode from;//起点
    public GraphNode to;//终点

    public Edge(int weight, GraphNode from, GraphNode to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "weight=" + weight +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public int compare(Edge o1, Edge o2) {
        return o2.weight - o1.weight;
    }
}
