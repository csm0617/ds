package com.csm.ds.class06;

import java.util.ArrayList;

public class GraphNode {
    public int value;
    public int in;//入度
    public int out;//出度
    public ArrayList<GraphNode> nexts;//相邻的点
    public ArrayList<Edge> edges;//边

    @Override
    public String toString() {
        return "GraphNode{" +
                "value=" + value +
                '}';
    }

    public GraphNode(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<GraphNode>();
        edges = new ArrayList<Edge>();
    }
}
